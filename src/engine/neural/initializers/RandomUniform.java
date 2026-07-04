package engine.neural.initializers;

import engine.neural.Initializer;
import engine.neural.Matrix;

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
	
	@Override
	public String getClassInfo() {
		return this.getClass().getName() + "\n" + this.min + "\n" + this.max;
	}

}
