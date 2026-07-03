package engine.neuralnet.activations;

import engine.neuralnet.Activation;

public class Linear implements Activation {

	@Override
	public float function(float x) {
		return x;
	}

	@Override
	public float derivative(float x) {
		return 1;
	}

}
