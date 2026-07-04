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
	private final int[] networkSizes;
	
	/**
	 * Variable that tracks the activation function being used
	 */
	private final Activation activation;
	
	/**
	 * Variable that tracks the cost function being used
	 */
	private final Cost cost;
	
	/**
	 * Variable that tracks the optimizer being used
	 */
	private final Optimizer optimizer;
	
	/**
	 * Variable that tracks the initializer being used
	 */
	private final Initializer initializer;
	
	/**
	 * Variable that keeps track of the last cost of the network
	 */
	private float currCost;
	
	private Vector[] biases, activations, preActivations;
	private Matrix[] weights;
	
	public Feedforward(int[] networkSizes, Initializer initializer, Activation activation, Cost cost, Optimizer optimizer) {
		if (networkSizes.length < 3) {throw new IllegalArgumentException();}
		// Store network geometry
		this.networkSizes = networkSizes; 
		// Store network, activation, cost type, and optimizer
		this.initializer = initializer;
		this.activation = activation; 
		this.cost = cost;
		this.optimizer = optimizer;
		// Initialize vectors and matrices
		this.activations = 	  new Vector[this.networkSizes.length];
		this.biases = 		  new Vector[this.networkSizes.length-1];
		this.preActivations = new Vector[this.networkSizes.length-1];
		this.weights =		  new Matrix[this.networkSizes.length-1];
		// Loop through and propagate arrays
		for (int i = 1; i < this.networkSizes.length; i++) {
			this.weights[i-1] = new Matrix(this.networkSizes[i-1], this.networkSizes[i]);
			this.biases[i-1] = new Vector(this.networkSizes[i]);
			this.initializer.initialize(this.weights[i-1]);
		}
	}

	// z = f(W*a + b)
	// a = f(z)
	private Vector forward(Vector input) {
		if (input.getHgt() != this.weights[0].getWid()) {
			throw new IllegalArgumentException("Input size mismatch");
		} // Activations has length + 1 compared to other arrays
		this.activations[0] = new Vector(input.getMatrix().clone());
		for (int i = 0; i < this.weights.length; i++) {
			// multiply by weights and add bias
			input = this.weights[i].multiply(input);
			input.addMatrix(this.biases[i].getMatrix());
			// Store the input before applying activation function
			this.preActivations[i] = new Vector(input.getMatrix().clone());
			// Apply activation function throughout input vector
			for (int j = 0; j < input.getHgt(); j++) {
				input.set(j, this.activation.function(input.get(j)));
			} // Store inputs before applying addition and multiplication
			this.activations[i+1] = new Vector(input.getMatrix().clone());
		} return input;
	}
	
	// cost derivative of output and expected
	// activation derivative of preactivation
	// element wise both together
	
	// calculate initial delta
	// Propagate backwards
	private void backward(Vector output, Vector expected) {
		// Initialize delta with error vector
		Vector delta = new Vector(this.cost.derivative(output, expected).getMatrix());
		// Element wise multiplication of the activation derivative
		for (int i = 0; i < delta.getHgt(); i++) {
			delta.scale(i, this.activation.derivative(this.preActivations[this.weights.length-1].get(i)));
		} Matrix gradient, preWeight;
		// Loop though weights backwards
		for (int i = this.weights.length - 1; i >= 0; i--) {
			// Get gradient of current layer
			gradient = this.activations[i].multiply(delta.getTranspose());
			// Store weights before updating
			preWeight = this.weights[i].copy();
			// Update weights and biases
			this.optimizer.updateWeights(this.weights[i], gradient);
			this.optimizer.updateBiases(this.biases[i], delta);
			// make sure not updating input layer
			if (i != 0) {
				// Update the next delta to be used
				delta = preWeight.getTranspose().multiply(delta);
				for (int j = 0; j < delta.getHgt(); j++) {
					delta.set(j, delta.get(j) * this.activation.derivative(this.preActivations[i-1].get(j)));
				}
			}
		}
	}
	
	@Override
	public void train(Vector[] inputs, Vector[] expected, int epochs) {
		for (int i = 0; i < epochs; i++) {
			for (int j = 0; j < inputs.length; j++) {
				this.backward(this.forward(inputs[j]), expected[j]);
			}
		} 
		// Calculate the current cost using the last output of the network
		this.currCost = cost.calculate(this.activations[this.activations.length-1], expected[expected.length-1]);
		System.out.println(this.currCost);
	}
	
	@Override
	public void step(Vector input, Vector expected) {
		this.backward(this.forward(input), expected);
		// Calculate the current cost using the output of the network
		this.currCost = cost.calculate(this.activations[this.activations.length-1], expected);
	}
	
	@Override
	// Literally just a forward pass but without all the storing
	public Vector predict(Vector input) {
		if (input.getHgt() == weights[0].getWid()) {  // Gatekeep
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
