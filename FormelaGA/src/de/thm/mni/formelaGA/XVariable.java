package de.thm.mni.formelaGA;


public class XVariable implements FormelItem {

	private double value;
	

	@Override
	public String getWriteAble() {
		return "x";
	}

	@Override
	public double getUseCase() {
		return value;
	}
	
	public void setXValue( double value){
		this.value = value;
	}

	@Override
	public int getDepth() {
		return 0;
	}

	@Override
	public FormelItem getClone() {
		return this;
	}

	@Override
	public FormelItem getXOPart(int n) {
		return this;
	}
	

}
