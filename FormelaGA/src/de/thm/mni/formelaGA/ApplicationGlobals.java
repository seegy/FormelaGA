package de.thm.mni.formelaGA;

import java.util.Random;

import de.thm.mni.formelaGA.items.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.OneCompItem;
import de.thm.mni.formelaGA.items.TwoCompItem;
import de.thm.mni.formelaGA.items.XVariable;
import de.thm.mni.formelaGA.items.impls.AngleItem;
import de.thm.mni.formelaGA.items.impls.Constant;
import de.thm.mni.formelaGA.items.impls.LogItem;
import de.thm.mni.formelaGA.items.impls.PotenzItem;
import de.thm.mni.formelaGA.items.impls.SqrtItem;
import de.thm.mni.formelaGA.items.impls.StandartItem;

public final class ApplicationGlobals {

	public static final String NAME = "GaussGenom";
	
	/**
	 * Develop
	 */
	public static final boolean inDevelopment = true;
	

	/**
	 * GA
	 */
	
	public static final int GEN_COUNT = 300;
	
	public static final double PM = 0.2;
	
	public static final double CHANGE_PM = 0.2;
	
	public static final double CONST_PM = 0.75;
	
	public static final long MAX_GENERATION = 200;
	
	public static final int REPL_TOP_X  = 30;
	
	public static final boolean PROT_BEST = true;
	
	public static final double PC = 0.4;
	
	public static final boolean DOUBLE_COMPARE = true;
	
	public static final double DOUBLE_COMPARE_RELEVANCE = .01;
	
	/**
	 * Formel
	 */
	
	public static XVariable X;
	
	public static double X_STEPS = 1.0;
	
//	public static double[] SOLUTIONS = {0+1, 1+1, 2+1, 3+1, 4+1, 5+1, 6+1, 7+1, 8+1, 9+1};
	
//	public static double[] SOLUTIONS = {(0*2)+1+10, (1*2)+1+10, (2*2)+1+10, (3*2)+1+10, (4*2)+1+10, (5*2)+1+10, (6*2)+1+10, (7*2)+1+10, (8*2)+1+10, (9*2)+1+10};

	public static double[] SOLUTIONS = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
	
	
	//########### Tools ###############
	
	public static final Random randomer = new Random();
	
	public static FormelPattern changeFormelItem(FormelItem fi){

		if(fi instanceof OneCompItem ){
			return createFormelPatter(((OneCompItem)fi).getComp(), null);
			
		} else if(fi instanceof TwoCompItem){
			return createFormelPatter(((TwoCompItem)fi).getCompOne(), 
					((TwoCompItem)fi).getCompTwo());
			
		} else if (fi instanceof XVariable){
			return createFormelPatter(fi, null);
		}
		
		return null;
	}
	
	
	
	public static FormelItem newEndItem(){
		
		FormelItem result = null;
		
		if(ApplicationGlobals.randomer.nextDouble() <= CONST_PM){
			result = new Constant(1);
			
		} else {
			
			result = X;
		}
		
		return result;
	}
	/**
	 * diese methode nimmt einen Value auf und packt 
	 * ihn in ein random erstelltes Formelpattern und gibt dieses zurück
	 */

	public static FormelPattern newMiddleFormelPattern(FormelItem value){
		
		return createFormelPatter(value, null);
		
		
	}
	
	private static FormelPattern createFormelPatter(FormelItem value1, FormelItem value2){
		
		FormelPattern result = null;
		FormelItem[] args = { value1, value2 };
		boolean torf = true;
		
		if(args[1] == null){
			torf = false;
			args[1] = newEndItem();
		}
		
		int i = ApplicationGlobals.randomer.nextInt(2);
		
		switch(ApplicationGlobals.randomer.nextInt(5)){
		
		case 0:
			result = new AngleItem(args[(torf?0:i)]);
			break;
		case 1:
			result = new LogItem(args[1-i], args[i]);
			break;
		case 2:
			result = new PotenzItem(args[1-i], args[i]);
			break;
		case 3:
			result = new StandartItem(args[1-i], args[i]);
			break;
		case 4:
			result = new SqrtItem(args[(torf?0:i)]);
			break;
		}
		
		return result;
		
	}
	
	

	public static FormelItem removeFormelPattern(FormelPattern fp){
		
		
		if(fp instanceof OneCompItem ){
			return ((OneCompItem)fp).getComp();
			
		} else if(fp instanceof TwoCompItem){
			switch(randomer.nextInt(2)){
				case 0:
					return ((TwoCompItem)fp).getCompOne();
				case 1:
					return ((TwoCompItem)fp).getCompTwo();
			}
			
		}
		return null;
	}
	
}
