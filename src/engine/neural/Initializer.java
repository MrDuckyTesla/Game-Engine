package engine.neural;

import engine.data.serializations.FastSerializable;
import engine.neural.util.Matrix;

public interface Initializer extends FastSerializable<Initializer> {
	
	public abstract void initialize(Matrix weights);

}
