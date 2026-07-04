package engine.neural;

import engine.util.Reconstructable;

public interface Cost extends Reconstructable {
	
	public abstract float calculate(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
