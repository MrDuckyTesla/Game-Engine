package engine.neural;

public interface Initializer extends Reconstructible {
	
	public abstract void initialize(Matrix weights);

}
