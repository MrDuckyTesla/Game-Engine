package engine.neural;

import engine.util.Reconstructable;

public interface Optimizer extends Reconstructable {
	
	public abstract void updateWeights(Matrix weights, Matrix gradient);
	
	public abstract void updateBiases(Vector biases, Vector delta);
	
}
