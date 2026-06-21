package engine.util.neuralnet.activations;

public class GELU extends AbstractActivation {

	@Override
	public float function(float x) {
		return x * this.gaussianDistribution(x);
	}
	
	private float gaussianDistribution(float x) {
		return 0.5f * (1 + (float) Math.tanh(Math.sqrt(2/Math.PI)*(x+0.044715*x*x*x)));
	}

	@Override
	public float derivative(float x) {
		float temp = (float) Math.tanh((Math.sqrt(2/Math.PI)*(x+0.044715*x*x*x)));
		return (float) (0.5*(1+temp)+x*0.5*(1-temp*temp)*(Math.sqrt(2/Math.PI)*(1+0.134145*x*x)));
	}

	@Override
	public Activations getActivation() {
		return Activations.GELU;
	}

}
