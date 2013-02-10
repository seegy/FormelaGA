/**
 * 
 */
package de.thm.mni.formelaGA.items.impls;

import java.util.Random;

import de.thm.mni.formelaGA.ApplicationGlobals;
import de.thm.mni.formelaGA.items.FormelItem;
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
		
		setDepth();
		
		Random r =  ApplicationGlobals.randomer;
		
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
	
	private void setDepth(){
		depth = (a.getDepth() < b.getDepth()?a.getDepth():b.getDepth())+1;
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
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				a = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht
				a = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
			}
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
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				b = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht
				b = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
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
						a = ApplicationGlobals.newMiddleFormelPattern(a);
					} else {
						b = ApplicationGlobals.newMiddleFormelPattern(b);
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

	@Override
	public FormelItem getXOPart(int n) {

		if(n == 0){
			return this;
		} 
		
		if(ApplicationGlobals.randomer.nextInt(2) == 1){
			return a.getXOPart(n-1);
		}
		return b.getXOPart(n-1);
	}
	
	@Override
	public void setXOValue(FormelItem xoPart) {
		
		if(ApplicationGlobals.randomer.nextInt(2) == 1){
			a = xoPart;
		} else {
			b = xoPart;
		}
		setDepth();
	}
	
}
