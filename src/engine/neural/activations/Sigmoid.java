package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;
import engine.util.data.Serializable;

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
	
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Activation deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new Sigmoid();
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Sigmoid[length];
	}

}
