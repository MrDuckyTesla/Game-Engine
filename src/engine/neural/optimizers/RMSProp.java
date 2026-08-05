package engine.neural.optimizers;

import engine.neural.*;

import engine.data.ByteHelper;

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
	public Optimizer deserialize(ByteHelper bytes) {
		return new RMSProp();
	}

}
