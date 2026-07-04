package engine.neuralnet.initializers;

import engine.neuralnet.Initializer;
import engine.neuralnet.Matrix;

public class SymmetricUniform implements Initializer {
	
	private final float num;

	public SymmetricUniform(float num) {
		this.num = num;
	}

	@Override
	public void initialize(Matrix weights) {
		weights.propagate(this.num);
	}

}
