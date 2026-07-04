package engine.neural;

import engine.util.Reconstructable;

public interface Initializer extends Reconstructable {
	
	public abstract void initialize(Matrix weights);

}
