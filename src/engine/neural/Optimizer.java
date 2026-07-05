package engine.neural;

import engine.util.Reconstructible;

public interface Optimizer extends Reconstructible {
	
	public abstract void updateWeights(Matrix weights, Matrix gradient);
	
	public abstract void updateBiases(Vector biases, Vector delta);
	
}
