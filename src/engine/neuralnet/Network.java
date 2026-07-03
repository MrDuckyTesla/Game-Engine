package engine.neuralnet;

public interface Network {
	
	public abstract void train(Vector[] inputs, Vector[] expected, int epochs);
	
	public abstract void step(Vector input, Vector expected);
	
	public abstract Vector predict(Vector input);
	
	public abstract float getCost();
	
	public abstract void loadNetwork(String name);
	
	public abstract void saveNetwork(String name);

}
