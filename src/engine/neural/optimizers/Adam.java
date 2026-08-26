package engine.neural.optimizers;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
import engine.neural.Optimizer;
import engine.neural.util.Matrix;
import engine.neural.util.Vector;

public class Adam implements Optimizer {

	public Adam() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateWeights(Matrix weights, Matrix gradient) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateBiases(Vector biases, Vector delta) {
		// TODO Auto-generated method stub
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Optimizer deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optimizer[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
