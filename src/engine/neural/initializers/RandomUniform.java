package engine.neural.initializers;

import engine.data.ByteHelper;
import engine.neural.*;

public class RandomUniform implements Initializer {

	private final float min, max;
	
	public RandomUniform() {this.min = 0; this.max = 1;}
	public RandomUniform(float num) {this.min = -num; this.max = num;}
	public RandomUniform(float min, float max) {this.min = min; this.max = max;
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
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(min),
			ByteHelper.toBytes(max)
		);
	}
	@Override
	public Initializer deserialize(ByteHelper bytes) {
		return new RandomUniform(
			bytes.readFloat(),
			bytes.readFloat()
		);
	}

}
