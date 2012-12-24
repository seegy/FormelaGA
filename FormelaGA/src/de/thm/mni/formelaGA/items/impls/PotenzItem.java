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
		setDepth();
	}
	
	private void setDepth(){
		depth = (base.getDepth() < exponent.getDepth()?base.getDepth():exponent.getDepth())+1;
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
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				exponent = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht
				exponent = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
			}
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
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				base = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht
				base = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
			}
			setDepth();
		}
		
		/**
		 * Eigene Mutation, entscheidet auch zwischen innere und aeussere Mutation
		 */
		if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.PM){
			if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.CHANGE_PM){
				//aeussere Mutation
				setDepth();
				return this;
			} else {
				//innere Mutation
				switch(ApplicationGlobals.randomer.nextInt(2)){
				
				case 0: 
					selfmutate();
					break;
				case 1:
					
					if(ApplicationGlobals.randomer.nextInt(2) == 0){
						exponent = ApplicationGlobals.newMiddleFormelPattern(exponent);
					} else {
						base = ApplicationGlobals.newMiddleFormelPattern(base);
					}
						break;
				}
			}
		}
		setDepth();
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


	@Override
	public FormelItem getClone() {
		PotenzItem result = new PotenzItem(base.getClone(), exponent.getClone());
		result.depth = depth;
		return result;
	}
	
	@Override
	public FormelItem getXOPart(int n) {

		if(n == 0){
			return this;
		} 
		
		if(ApplicationGlobals.randomer.nextInt(2) == 1){
			return exponent.getXOPart(n-1);
		}
		return base.getXOPart(n-1);
	}
	
	@Override
	public void setXOValue(FormelItem xoPart) {
		
		if(ApplicationGlobals.randomer.nextInt(2) == 1){
			exponent = xoPart;
		} else {
			base = xoPart;
		}
		setDepth();
	}

}
