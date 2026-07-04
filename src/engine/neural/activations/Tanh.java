package engine.neural.activations;

import engine.neural.Activation;

public class Tanh implements Activation {

	@Override
	public float function(float x) {
		return (float) Math.tanh(x);
	}

	@Override
	public float derivative(float x) {
		float tan = (float) Math.tanh(x);
		return 1 - tan*tan;
	}

}
