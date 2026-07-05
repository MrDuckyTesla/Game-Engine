// First time I've ever made a neural net, so I heavily
// commented this file to help myself learn better

package engine.neural.networks;

import engine.neural.*;
import java.io.*;
import java.util.Scanner;

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
	
	public Feedforward(String name) throws Exception {
		File file = new File("net"); file.mkdirs();
	    Scanner scan = new Scanner(new File(file, name + ".txt"));
	    // Look for NetworkSizes
	    while (!scan.next().equals("[")) {}
	    int[] netSize = new int[] {};
	    while (scan.hasNextInt()) {
	    	int[] temp = new int[netSize.length+1];
	    	for (int i = 0; i < netSize.length; i++) {temp[i] = netSize[i];}
	    	temp[netSize.length] = scan.nextInt(); netSize = temp;
	    } this.networkSizes = netSize;
	    // Find network parameters
	    this.initializer = (Initializer) this.search("Initializer Information:", scan);
	    this.activation = (Activation) this.search("Activation Information:", scan);
	    this.cost = (Cost) this.search("Cost Information:", scan);
	    this.optimizer = (Optimizer) this.search("Optimizer Information:", scan);
	    // Get cost
	    scan.nextLine(); this.currCost = scan.nextFloat();
	    // Initialize vectors and matrices
	    this.activations = 	  new Vector[this.networkSizes.length];
	 	this.biases = 		  new Vector[this.networkSizes.length-1];
	 	this.preActivations = new Vector[this.networkSizes.length-1];
	 	this.weights =		  new Matrix[this.networkSizes.length-1];
	 	// Loop through and propagate arrays
	 	for (int i = 1; i < this.networkSizes.length; i++) {
	 		this.weights[i-1] = new Matrix(this.networkSizes[i-1], this.networkSizes[i]);
	 		this.biases[i-1] = new Vector(this.networkSizes[i]);
	 	} // Propagate weights and biases
	 	this.textToMatrix(scan, this.weights);
	 	this.textToMatrix(scan, this.biases);
	    scan.close();
	}
	
	private void textToMatrix(Scanner scan, Matrix[] m) {
		for (int k = 0; k < this.biases.length; k++) {
		 	while (!scan.hasNextFloat()) {scan.nextLine();}
		 	for (int i = 0; i < m[k].getHgt(); i++) {
		 		for (int j = 0; j < m[k].getWid(); j++) {
		 			m[k].set(i, j, scan.nextFloat());
		 		}
		 	}
	 	}
	}
	
	/**
	 * Function for finding and initializing network params
	 * @param str Header information
	 * @param scan Scanner object
	 * @return Class within text file
	 * @throws Exception
	 */
	private Object search(String str, Scanner scan) throws Exception {
		while (!scan.nextLine().equals(str)) {} scan.nextLine();
	    String className = scan.nextLine(); String[] args = new String[] {};
	    String next = scan.nextLine();
	    while (!next.isBlank() && !next.contains(":")) {
	    	String[] temp = new String[args.length+1];
	    	for (int i = 0; i < args.length; i++) {temp[i] = args[i];}
	    	temp[args.length] = next; args = temp;
	    	next = scan.nextLine();
	    } Class<?> t, c = Class.forName(className);  // Get class from string and make temp
		Class<?>[] p = new Class<?>[args.length];  // Create container for params
		Object[] argsReal = new Object[args.length];
		// Iterate through arguments provided
		for (int i = 0; i < args.length; i++) {
			// Assign temp to arguments class
			try {argsReal[i] = Float.parseFloat(args[i]); t = float.class;} 
			catch (NumberFormatException e) {
				try {argsReal[i] = Integer.parseInt(args[i]); t = int.class;} 
				catch(NumberFormatException f) {
					argsReal[i] = Boolean.parseBoolean(args[i]); t = boolean.class;
				}
			} p[i] = t;  // Assign  temp to param
		} System.out.print("Found " + className);
		if (args.length > 0) { // Check if parameters were found
			System.out.print(" with params: ");
			for (String i : args) {System.out.print(i + " ");} 
		} System.out.println();  // Create new instance of described class
		return c.getDeclaredConstructor(p).newInstance(argsReal);
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
	public void saveNetwork(String name) {	
		BufferedWriter writer;
		try {
			File file = new File("net");
	        file.mkdirs();
	        
	        writer = new BufferedWriter(new FileWriter(new File(file, name + ".txt")));

			writer.write("Network Sizes:\n\n[\n"+this.networkSizes[0]);
			for (int i = 1; i < this.networkSizes.length; i++) {
				writer.write(" "+this.networkSizes[i]);
			} writer.write("\n]\n\n");
			
			writer.write("Network Information:\n\n");
			writer.write(this.getClassInfo()+"\n\n");
			
			writer.write("Initializer Information:\n\n");
			writer.write(this.initializer.getClassInfo()+"\n\n");
			
			writer.write("Activation Information:\n\n");
			writer.write(this.activation.getClassInfo()+"\n\n");
			
			writer.write("Cost Information:\n\n");
			writer.write(this.cost.getClassInfo()+"\n\n");
			
			writer.write("Optimizer Information:\n\n");
			writer.write(this.optimizer.getClassInfo()+"\n\n");
			
			writer.write("Last Cost:\n\n");
			writer.write(this.currCost+"\n\n"); 
			
			writer.write("Weights:\n");
			writer.write(this.printMatrices(this.weights)+"\n\n");
			
			writer.write("Biases:\n");
			writer.write(this.printMatrices(this.biases)+"\n\n");
			
			writer.write("Activations:\n");
			writer.write(this.printMatrices(this.activations)+"\n\n");
			
			writer.write("Pre Activations:\n");
			writer.write(this.printMatrices(this.preActivations)+"\n\n");
			
			writer.close();
		} catch (IOException e) {
			System.out.println("Save Failed.");
			e.printStackTrace();
		}
	}
	
	private String printMatrices(Matrix[] m) {
		StringBuilder s = new StringBuilder();
		for (int k = 0; k < m.length; k++) {
			s.append("\nLayer " + k + ":\n[\n");
			for (int i = 0; i < m[k].getHgt(); i++) {
				for (int j = 0; j< m[k].getWid(); j++) {
					s.append(m[k].get(i, j) + " ");
				} s.append("\n");
			} s.append("]\n");
		} return s.toString();
	}
	
}
