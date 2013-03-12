package de.thm.mni.formelaGA.items;



public interface FormelPattern extends FormelItem {

	
	public FormelItem mutate();

	public void setXOValue(FormelItem xoPart);
	
	
}
