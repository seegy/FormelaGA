package de.thm.mni.formelaGA;

import java.util.Random;

import de.thm.mni.formelaGA.items.AngleItem;
import de.thm.mni.formelaGA.items.Constant;
import de.thm.mni.formelaGA.items.LogItem;
import de.thm.mni.formelaGA.items.OneCompItem;
import de.thm.mni.formelaGA.items.PotenzItem;
import de.thm.mni.formelaGA.items.SqrtItem;
import de.thm.mni.formelaGA.items.StandartItem;
import de.thm.mni.formelaGA.items.TwoCompItem;

public final class ApplicationGlobals {

	public static String NAME = "PenisMaster 1337";
	
	public static double PM = 0.05;
	
	public static double CHANGE_PM = 0.1;
	
	public static double[] X_STEPS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	
	public static double CONST_PM = 0.5;
	
	public static XVariable X;
	
	
	
	
	//########### Tools ###############
	
	public static FormelItem randomMiddleItem(FormelItem fi){
		FormelItem result = null;
		FormelItem[] args = { null, null };
		
		if(fi instanceof OneCompItem){
			args[0] = ((OneCompItem)fi).getComp();
			args[1] = randomEndItem();
			
		} else if(fi instanceof TwoCompItem){
			args[0] = ((TwoCompItem)fi).getCompOne();
			args[1] = ((TwoCompItem)fi).getCompTwo();
			
		}
		
		int i = new Random().nextInt(2);
		
		switch(new Random().nextInt(5)){
		
		case 0:
			result = new AngleItem(args[0]);
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
			result = new SqrtItem(args[0]);
			break;
		}
		
		return result;
	}
	
	
	
	public static FormelItem randomEndItem(){
		
		FormelItem result = null;
		
		if(new Random().nextDouble() <= CONST_PM){
			result = new Constant(1);
			
		} else {
			
			result = X;
		}
		
		return result;
	}
}
