package engine.util.neuralnet.activations;

public class ReLU extends AbstractActivation {

	@Override
	public float function(float x) {
		return Math.max(0, x);
	}

	@Override
	public float derivative(float x) {
		return x > 0? 1 : 0;
	}

	@Override
	public Activations getActivation() {
		return Activations.RELU;
	}

}
