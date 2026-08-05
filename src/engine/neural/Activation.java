package engine.neural;

import engine.util.data.Serializable;

public interface Activation extends Serializable<Activation> {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);

}
