package engine.util.neuralnet.networks;

import engine.util.neuralnet.Matrix;
import engine.util.neuralnet.Vector;
import engine.util.neuralnet.activations.*;

public class Network {
	
	private int[] networkSizes;
	private Vector[] biases, activations, preActivations;
	private Matrix[] weights;
	
	public Network(int[] networkSizes) {
		if (networkSizes.length < 3) {throw new IllegalArgumentException();}
		this.networkSizes = networkSizes;
		this.activations = new Vector[this.networkSizes.length];
		this.biases = new Vector[this.networkSizes.length-1];
		this.preActivations = new Vector[this.networkSizes.length-1];
		this.weights = new Matrix[this.networkSizes.length-1];
		for (int i = 1; i < this.networkSizes.length; i++) {
			this.weights[i-1] = new Matrix(this.networkSizes[i-1], this.networkSizes[i]);
			this.biases[i-1] = new Vector(this.networkSizes[i]);
			this.weights[i-1].propagate();
		}
	}
	
	// new = W*i + b
	private Vector forward(Vector input, AbstractActivation activation) {
		if (input.getHgt() == weights[0].getHgt()) {
			for (int i = 0; i < this.weights.length; i++) {
				input = (Vector) this.weights[i].multiply(input);
				input.addMatrix(this.biases[i].getMatrix());
				for (int j = 0; j < input.getHgt(); j++) {
					this.preActivations[i] = input;
					input.set(j, activation.function(input.get(j)));
				}
			}
		} return input;
	}
	
	private void backward() {
		
	}
	
	public void train() {
		
	}
	
	public Vector getPrediction(Vector input) {
		return null;
	}
	
	public void loadWeights() {
		
	}
	
	public void saveWeightd() {
		
	}
}
