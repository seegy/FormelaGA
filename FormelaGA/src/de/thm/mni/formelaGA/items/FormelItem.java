package de.thm.mni.formelaGA.items;
public interface FormelItem {

	public String getWriteAble();
	
	public double getUseCase();
	
	public int getDepth();
	
	public FormelItem getClone();
	
	public FormelItem getXOPart(int n);
	
	public int getItemCount();

}
