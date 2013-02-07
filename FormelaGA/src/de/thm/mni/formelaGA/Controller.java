package de.thm.mni.formelaGA;

import java.util.ArrayList;
import java.util.List;

import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.impls.AngleItem;
import de.thm.mni.formelaGA.items.impls.Constant;
import de.thm.mni.formelaGA.items.impls.LogItem;
import de.thm.mni.formelaGA.items.impls.PotenzItem;
import de.thm.mni.formelaGA.items.impls.StandartItem;


public class Controller {

	private static List<Gen> genom;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		initialisierung();
		
		run();
	}
	

	private static void initialisierung(){
		
		ApplicationGlobals.X = new XVariable();
		genom = new ArrayList<Gen>();
		
		for(int i = 0; i < ApplicationGlobals.GEN_COUNT; i++){
			genom.add(new Gen(ApplicationGlobals.changeFormelItem(ApplicationGlobals.X)));
		}
		
	}
	
	private static void run(){
		
		double bestFit;
		
		for(long i = 1; i <= ApplicationGlobals.MAX_GENERATION || ApplicationGlobals.inDevelopment; i++){
			
			mutation();
			
			bestFit = test();
			if(bestFit == 0.0) break;
			
			//recombination();
			
			bestFit = test();
			if(bestFit>= 0.0) break;
			
			replication();
			
			bestFit = test();
			if(bestFit>= 0.0) break;
			System.out.println("Beste Fitness: "+bestFit);
			//System.out.println("f(x) = "+genom.get(0).getFormelPattern().getWriteAble());
		}
		System.out.println("f(x) = "+genom.get(0).getFormelPattern().getWriteAble());

		
	}
	
	private static double test() {
		
		setFitnesses();
		
		sort(0, genom.size()-1);
		
		return genom.get(0).getFitness();
	}


	private static void sort(int li, int re) {
        int i, j;
        Gen x;
        while (re > li)
        {
            i = li;
            j = re;
            x = genom.get(li);
            while(i < j)
            {
                while(genom.get(j).getFitness()<x.getFitness())j--;
                genom.set(i, genom.get(j));
                while ((i<j) && !(genom.get(i).getFitness()<x.getFitness()))i++;
                genom.set(j, genom.get(i));
            }
            genom.set(i, x);
            sort(li, i-1);
            li =i+1;
        }
				
	}


	private static void mutation(){
		
		int i = 0;
		
		if(ApplicationGlobals.PROT_BEST){
			i++;
		}
		
		for(; i < ApplicationGlobals.GEN_COUNT; i++){
			genom.get(i).mutate();
		}
	}
	
	private static void replication(){
		List<Gen> temp = new ArrayList<Gen>();
		
		int populationCount = ApplicationGlobals.GEN_COUNT / ApplicationGlobals.REPL_TOP_X;
		
		
		for(int i = 0; i < ApplicationGlobals.REPL_TOP_X; i++){
			for( int j = 0; j < populationCount; j++){
				temp.add(genom.get(i).clone());
			}
		}
		
		genom = temp;
	}
	
	private static void recombination(){
		
		
		for(int i = 0; i < ApplicationGlobals.GEN_COUNT * ApplicationGlobals.PC ; i++){
			
			FormelItem xo1 = null;
			FormelItem xo2 = null;
			
			do{
				do{
					xo1 = genom.get(ApplicationGlobals.randomer.nextInt(ApplicationGlobals.GEN_COUNT)).getFormelPattern();
				}while(xo1.getDepth()<1);
				
				do{ // xo1 und xo2 sollten nicht gleich sein
					xo2 = genom.get(ApplicationGlobals.randomer.nextInt(ApplicationGlobals.GEN_COUNT)).getFormelPattern();
				}while(xo1 == xo2 || xo2.getDepth()<1);
				
				//bei protbest muss die init wiederholt werden, wenn best dabei ist
			}while(ApplicationGlobals.PROT_BEST &&( xo1 == genom.get(0) || xo2 == genom.get(0)));
			
			//####################################################
			
			int depth1 = ApplicationGlobals.randomer.nextInt(xo1.getDepth());
			
			int depth2 = ApplicationGlobals.randomer.nextInt(xo2.getDepth());
			
			FormelItem part1 = xo1.getXOPart(depth1);
			
			FormelItem part2 = xo2.getXOPart(depth2);
			
			FormelItem temp = part1.getXOPart(1);
			
			
			((FormelPattern)part1).setXOValue(part2.getXOPart(1));
			((FormelPattern)part2).setXOValue(temp);
			
		}
		
		
	}

	private static void setFitnesses(){
		for(Gen gen : genom){
			
			double result = 0.;
			double j = 0;
			
			for(int i = 0; j < ApplicationGlobals.SOLUTIONS.length; j+=ApplicationGlobals.X_STEPS, i++){
	
				ApplicationGlobals.X.setXValue(j);
				double uc = gen.getFormelPattern().getUseCase();
				double diff = Math.pow(uc - ApplicationGlobals.SOLUTIONS[i], 2);
				result -= (Math.pow(gen.getFormelPattern().getUseCase()-
						ApplicationGlobals.SOLUTIONS[i], 2));
				
			}
			if(Double.isNaN(result)){
				result = Double.NEGATIVE_INFINITY;
			}
			gen.setFitness(result);
		}
		
	}
}
