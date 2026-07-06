package engine.neural.initializers;

import engine.neural.Initializer;
import engine.neural.Matrix;

public class RandomNormal implements Initializer {

	private final java.util.Random rand = new java.util.Random();
	private final float mean, stdDev;
	
	public RandomNormal() {this.mean = 0; this.stdDev = 1;}
	public RandomNormal(float mean, float stdDev) {this.mean = mean; this.stdDev = stdDev;}

	@Override
	public void initialize(Matrix weights) {
		for (int i = 0; i < weights.getWid(); i++) {
			for (int j = 0; j < weights.getHgt(); j++) {
				weights.set(j, i, (float) (this.rand.nextGaussian(this.mean, this.stdDev)));
			}
		}
	}
	
	@Override
	public String getClassInfo() {
		return this.getClass().getName() + "\n" + this.mean + "\n" + this.stdDev;
	}

}
