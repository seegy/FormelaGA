/**
 * 
 */
package de.thm.mni.formelaGA.items;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;

/**
 * @author SeeGy
 *
 */
public class SqrtItem implements OneCompItem {

	private FormelItem value;
	
	private int depth;
	
	public SqrtItem(FormelItem value){
		this.value = value;
		this.depth = value.getDepth()+1;
		
		
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getWriteAble()
	 */
	@Override
	public String getWriteAble() {
		StringBuilder sb = new StringBuilder("");
		sb.append(" sqrt("+value.getWriteAble()+") ");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getUseCase()
	 */
	@Override
	public double getUseCase() {
		return Math.sqrt(value.getUseCase());
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelPattern#mutate()
	 */
	@Override
	public FormelItem mutate() {
		FormelItem isNull = null;
		
		if( value.getDepth()>0){
			isNull = ((FormelPattern)value).mutate();
		}
		
		if(isNull != null){
			if(isNull instanceof OneCompItem){
				
			}
		}
		
		if(new Random().nextDouble() <= ApplicationGlobals.PM){
				return this;
		}
		
		return null;
	}
	
	


	@Override
	public int getDepth() {
		return depth;
	}


	@Override
	public FormelItem getComp() {
		return value;
	}

}
