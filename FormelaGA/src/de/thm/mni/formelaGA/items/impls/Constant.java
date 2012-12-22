package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;


public class Constant implements FormelPattern{

	
	private double value;
	
	public Constant(double value){
		this.value = value;
	}
	
	
	@Override
	public String getWriteAble() {
		return ""+value;
	}

	@Override
	public double getUseCase() {
		return value;
	}


	@Override
	public FormelItem mutate() {
		
		if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.PM)
			switch(ApplicationGlobals.randomer.nextInt(7)){
			case 0:value++;
			break;
			case 1:value--;
			break;
			case 2:value*= -1;
			break;
			case 3:value*= 2;
			break;
			case 4:value/= 2;
			break;
			case 5:value+=Double.MIN_VALUE;
			break;
			case 6:value-=Double.MIN_VALUE;
			}
		
		return null;
	}


	@Override
	public int getDepth() {
		return 1;
	}


	@Override
	public FormelItem getClone() {
		return new Constant(value);
	}

	

}
