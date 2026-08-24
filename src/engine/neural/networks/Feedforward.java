// First time I've ever made a neural net, so I heavily
// commented this file to help myself learn better

package engine.neural.networks;

import engine.neural.*;
import engine.util.ByteHelper;
import engine.util.Matrix;
import engine.util.Vector;

/**
 * "Simple" multi-layer neural network
 */
public class Feedforward implements Network {
	
	/**
	 * Keeps track of the network geometry
	 */
	private final int[] networkSizes;
	
	private Vector[] biases, activations, preActivations;
	private Matrix[] weights;
	
	/**
	 * Variable that tracks the initializer being used
	 */
	private final Initializer initializer;
	
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
	 * Variable that keeps track of the last cost of the network
	 */
	private float currCost;
	
	/**
	 * Constructs a new multilayer neural network
	 * @param networkSizes The size of each layer of the network, for instance, [2, 4, 4, 1] 
	 * has an input of size 2, 2 hidden layers of size 4, and an output of size 1
	 * @param initializer The specific way you want to populate the weights at creation
	 * @param activation The specific way you want neurons to fire
	 * @param cost The specific way you want to show how wrong the network is
	 * @param optimizer The specific way you want to push the network towards minima
	 */
	public Feedforward(int[] networkSizes, Initializer initializer, Activation activation, Cost cost, Optimizer optimizer) {
		// Enforce legal argument sizes
		if (networkSizes.length < 3) {throw new IllegalArgumentException();}
		for (int i : networkSizes) {if (i <= 0) {throw new IllegalArgumentException();}}
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
	
	/**
	 * Only use this constructor if you intend to use Feedforward as a prototype
	 */
	@SuppressWarnings("unused")
	private Feedforward() {
		this.networkSizes = null;
		this.initializer = null;
		this.activation = null;
		this.cost = null;
		this.optimizer = null;
	}
	
	private Vector forward(Vector input) {
		if (input.getHgt() != this.weights[0].getWid()) {
			throw new IllegalArgumentException("Input size mismatch");
		} // Activations has length + 1 compared to other arrays
		this.activations[0] = new Vector(input.getMatrix().clone());
		for (int i = 0; i < this.weights.length; i++) {
			// multiply by weights and add bias, z = f(W*a + b)
			input = this.weights[i].multiply(input);
			input.addMatrix(this.biases[i].getMatrix()); 
			// Store the input before applying activation function
			this.preActivations[i] = new Vector(input.getMatrix().clone());
			// Apply activation function throughout input vector
			for (int j = 0; j < input.getHgt(); j++) { // a = f(z)
				input.set(j, this.activation.function(input.get(j)));
			} // Store inputs before applying addition and multiplication
			this.activations[i+1] = new Vector(input.getMatrix().clone());
		} return input;  // Return prediction
	}
	
	private void backward(Vector output, Vector expected) {
		// Initialize delta with error vector
		Vector delta = this.cost.derivative(output, expected);
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
	
	@Override
	public void train(Vector[] inputs, Vector[] expected, int epochs) {
		for (int i = 0; i < epochs; i++) {
			for (int j = 0; j < inputs.length; j++) {
				this.backward(this.forward(inputs[j]), expected[j]);
			}
		}  // Calculate the current cost using the last output of the network
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
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.networkSizes),
			ByteHelper.toBytes(this.initializer),
			ByteHelper.toBytes(this.activation),
			ByteHelper.toBytes(this.cost),
			ByteHelper.toBytes(this.optimizer),
			ByteHelper.toBytes(this.biases),
			ByteHelper.toBytes(this.activations),
			ByteHelper.toBytes(this.preActivations),
			ByteHelper.toBytes(this.weights)
		);
	}

	@Override
	public Network deserialize(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
