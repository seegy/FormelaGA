/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.TwoCompItem;

/**
 * @author SeeGy
 *
 */
public class PotenzItem implements TwoCompItem {

	private FormelItem base;
	
	private FormelItem exponent;
	
	private int depth;
	
	
	public PotenzItem(FormelItem base, FormelItem exponent){
		this.base = base;
		this.exponent = exponent;
		depth = (base.getDepth() > exponent.getDepth()?
				base.getDepth():exponent.getDepth())+1;
	}
	
	
	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getWriteAble()
	 */
	@Override
	public String getWriteAble() {
		return "("+base.getWriteAble()+")^("+exponent.getWriteAble()+")";
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getUseCase()
	 */
	@Override
	public double getUseCase() {
		
		return Math.pow(base.getUseCase(), exponent.getUseCase());
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelPattern#mutate()
	 */
	@Override
	public FormelItem mutate() {
		FormelItem isNull = null;
		
		
		/**
		 *  Wenn der Exponent keine Variable ist, Mutation weiter geben
		 */
		if( exponent.getDepth()>0){
			isNull = ((FormelPattern)exponent).mutate();
		}
		
		/** 
		 * wenn die Mutation des Exponenten nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			exponent = ApplicationGlobals.randomMiddleItem(isNull);
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
		if(new Random().nextDouble() <= ApplicationGlobals.PM){
			if(new Random().nextDouble() <= ApplicationGlobals.CHANGE_PM){
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
		return base;
	}


	@Override
	public FormelItem getCompTwo() {
		return exponent;
	}

}
