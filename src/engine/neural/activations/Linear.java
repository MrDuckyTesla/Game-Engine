package engine.neural.activations;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
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
	public Activation deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new Linear();
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Linear[length];
	}

}
