package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;

public class Linear implements Activation {

	@Override
	public float function(float x) {
		return x;
	}

	@Override
	public float derivative(float x) {
		return 1;
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

}
