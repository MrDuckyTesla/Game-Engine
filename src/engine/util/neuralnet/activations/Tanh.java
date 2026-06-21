package engine.util.neuralnet.activations;

public class Tanh extends AbstractActivation {

	@Override
	public float function(float x) {
		return (float) Math.tanh(x);
	}

	@Override
	public float derivative(float x) {
		float tan = (float) Math.tanh(x);
		return 1 - tan*tan;
	}

	@Override
	public Activations getActivation() {
		return Activations.TANH;
	}

}
