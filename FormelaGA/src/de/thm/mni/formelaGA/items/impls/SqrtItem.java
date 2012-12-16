/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.OneCompItem;

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
		
		
		/**
		 *  Wenn der Inhalt keine Variable ist, Mutation weiter geben
		 */
		if( value.getDepth()>0){
			isNull = ((FormelPattern)value).mutate();
		}
		
		/** 
		 * wenn die Mutation des Inhaltes nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			value = ApplicationGlobals.randomMiddleItem(isNull);
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
	public FormelItem getComp() {
		return value;
	}

}
