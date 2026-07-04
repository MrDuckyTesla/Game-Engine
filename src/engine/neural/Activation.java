package engine.neural;

public interface Activation {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);

}
