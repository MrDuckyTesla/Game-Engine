package engine.neural;

import engine.data.serializations.FastSerializable;

public interface Activation extends FastSerializable<Activation> {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);

}
