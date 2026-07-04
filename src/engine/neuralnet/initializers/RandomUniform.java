package engine.neuralnet.initializers;

import engine.neuralnet.Initializer;
import engine.neuralnet.Matrix;

public class RandomUniform implements Initializer {

	private final float min, max;
	
	public RandomUniform(float min, float max) {
		this.min = min; this.max = max;
	}

	@Override
	public void initialize(Matrix weights) {
		for (int i = 0; i < weights.getWid(); i++) {
			for (int j = 0; j < weights.getHgt(); j++) {
				weights.set(j, i, (float) (Math.random()*(max-min)+min));
			}
		}
	}

}
