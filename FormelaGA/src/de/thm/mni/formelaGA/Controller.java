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

	private static List<FormelPattern> genom;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		initialisierung();
		
		run();
	}
	

	private static void initialisierung(){
		
		ApplicationGlobals.X = new XVariable();
		genom = new ArrayList<FormelPattern>();
		
		for(int i = 0; i < ApplicationGlobals.GEN_COUNT; i++){
			genom.add(ApplicationGlobals.randomMiddleItem(ApplicationGlobals.X));
		}
		
	}
	
	private static void run(){
		
		for(long i = 1; i <= ApplicationGlobals.MAX_GENERATION; i++){
			mutation();
			
			if(test() >= 1.0) break;
			
			recombination();
			
			if(test()>= 1.0) break;
			
			replication();
			
			if(test()>= 1.0) break;
			System.out.println("Beste Fitness: "+getFitness(genom.get(0)) );
		}
		
		System.out.println("f(x) = "+genom.get(0).getWriteAble());
	}
	
	private static double test() {

		sort(0, genom.size()-1);
		
		return getFitness(genom.get(0));
	}


	private static void sort(int li, int re) {
        int i, j;
        FormelPattern x;
        while (re > li)
        {
            i = li;
            j = re;
            x = genom.get(li);
            while(i < j)
            {
                while(getFitness(genom.get(j))<getFitness(x))j--;
                genom.set(i, genom.get(j));
                while ((i<j) && !(getFitness(genom.get(i))<getFitness(x)))i++;
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
		// TODO
	}
	
	private static void recombination(){
		// TODO
	}

	private static double getFitness(FormelItem fi){
		double result = 1.0;
		double j = 0;
		
		for(int i = 0; j < ApplicationGlobals.SOLUTIONS.length; j+=ApplicationGlobals.X_STEPS, i++){

			ApplicationGlobals.X.setXValue(j);
			result -= (Math.pow(fi.getUseCase()-ApplicationGlobals.SOLUTIONS[i], 2))/Math.pow(ApplicationGlobals.SOLUTIONS[i], 2);
			
		}
		
		return result;
	}
}
