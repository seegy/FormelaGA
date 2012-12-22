/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.OneCompItem;
import de.thm.mni.formelaGA.items.impls.stuff.AngleType;

/**
 * @author SeeGy
 *
 */
public class AngleItem implements OneCompItem {

	private AngleType type;
	private FormelItem value;
	
	private int depth;
	
	public AngleItem(FormelItem value){
		this.value = value;
		this.depth = value.getDepth()+1;
		
		Random r = ApplicationGlobals.randomer;
		
		switch(r.nextInt(6)){
		case 0:
			type = AngleType.ARCCOS;
			break;
		case 1:
			type = AngleType.ARCSIN;
			break;
		case 2:
			type = AngleType.ARCTAN;
			break;
		case 3:
			type = AngleType.COS;
			break;
		case 4:
			type = AngleType.SIN;
			break;
		case 5:
			type = AngleType.TAN;
			break;
		
		}
		
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getWriteAble()
	 */
	@Override
	public String getWriteAble() {
		StringBuilder sb = new StringBuilder("");
		
		switch(type){
		case ARCCOS:
			sb.append("arccos(");
			break;
		case ARCSIN:
			sb.append("arcsin(");
			break;
		case ARCTAN:
			sb.append("arctan(");
			break;
		case COS:
			sb.append("cos(");
			break;
		case SIN:
			sb.append("sin(");
			break;
		case TAN:
			sb.append("tan(");
			break;		
		default: 
			System.err.println("Wrong Type in AngleItem");
		}
		sb.append(value.getWriteAble());
		
		sb.append(")");
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getUseCase()
	 */
	@Override
	public double getUseCase() {
		double result =0;
		
		switch(type){
		case ARCCOS:
			result = Math.acos(value.getUseCase());
			break;
		case ARCSIN:
			result = Math.asin(value.getUseCase());
			break;
		case ARCTAN:
			result = Math.atan(value.getUseCase());
			break;
		case COS:
			result = Math.cos(value.getUseCase());
			break;
		case SIN:
			result = Math.sin(value.getUseCase());
			break;
		case TAN:
			result = Math.tan(value.getUseCase());
			break;		
		default: 
			System.err.println("Wrong Type in AngleItem");
		}
		
		return result;
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
		 * Aeussere Mutation des inneren Items
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
		
		return null;
	}
	
	private void selfmutate(){
	
		AngleType temptype = null;
		
		do{
			switch(ApplicationGlobals.randomer.nextInt(6)){
			case 0:
				temptype = AngleType.ARCCOS;
				break;
			case 1:
				temptype = AngleType.ARCSIN;
				break;
			case 2:
				temptype = AngleType.ARCTAN;
				break;
			case 3:
				temptype = AngleType.COS;
				break;
			case 4:
				temptype = AngleType.SIN;
				break;
			case 5:
				temptype = AngleType.TAN;
				break;
			}
		} while(temptype == type);
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
		AngleItem result = new AngleItem(value.getClone());
		result.type = type;
		result.depth = depth;
		return result;
	}

}
