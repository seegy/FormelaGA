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
		
		for(long i = 1; i <= ApplicationGlobals.MAX_GENERATION; i++){
			mutation();
			
			bestFit = test();
			if(bestFit == 0.0) break;
			
			recombination();
			
			bestFit = test();
			if(bestFit>= 0.0) break;
			
			replication();
			
			bestFit = test();
			if(bestFit>= 0.0) break;
			System.out.println("Beste Fitness: "+bestFit);
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
		
		for(int i = 0; i < ApplicationGlobals.GEN_COUNT; i++){
			genom.get(i).mutate();
		}
	}
	
	private static void replication(){
		List<Gen> temp = new ArrayList<>();
		//TODO
		//genom = temp;
	}
	
	private static void recombination(){
		// TODO
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
