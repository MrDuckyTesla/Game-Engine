package engine.neural.optimizers;

import java.util.*;

import engine.neural.*;
import engine.util.*;
import engine.util.Vector;


public class SGDMomentum implements Optimizer {
	
	private final float learningRate, momentum;
	
	/**
	 * Store velocity matrix based off of which matrix is given
	 */
	private IdentityHashMap<Matrix, Matrix> velocityWeights = new IdentityHashMap<>();
	/**
	 * Store velocity matrix based off of which vector is given
	 */
	private IdentityHashMap<Vector, Vector> velocityBiases = new IdentityHashMap<>();

	public SGDMomentum(float learningRate, float momentum) {
		this.learningRate = learningRate;
		this.momentum = momentum;
	}

	@Override
	// momentum * velocity - learningRate * gradient
	public void updateWeights(Matrix weights, Matrix gradient) {
		// Adds old weights if they exist or makes new ones
		 Matrix velocity = this.velocityWeights.computeIfAbsent(
			weights, m -> new Matrix(weights.getWid(), weights.getHgt())
		); // Scale velocity matrix by momentum
		velocity.scaleMatrix(this.momentum);
		// Scale gradient by learning rate
		Matrix temp = gradient.copy();
		temp.scaleMatrix(this.learningRate);
		// Subtract gradient from velocity
		velocity.subMatrix(temp.getMatrix());
		// Add velocity to weights
		weights.addMatrix(velocity.getMatrix());
	}

	@Override
	// momentum * velocity - learningRate * delta
	public void updateBiases(Vector biases, Vector delta) {
		Vector velocity = this.velocityBiases.computeIfAbsent(
			biases, v -> new Vector(biases.getHgt())
		); // Scale velocity vector by momentum
		velocity.scaleMatrix(this.momentum);
		// Scale delta by learning rate
		Vector temp = delta.copy();
		temp.scaleMatrix(this.learningRate);
		// Subtract delta from velocity
		velocity.subMatrix(temp.getMatrix());
		// Add velocity to biases
		biases.addMatrix(velocity.getMatrix());
	}

	@Override
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.learningRate),
			ByteHelper.toBytes(this.momentum),
			this.toBytes(this.velocityWeights, Matrix.class),
			this.toBytes(this.velocityBiases, Vector.class)
		);
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Matrix> byte[] toBytes(IdentityHashMap<T, T> i, Class<T> type) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, i.size());
		int j = 0;
		for (Map.Entry<T, T> entry : i.entrySet()) {
			a[j++] = entry.getValue();
		} return ByteHelper.toBytes(a); 
	}

}

