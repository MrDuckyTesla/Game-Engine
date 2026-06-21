package engine.util.neuralnet.activations;

public class Sigmoid extends AbstractActivation {

	@Override
	public float function(float x) {
		return 1 / (float) (1 + Math.exp(x));
	}

	@Override
	public float derivative(float x) {
		float sig = this.function(x);
		return sig * (1 - sig);
	}

	@Override
	public Activations getActivation() {
		return Activations.SIGMOID;
	}

}
