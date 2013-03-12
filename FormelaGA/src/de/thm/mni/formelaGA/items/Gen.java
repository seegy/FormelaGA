package de.thm.mni.formelaGA.items;

import de.thm.mni.formelaGA.ApplicationGlobals;

public class Gen {

	private double fitness;
	
	private FormelPattern formelPattern;
	
	public Gen (){}
	
	public Gen(FormelPattern formelPattern){
		this.formelPattern = formelPattern;
	}

	public double getFitness() {
		if(ApplicationGlobals.DOUBLE_COMPARE){
			return fitness - formelPattern.getItemCount() * ApplicationGlobals.DOUBLE_COMPARE_RELEVANCE;
		} else {
		return fitness;
		}
	}
	
	public double getNonDcFitness(){
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
	
	public void mutate(){
		FormelItem isNull = null;
		
		
		if(formelPattern.getDepth() >1){
			isNull = formelPattern.mutate();
		}
		
		if(isNull != null){
			switch(ApplicationGlobals.randomer.nextInt(2)){
			case 0:
				//item wird geändert
				formelPattern = ApplicationGlobals.changeFormelItem(isNull);
				break;
			case 1:
				//zwischenitem wird gelöscht sofern es 
				if(isNull instanceof FormelPattern){
					Object test = ApplicationGlobals.removeFormelPattern((FormelPattern) isNull);
					
					if(test instanceof FormelPattern){
						formelPattern = (FormelPattern)test;
					}
				}
					
			}
		}
		
		if(ApplicationGlobals.randomer.nextDouble() <= ApplicationGlobals.PM){
			formelPattern = ApplicationGlobals.newMiddleFormelPattern(formelPattern);
		}
	}
	
	public Gen clone(){
		return new Gen((FormelPattern) formelPattern.getClone());
	}
	
}
