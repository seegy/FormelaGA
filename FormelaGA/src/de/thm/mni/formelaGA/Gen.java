package de.thm.mni.formelaGA;

import de.thm.mni.formelaGA.items.FormelPattern;

public class Gen {

	private double fitness;
	
	private FormelPattern formelPattern;
	
	public Gen (){}
	
	public Gen(FormelPattern formelPattern){
		this.formelPattern = formelPattern;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public FormelPattern getFormelPattern() {
		return formelPattern;
	}

	public void setFormelPattern(FormelPattern formelPattern) {
		this.formelPattern = formelPattern;
	}
	
	
}
