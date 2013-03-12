package de.thm.mni.formelaGA;


public class Controller {

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		IOChannel ioChannel = new ConsoleChannel();
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(ioChannel);
		
		geneticAlgorithm.run();
		
	}

	
}
