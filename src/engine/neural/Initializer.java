package engine.neural;

import engine.util.Matrix;
import engine.util.data.Serializable;

public interface Initializer extends Serializable<Initializer> {
	
	public abstract void initialize(Matrix weights);

}
