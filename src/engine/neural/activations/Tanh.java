package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;

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
	public Activation deserialize(ByteHelper bytes) {
		return new Tanh();
	}

}
