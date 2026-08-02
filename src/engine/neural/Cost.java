package engine.neural;

public interface Cost extends Reconstructible {
	
	public abstract float calculate(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
