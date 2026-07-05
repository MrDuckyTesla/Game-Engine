package engine.neural.optimizers;

import engine.neural.*;

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
	public String getClassInfo() {
		return this.getClass().getName() + "\n" + this.learningRate;
	}

}
