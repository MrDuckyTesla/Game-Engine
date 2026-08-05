package engine.neural.optimizers;

import engine.data.ByteHelper;
import engine.neural.Matrix;
import engine.neural.Optimizer;
import engine.neural.Vector;

public class AdamW implements Optimizer {

	public AdamW() {
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
		return new AdamW();
	}

}
