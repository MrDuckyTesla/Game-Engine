package engine.neural;

import engine.util.Reconstructable;

public interface Activation extends Reconstructable {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);

}
