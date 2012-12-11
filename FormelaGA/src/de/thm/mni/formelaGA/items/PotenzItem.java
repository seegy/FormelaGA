/**
 * 
 */
package de.thm.mni.formelaGA.items;

import de.thm.mni.formelaGA.FormelItem;

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
		// TODO Auto-generated method stub
		return null;
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
