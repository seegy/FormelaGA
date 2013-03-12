package de.thm.mni.formelaGA;

public interface IOChannel {

	
	public void sayHello();
	
	public void showFitness(String fitness);
	
	public void showFormula(String formula);
	
	public void log(String text);
	
	public void showGeneration(long gener);
	
}
