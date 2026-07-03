package engine.neuralnet;

public interface Optimizer {
	
	public abstract Matrix updateWeights();
	
	public abstract Matrix updateBiases();
	
}
