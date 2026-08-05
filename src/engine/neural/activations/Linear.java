package engine.neural.activations;

import engine.data.ByteHelper;
import engine.neural.Activation;

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

	@Override
	public Activation deserialize(ByteHelper bytes) {
		return new Linear();
	}

}
