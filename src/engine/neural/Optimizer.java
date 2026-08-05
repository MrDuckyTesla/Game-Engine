package engine.neural;

import engine.data.Serializable;

public interface Optimizer extends Serializable<Optimizer> {
	
	public abstract void updateWeights(Matrix weights, Matrix gradient);
	
	public abstract void updateBiases(Vector biases, Vector delta);
	
}
