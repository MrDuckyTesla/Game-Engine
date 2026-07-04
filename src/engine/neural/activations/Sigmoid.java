package engine.neural.activations;

import engine.neural.Activation;

public class Sigmoid implements Activation {

	@Override
	public float function(float x) {
		return 1 / (float) (1 + Math.exp(-x));
	}

	@Override
	public float derivative(float x) {
		float sig = this.function(x);
		return sig * (1 - sig);
	}

}
