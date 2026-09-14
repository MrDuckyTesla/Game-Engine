package engine.neural.networks;

import java.util.Random;

import engine.neural.*;

import engine.neural.util.Matrix;
import engine.neural.util.Vector;

public class Policy<T> extends Feedforward {
	
	private RewardFunction<T> rewardFun;
	private Vector noise;
	private Random rand = new Random();
	private float reward;

	public Policy(int[] networkSizes, Initializer initializer, Activation activation, Cost cost, Optimizer optimizer, RewardFunction<T> reward) {
		super(networkSizes, initializer, activation, cost, optimizer);
		noise = new Vector(networkSizes[networkSizes.length-1]);
		this.rewardFun = reward;
	}
	
	@FunctionalInterface
	public interface RewardFunction<T> {
		float calculate(T[] state, Object...args);
	}
	
	@FunctionalInterface
	public interface Simulation<T> {
		T[] simulate(Vector noise);
	}
	
	public void correct() {
		Vector delta = this.noise.copy();
		delta.scaleMatrix(-reward);
		// Element wise multiplication of the activation derivative of last preactivation
		for (int i = 0; i < delta.getHgt(); i++) {
			delta.scale(i, this.activation.derivative(this.preActivations[this.weights.length-1].get(i)));
		} Matrix gradient, weight;  // Store gradient and weight before change
		// Loop though weights backwards
		for (int i = this.weights.length-1; i >= 0 ; i--) {
			// Get gradient of current layer (delta * activations Transpose)
			gradient = delta.multiply(this.activations[i].getTranspose());
			// Store weights before changing them in update
			weight = this.weights[i].copy();
			// Update weights and biases
			this.optimizer.updateWeights(this.weights[i], gradient);
			this.optimizer.updateBiases(this.biases[i], delta);
			// make sure not updating input layer
			if (i != 0) {  // Multiply delta by weights (weight Transpose * delta)
				delta = weight.getTranspose().multiply(delta);
				for (int j = 0; j < delta.getHgt(); j++) {
					// Multiply delta by activation derivative of last preactivation
					delta.scale(j, this.activation.derivative(this.preActivations[i-1].get(j)));
				}
			}
		}
	}
	
	public  T[] computeBestNoise(int num, Simulation<T> s, Object...args) {
		T[] stateOfBestSim = null;
		Vector bestNoise = null;
		float bestReward = Float.NEGATIVE_INFINITY;
		
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < this.noise.getLength(); j++) {
				this.noise.set(j, (float) this.rand.nextGaussian()/50);
			} T[] currSim = (T[]) s.simulate(this.noise);
			float currReward = this.rewardFun.calculate(currSim, args);
			if (currReward > bestReward) {
				stateOfBestSim = currSim; bestReward = currReward;
				bestNoise = new Vector(this.noise.getMatrix());
			}
		} this.noise = bestNoise; this.reward = bestReward;
		return stateOfBestSim;
	}
	
	public Vector getNoise() {return this.noise;}

}
