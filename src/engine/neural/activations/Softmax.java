package engine.neural.activations;

import engine.data.ByteHelper;
import engine.neural.Activation;

public class Softmax implements Activation {

	@Override
	public float function(float x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float derivative(float x) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Activation deserialize(ByteHelper bytes) {
		return new Softmax();
	}

}
