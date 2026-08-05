package engine.neural;

import engine.data.Serializable;

public interface Initializer extends Serializable<Initializer> {
	
	public abstract void initialize(Matrix weights);

}
