package engine.neural.activations;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
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
	public Activation deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Activation[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
