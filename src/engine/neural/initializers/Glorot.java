package engine.neural.initializers;

import engine.neural.*;
import engine.util.ByteHelper;
import engine.util.Matrix;
import engine.util.data.Serializable;

public class Glorot implements Initializer {
	
	private final java.util.Random rand = new java.util.Random();
	private final boolean uniform;
	
	public Glorot() {this.uniform = true;}
	public Glorot(boolean uniform) {this.uniform = uniform;}

	@Override
	public void initialize(Matrix weights) {
		float bound = uniform
				? (float) Math.sqrt(6.0 / (weights.getWid() + weights.getHgt())) 
				: (float) Math.sqrt(2.0 / (weights.getWid() + weights.getHgt()));
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
	public Initializer deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new Glorot(b.readBool());
	}
	@Override
	public Initializer[] getProtoArray(int length) {
		return new Glorot[length];
	}
	
}
