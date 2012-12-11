package de.thm.mni.formelaGA;

import de.thm.mni.formelaGA.items.AngleItem;
import de.thm.mni.formelaGA.items.Constant;
import de.thm.mni.formelaGA.items.LogItem;
import de.thm.mni.formelaGA.items.PotenzItem;
import de.thm.mni.formelaGA.items.StandartItem;


public class Controller {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i = 0; i <= 10; i++){
			ApplicationGlobals.X = new XVariable();
			FormelItem fi = new StandartItem(ApplicationGlobals.X, ApplicationGlobals.X);
			fi = new LogItem(new Constant(1337), fi);
			fi = new AngleItem(fi);
			fi = new PotenzItem( fi, ApplicationGlobals.X);
			System.out.println("f(x) = "+ new StandartItem(fi, fi).getWriteAble());
			tryUseCase(fi, ApplicationGlobals.X);
		}
	}
	
	private static void tryUseCase(FormelItem fi, XVariable x){
		
		for(int i = 0; i < ApplicationGlobals.X_STEPS.length; i++){
			x.setXValue(ApplicationGlobals.X_STEPS[i]);
			
			double temp = fi.getUseCase();
			
			System.out.println("f("+ApplicationGlobals.X_STEPS[i]+") = "+temp);
		}
		
	}

}
