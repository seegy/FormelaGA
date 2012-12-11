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
public class AngleItem implements OneCompItem {

	private AngleType type;
	private FormelItem value;
	
	private int depth;
	
	public AngleItem(FormelItem value){
		this.value = value;
		this.depth = value.getDepth()+1;
		
		Random r = new Random();
		
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
		
		if( value.getDepth()>0){
			isNull = ((FormelPattern)value).mutate();
		}
		
		if(isNull != null){
			value = ApplicationGlobals.randomMiddleItem(isNull);
		}
		
		if(new Random().nextDouble() <= ApplicationGlobals.PM){
			if(new Random().nextDouble() <= ApplicationGlobals.CHANGE_PM){
				return this;
			} else {
				selfmutate();
			}
		}
		
		return null;
	}
	
	private void selfmutate(){
	
		AngleType temptype = null;
		
		do{
			switch(new Random().nextInt(6)){
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

}
