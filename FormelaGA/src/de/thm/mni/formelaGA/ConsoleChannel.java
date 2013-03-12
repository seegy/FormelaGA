package de.thm.mni.formelaGA;

public class ConsoleChannel implements IOChannel {

	@Override
	public void sayHello() {
		System.out.println("welcome to " +ApplicationGlobals.NAME);	
	}

	@Override
	public void showFitness(String fitness) {
		System.out.println("Beste Fitness: "+fitness);
	}

	@Override
	public void showFormula(String formula) {
		System.out.println("f(x) = "+formula);
	}

	@Override
	public void log(String text) {
		System.out.println(text);
	}

	@Override
	public void showGeneration(long gener) {
		System.out.println("Generation: "+gener);
	}

	
	
}
