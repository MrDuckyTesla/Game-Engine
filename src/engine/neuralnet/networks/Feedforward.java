package engine.neuralnet.networks;

import engine.neuralnet.Activation;
import engine.neuralnet.Matrix;
import engine.neuralnet.Network;
import engine.neuralnet.Vector;
import engine.neuralnet.activations.*;

public class Feedforward implements Network {
	
	private int[] networkSizes;
	private Activation activation;
	private Vector[] biases, activations, preActivations;
	private Matrix[] weights;
	
	public Feedforward(int[] networkSizes, Activation activation) {
		if (networkSizes.length < 3) {throw new IllegalArgumentException();}
		this.networkSizes = networkSizes; this.activation = activation;
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
	private Vector forward(Vector input) {
		if (input.getHgt() == weights[0].getHgt()) {
			for (int i = 0; i < this.weights.length; i++) {
				input = (Vector) this.weights[i].multiply(input);
				input.addMatrix(this.biases[i].getMatrix());
				for (int j = 0; j < input.getHgt(); j++) {
					this.preActivations[i] = input;
					input.set(j, this.activation.function(input.get(j)));
				}
			}
		} return input;
	}
	
	private void backward(Vector expected, Vector input) {
		
	}
	
	@Override
	public void train(Vector[] inputs, Vector[] expected, int epochs) {
		for (int i = 0; i < epochs; i++) {
			this.forward(inputs[i]);
		}
	}
	
	@Override
	public void step(Vector input, Vector expected) {
		
	}
	
	@Override
	public Vector predict(Vector input) {
		return null;
	}
	
	@Override
	public float getCost() {
		return-1;
	}
	
	@Override
	public void loadNetwork(String name) {
		
	}
	
	@Override
	public void saveNetwork(String name) {
		
	}
}
