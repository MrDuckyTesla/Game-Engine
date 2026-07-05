package engine.neural;

import engine.util.Reconstructible;

public interface Network extends Reconstructible {
	
	public abstract void train(Vector[] inputs, Vector[] expected, int epochs);
	
	public abstract void step(Vector input, Vector expected);
	
	public abstract Vector predict(Vector input);
	
	public abstract float getCost();
	
	public abstract void saveNetwork(String name);

}
