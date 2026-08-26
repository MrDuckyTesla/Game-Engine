package engine.neural.activations;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
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
	
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Activation deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new Tanh();
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Tanh[length];
	}

}
