package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.TwoCompItem;

public class LogItem implements TwoCompItem {

	private FormelItem base;
	
	private FormelItem x;
	
	private int depth;
	
	public LogItem(FormelItem base, FormelItem x){
		this.base = base;
		this.x = x;
		depth = (base.getDepth() > x.getDepth()?base.getDepth():x.getDepth())+1;
	}
	
	
	@Override
	public String getWriteAble() {	
		return "log(B "+base.getWriteAble()+")("+x.getWriteAble()+")";
	}

	@Override
	public double getUseCase() {
		
		return Math.log10(x.getUseCase())/Math.log10(base.getUseCase());
	}

	
	@Override
	public FormelItem mutate() {
		FormelItem isNull = null;
		
		
		/**
		 *  Wenn der Inhalt keine Variable ist, Mutation weiter geben
		 */
		if( x.getDepth()>0){
			isNull = ((FormelPattern)x).mutate();
		}
		
		/** 
		 * wenn die Mutation des Inhaltes nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			x = ApplicationGlobals.randomMiddleItem(isNull);
		}
		
		/**
		 *  Wenn die Basis keine Variable ist, Mutation weiter geben
		 */
		if( base.getDepth()>0){
			isNull = ((FormelPattern)base).mutate();
		}
		
		/** 
		 * wenn die Mutation der Basis nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			base = ApplicationGlobals.randomMiddleItem(isNull);
		}
		
		/**
		 * Eigene Mutation, entscheidet auch zwischen innere und aeussere Mutation
		 */
		if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.PM){
			if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.CHANGE_PM){
				//aeussere Mutation
				return this;
			} else {
				//innere Mutation
				selfmutate();
			}
		}
		
		return null;
	}


	private void selfmutate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getDepth() {
		return depth;
	}


	@Override
	public FormelItem getCompOne() {
		return x;
	}


	@Override
	public FormelItem getCompTwo() {
		return base;
	}


	@Override
	public FormelItem getClone() {
		LogItem result = new LogItem(base.getClone(), x.getClone());
		result.depth = depth;
		return result;
	}

}
