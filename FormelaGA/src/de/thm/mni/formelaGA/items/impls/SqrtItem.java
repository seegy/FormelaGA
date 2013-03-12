/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.items.FormelItem;
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
		setDepth();
		
		
		
		
	}
	
	
	private void setDepth(){
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
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				value = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht
				value = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
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
					value = ApplicationGlobals.newMiddleFormelPattern(value);
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
	public FormelItem getComp() {
		return value;
	}


	@Override
	public FormelItem getClone() {
		SqrtItem result = new SqrtItem(value.getClone());
		result.depth = depth; 
		return result;
	}
	
	@Override
	public FormelItem getXOPart(int n) {
		if(n == 0){
			return this;
		}  
		return value.getXOPart(n-1);
	}

	@Override
	public void setXOValue(FormelItem xoPart) {
		value = xoPart;
		setDepth();
	}


	@Override
	public int getItemCount() {
		return 1+value.getItemCount();
	}
}
