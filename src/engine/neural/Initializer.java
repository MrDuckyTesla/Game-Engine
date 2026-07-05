package engine.neural;

import engine.util.Reconstructible;

public interface Initializer extends Reconstructible {
	
	public abstract void initialize(Matrix weights);

}
