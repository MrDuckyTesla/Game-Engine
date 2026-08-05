package engine.neural;

import engine.util.Vector;
import engine.util.data.Serializable;

public interface Cost extends Serializable<Cost> {
	
	public abstract float calculate(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
