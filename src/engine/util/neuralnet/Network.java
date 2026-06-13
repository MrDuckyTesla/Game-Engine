package engine.util.neuralnet;

public class Network {
	
	private Matrix blackBox;
	private Vector inputs, outputs;
	
	public Network(int inputs, int outputs, int middleWidth, int middleHeight) {
		this.blackBox = new Matrix(middleWidth, middleHeight);
		this.inputs = new Vector(inputs);
		this.outputs = new Vector(outputs);
	}
	
	public void loadWeights() {
		
	}
	
	public void saveWeightd() {
		
	}
}
