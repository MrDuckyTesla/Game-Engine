package engine.neural.initializers;

import engine.neural.Initializer;
import engine.util.ByteHelper;
import engine.util.Matrix;

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
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(mean),
			ByteHelper.toBytes(stdDev)
		);
	}

}
