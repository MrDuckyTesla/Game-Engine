package engine.neural;

import engine.data.serializations.FastSerializable;
import engine.neural.util.Matrix;
import engine.neural.util.Vector;

public interface Optimizer extends FastSerializable<Optimizer> {
	
	public abstract void updateWeights(Matrix weights, Matrix gradient);
	
	public abstract void updateBiases(Vector biases, Vector delta);
	
}
