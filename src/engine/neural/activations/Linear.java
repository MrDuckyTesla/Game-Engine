package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;
import engine.util.data.Serializable;

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
	public Activation deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new Linear();
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Linear[length];
	}

}
