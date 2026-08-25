package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;
import engine.util.data.Serializable;

public class ReLU implements Activation {

	@Override
	public float function(float x) {
		return Math.max(0, x);
	}

	@Override
	public float derivative(float x) {
		return x > 0? 1 : 0;
	}
	
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Activation deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new ReLU();
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new ReLU[length];
	}

}
