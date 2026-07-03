package engine.neuralnet;

public interface Cost {
	
	public abstract float cost(Vector output, Vector target);
	
	public abstract Vector derivative(Vector output, Vector target);

}
