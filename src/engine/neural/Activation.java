package engine.neural;

import engine.util.Reconstructible;

public interface Activation extends Reconstructible {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);

}
