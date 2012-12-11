package de.thm.mni.formelaGA.items;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;


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
		
		if(new Random().nextDouble() <= ApplicationGlobals.PM)
			switch(new Random().nextInt(7)){
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

	

}
