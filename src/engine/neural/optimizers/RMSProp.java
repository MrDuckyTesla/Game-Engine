package engine.neural.optimizers;

import engine.neural.*;
import engine.util.ByteHelper;
import engine.util.Matrix;
import engine.util.Vector;
import engine.util.data.Serializable;

public class RMSProp implements Optimizer {

	public RMSProp() {
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
	public Optimizer deserialize(ByteHelper b, Serializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optimizer[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
