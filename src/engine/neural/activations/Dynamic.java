package engine.neural.activations;

import engine.neural.Activation;
import engine.util.ByteHelper;

public class Dynamic implements Activation {
	
	private final Activation[] layer;
	private int layerNum = 0;

	public Dynamic(Activation[] layer) {
		if (layer.length == 0) {
			throw new IllegalArgumentException();
		} for (Activation a : layer) {
			if (a == null) {
				throw new IllegalArgumentException();
			}
		} this.layer = layer;
	}
	
	public void next() {
		this.layerNum = this.layerNum == this.layer.length-1? 0 : this.layerNum++;
	}

	@Override
	public float function(float x) {
		return this.layer[this.layerNum].function(x);
	}

	@Override
	public float derivative(float x) {
		return this.layer[this.layerNum].derivative(x);
	}

	@Override
	public byte[] serialize() {
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(this.layer),
			ByteHelper.toBytes(this.layerNum)
		);
	}

	@Override
	public Activation deserialize(ByteHelper b) {
		Dynamic d = new Dynamic(b.readObjArr(this.getPrototype()));
		d.layerNum = b.readInt(); return d;
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Activation[length];
	}

	@Override
	public Activation getPrototype() {
		return new Dynamic(new Activation[] {new engine.neural.activations.Linear()});
	}

}
