package engine.neural;

import engine.data.Serializable;

public interface Cost extends Serializable<Cost> {
	
	public abstract float calculate(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
