package engine.neural.optimizers;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
import engine.neural.*;
import engine.neural.util.Matrix;
import engine.neural.util.Vector;

public class SGD implements Optimizer {
	
	private final float learningRate;

	public SGD(float learningRate) {
		this.learningRate = learningRate;
	}

	@Override
	public void updateWeights(Matrix weights, Matrix gradient) {
		weights.subMatrix(gradient.scaleMatrixReturn(this.learningRate).getMatrix());
	}

	@Override
	public void updateBiases(Vector biases, Vector delta) {
		biases.subMatrix(delta.scaleMatrixReturn(this.learningRate).getMatrix());
	}

	@Override
	public byte[] serialize() {
		return ByteHelper.toBytes(this.learningRate);
	}

	@Override
	public Optimizer deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new SGD( 
			b.readFloat()
		);
	}

	@Override
	public Optimizer[] getProtoArray(int length) {
		return new SGD[length];
	}

}
