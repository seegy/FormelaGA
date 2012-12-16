/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.FormelItem;
import de.thm.mni.formelaGA.items.FormelPattern;
import de.thm.mni.formelaGA.items.TwoCompItem;
import de.thm.mni.formelaGA.items.impls.stuff.StandartType;

/**
 * @author SeeGy
 *
 */
public class StandartItem implements TwoCompItem {

	
	private StandartType type;
	
	private FormelItem a;
	
	private FormelItem b;
	
	private int depth;
	
	public StandartItem(FormelItem a, FormelItem b){
		this.a = a;
		this.b = b;
		
		depth = (a.getDepth() > b.getDepth()?a.getDepth():b.getDepth())+1;
		
		Random r =  new Random();
		
		switch(r.nextInt(5)){
		
		case 0:
			type=StandartType.ADD;
			break;
		case 1:
			type=StandartType.DIV;
			break;
		case 2:
			type=StandartType.MOD;
			break;
		case 3:
			type=StandartType.MUL;
			break;
		case 4:			
			type=StandartType.SUB;
			break;
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getWriteAble()
	 */
	@Override
	public String getWriteAble() {
		StringBuilder sb = new StringBuilder("");
		
		sb.append("(");
		sb.append(a.getWriteAble());
		
		switch(type){
		case ADD:
			sb.append(" + ");
			break;
		case DIV:
			sb.append(" / ");
			break;
		case MOD:
			sb.append(" % ");
			break;
		case MUL:
			sb.append(" * ");
			break;
		case SUB:
			sb.append(" - ");
			break;
		default:
			System.err.println("falscher typ in einem ZweiKompItem");
		}
		
		sb.append(b.getWriteAble());
		sb.append(")");
		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelItem#getUseCase()
	 */
	@Override
	public double getUseCase() {
		
		switch(type){
		case ADD:
			return a.getUseCase() + b.getUseCase();
		case DIV:
			return a.getUseCase() / b.getUseCase();
		case MOD:
			return a.getUseCase() % b.getUseCase();
		case MUL:
			return a.getUseCase() * b.getUseCase();
		case SUB:
			return a.getUseCase() - b.getUseCase();
		default:
			System.err.println("falscher typ in einem ZweiKompItem");
		}
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see de.thm.mni.sgtz33.formeln.FormelPattern#mutate()
	 */
	@Override
	public FormelItem mutate() {
		FormelItem isNull = null;
		
		
		/**
		 *  Wenn a keine Variable ist, Mutation weiter geben
		 */
		if( a.getDepth()>0){
			isNull = ((FormelPattern)a).mutate();
		}
		
		/** 
		 * wenn die Mutation von a nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			a = ApplicationGlobals.randomMiddleItem(isNull);
		}
		
		/**
		 *  Wenn b keine Variable ist, Mutation weiter geben
		 */
		if( b.getDepth()>0){
			isNull = ((FormelPattern)b).mutate();
		}
		
		/** 
		 * wenn die Mutation von b nicht Null zurückgickt, 
		 * wird es verändert durch durch die static-Methode
		*/
		if(isNull != null){
			b = ApplicationGlobals.randomMiddleItem(isNull);
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
		return a;
	}


	@Override
	public FormelItem getCompTwo() {
		return b;
	}


	@Override
	public FormelItem getClone() {
		StandartItem result = new StandartItem(a.getClone(), b.getClone());
		result.type = type;
		result.depth = depth;
		return result;
	}

	
}
