package engine.neural;

import engine.data.serializations.FastSerializable;
import engine.neural.util.Vector;

public interface Network extends FastSerializable<Network> {
	
	public abstract void train(Vector[] inputs, Vector[] expected, int epochs);
	
	public abstract void step(Vector input, Vector expected);
	
	public abstract Vector predict(Vector input);
	
	public abstract float getCost();

}
