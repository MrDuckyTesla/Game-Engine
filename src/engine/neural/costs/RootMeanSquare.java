package engine.neural.costs;

import engine.neural.Cost;
import engine.util.ByteHelper;
import engine.util.Vector;
import engine.util.data.Serializable;

public class RootMeanSquare implements Cost {

	public RootMeanSquare() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float calculate(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Vector derivative(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Cost deserialize(ByteHelper b, Serializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cost[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
