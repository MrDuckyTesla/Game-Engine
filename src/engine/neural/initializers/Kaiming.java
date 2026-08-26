package engine.neural.initializers;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
import engine.neural.*;
import engine.neural.util.Matrix;

public class Kaiming implements Initializer {

	private final java.util.Random rand = new java.util.Random();
	private final boolean uniform;
	
	public Kaiming() {this.uniform = true;}
	public Kaiming(boolean uniform) {this.uniform = uniform;}

	@Override
	public void initialize(Matrix weights) {
		float bound = uniform
				? (float) Math.sqrt(6.0 / (weights.getWid())) 
				: (float) Math.sqrt(2.0 / (weights.getWid()));
		for (int i = 0; i < weights.getLength(); i++) {
			if (this.uniform) {weights.set(i, rand.nextFloat(-bound, bound));} 
			else {weights.set(i, (float) rand.nextGaussian(0, bound));}
		}
	}
	
	public String getClassInfo() {
		return this.getClass().getName() + "\n" + this.uniform;
	}
	
	@Override
	public byte[] serialize() {
		return ByteHelper.toBytes(this.uniform);
	}
	@Override
	public Initializer deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new Kaiming(b.readBool());
	}
	@Override
	public Initializer[] getProtoArray(int length) {
		return new Kaiming[length];
	}
	
}
