package engine.neural;

import engine.data.serializations.FastSerializable;
import engine.neural.util.Vector;

public interface Cost extends FastSerializable<Cost> {
	
	public abstract float calculate(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
