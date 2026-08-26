package engine.neural.costs;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
import engine.neural.Cost;
import engine.neural.util.Vector;

public class BinaryCrossEntropy implements Cost {

	@Override
	// -(actual * log(prediction) + (1 - actual) * log(1 - prediction))
	public float calculate(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	// prediction - actual
	public Vector derivative(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		return new byte[] {};
	}

	@Override
	public Cost deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cost[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}
}
