package engine.neuralnet.activations;

import engine.neuralnet.Activation;

public class ReLU implements Activation {

	@Override
	public float function(float x) {
		return Math.max(0, x);
	}

	@Override
	public float derivative(float x) {
		return x > 0? 1 : 0;
	}

}
