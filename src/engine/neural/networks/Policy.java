package engine.neural.networks;

import java.util.Random;

import engine.neural.*;

import engine.neural.util.Matrix;
import engine.neural.util.Vector;

import engine.neural.networks.Policy.Simulation.SimulationResult;

public class Policy<T> extends Feedforward {
	
	private RewardFunction<T> rewardFun;
	private Vector noise, lastPrediction;
	private Random rand = new Random();
	private float reward, avgReward = 0;

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
		SimulationResult<T> simulate(Vector noise, Object...args);
		
		public static class SimulationResult<T> {
			public final T[] sim;
			public final Object[] arg;
			
			public SimulationResult(T[] sim, Object...args) {
				this.sim = sim; this.arg = args;
			}
		}
	}
	
	public void correct() {
		
		Vector delta = this.lastPrediction.copy();
		delta.addMatrix(this.noise.getMatrix());
		delta.addMatrix(this.activations[this.activations.length - 1].copy().negate().getMatrix());
		
//		this.avgReward = 0.99f*this.avgReward + 0.01f*reward;
//		delta.scaleMatrix(-(reward - this.avgReward));
		
//		delta.scaleMatrix(-reward);
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
	
	public  T[] computeBestNoise(int num, Vector prediction, Simulation<T> s, Object...args) {
		
		Vector bestNoise = new Vector(this.noise.getMatrix());
		
		this.lastPrediction = prediction.copy();

		SimulationResult<T> bestResult = s.simulate(bestNoise, args);
		float bestReward = this.rewardFun.calculate(bestResult.sim, bestResult.arg);

		T[] stateOfBestSim = bestResult.sim;
		
		for (int i = 0; i < num; i++) {
			Vector candidate = new Vector(bestNoise.getMatrix());
			for (int j = 0; j < candidate.getLength(); j++) {
				candidate.set(j, Math.max(-1, Math.min(1, candidate.get(j) + (float) this.rand.nextGaussian() * 0.1f)));
			}
			SimulationResult<T> result = s.simulate(candidate, args);
			float currReward = this.rewardFun.calculate(result.sim, result.arg);
			if (currReward > bestReward) {
				stateOfBestSim = result.sim; bestReward = currReward;
				bestNoise = new Vector(candidate.getMatrix());
			}
//			if (-currReward > bestReward) {
//				stateOfBestSim = result.sim; bestReward = -currReward;
//				bestNoise = new Vector(this.noise.getMatrix()); bestNoise.negate();
//			}
		} this.noise = bestNoise; this.reward = bestReward;
		return stateOfBestSim;
	}
	
	public Vector getNoise() {return this.noise;}

}
