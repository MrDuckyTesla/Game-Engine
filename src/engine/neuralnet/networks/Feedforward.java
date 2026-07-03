// First time I've ever made a neural net, so I heavily
// commented this file to help myself learn better

package engine.neuralnet.networks;

import engine.neuralnet.*;

/**
 * "Simple" multi-layer neural network
 */
public class Feedforward implements Network {
	
	/**
	 * Keeps track of the network geometry
	 */
	private int[] networkSizes;
	
	/**
	 * Variable that keeps the activation function being used
	 */
	private Activation activation;
	
	/**
	 * Variable that keeps the cost function being used
	 */
	private Cost cost;
	
	/**
	 * Variable that keeps track of the last cost of the network
	 */
	private float currCost;
	
	private Vector[] biases, activations, preActivations;
	private Matrix[] weights;
	
	public Feedforward(int[] networkSizes, Activation activation, Cost cost) {
		if (networkSizes.length < 3) {throw new IllegalArgumentException();}
		// Store network, activation, and cost type
		this.networkSizes = networkSizes; 
		this.activation = activation; 
		this.cost = cost;
		// Initialize vectors and matrices
		this.activations = 	  new Vector[this.networkSizes.length];
		this.biases = 		  new Vector[this.networkSizes.length-1];
		this.preActivations = new Vector[this.networkSizes.length-1];
		this.weights =		  new Matrix[this.networkSizes.length-1];
		// Loop through and propagate arrays
		for (int i = 1; i < this.networkSizes.length; i++) {
			this.weights[i-1] = new Matrix(this.networkSizes[i-1], this.networkSizes[i]);
			this.biases[i-1] = new Vector(this.networkSizes[i]);
			this.weights[i-1].propagate();
		}
	}
	
	// z = f(W*a + b)
	// a = f(z)
	private Vector forward(Vector input) {
		if (input.getHgt() == weights[0].getHgt()) {  // Gatekeep
			// Activations has length + 1 compared to other arrays
			this.activations[0] = new Vector(input.getMatrix().clone());
			for (int i = 0; i < this.weights.length; i++) {
				// multiply by weights and add bias
				input = (Vector) this.weights[i].multiply(input);
				input.addMatrix(this.biases[i].getMatrix());
				// Store the input before applying activation function
				this.preActivations[i] = new Vector(input.getMatrix().clone());
				// Apply activation function throughout input vector
				for (int j = 0; j < input.getHgt(); j++) {
					input.set(j, this.activation.function(input.get(j)));
				} // Store inputs before applying addition and multiplication
				this.activations[i+1] = new Vector(input.getMatrix().clone());
			}
		} return input;
	}
	
	// cost derivative of output and expected
	// activation derivative of preactivation
	// element wise both together
	
	// calculate initial delta
	// Propagate backwards
	private void backward(Vector output, Vector expected) {
		Vector error; 
		for (int i = weights.length - 1; i > 0; i--) {
			for (int j = 0; j > this.preActivations[i].getHgt(); j++) {
				error = this.cost.derivative(output, expected);
				error.set(j, this.activation.derivative(this.preActivations[i].get(j)));
			}
		}
	}
	
	@Override
	public void train(Vector[] inputs, Vector[] expected, int epochs) {
		for (int i = 0; i < epochs; i++) {
			this.forward(inputs[i]);
		} // Calculate the current cost using the last output of the network
		this.currCost = cost.calculate(this.activations[this.activations.length-1], expected[expected.length-1]);
	}
	
	@Override
	public void step(Vector input, Vector expected) {
		// Calculate the current cost using the output of the network
		this.currCost = cost.calculate(this.activations[this.activations.length-1], expected);
	}
	
	@Override
	// Literally just a forward pass but without all the storing
	public Vector predict(Vector input) {
		if (input.getHgt() == weights[0].getHgt()) {  // Gatekeep
			for (int i = 0; i < this.weights.length; i++) {
				// multiply by weights and add bias
				input = (Vector) this.weights[i].multiply(input);
				input.addMatrix(this.biases[i].getMatrix());
				// Apply activation function throughout input vector
				for (int j = 0; j < input.getHgt(); j++) {
					input.set(j, this.activation.function(input.get(j)));
				}
			}
		} return input;
	}
	
	@Override
	public float getCost() {
		return this.currCost;
	}
	
	@Override
	public void loadNetwork(String name) {
		
	}
	
	@Override
	public void saveNetwork(String name) {
		
	}
}
