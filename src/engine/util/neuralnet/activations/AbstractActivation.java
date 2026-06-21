package engine.util.neuralnet.activations;

public abstract class AbstractActivation {
	
	public abstract float function(float x);
	
	public abstract float derivative(float x);
	
	public abstract Activations getActivation();

}
