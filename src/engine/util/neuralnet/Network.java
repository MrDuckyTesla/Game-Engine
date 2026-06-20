package engine.util.neuralnet;

public class Network {
	
	private int[] networkSizes;
	private Vector[] biases;
	private Matrix[] weights;
	
	public Network(int[] networkSizes) {
		this.networkSizes = networkSizes;
		this.weights = new Matrix[networkSizes.length-1];
		this.biases = new Vector[networkSizes.length-1];
		for (int i = 1; i < networkSizes.length; i++) {
			this.weights[i-1] = new Matrix(networkSizes[i-1], networkSizes[i]);
			this.biases[i-1] = new Vector(networkSizes[i]);
			this.weights[i-1].propagate();
		}
	}
	
	public Vector forward() {
		return null;
	}
	
	private void backward() {
		
	}
	
	public void train() {
		
	}
	
	public void loadWeights() {
		
	}
	
	public void saveWeightd() {
		
	}
}
