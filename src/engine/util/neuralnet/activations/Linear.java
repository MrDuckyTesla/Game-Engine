package engine.util.neuralnet.activations;

public class Linear extends AbstractActivation {

	@Override
	public float function(float x) {
		return x;
	}

	@Override
	public float derivative(float x) {
		return 1;
	}

	@Override
	public Activations getActivation() {
		return Activations.LINEAR;
	}

}
