package engine.neural;

import engine.util.Vector;
import engine.util.data.Serializable;

public interface Network extends Serializable<Network> {
	
	public abstract void train(Vector[] inputs, Vector[] expected, int epochs);
	
	public abstract void step(Vector input, Vector expected);
	
	public abstract Vector predict(Vector input);
	
	public abstract float getCost();

}
