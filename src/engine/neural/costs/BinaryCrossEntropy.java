package engine.neural.costs;

import engine.neural.Cost;
import engine.util.ByteHelper;
import engine.util.Vector;

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
	public Cost deserialize(ByteHelper bytes) {
		// TODO Auto-generated method stub
		return new BinaryCrossEntropy();
	}

}
