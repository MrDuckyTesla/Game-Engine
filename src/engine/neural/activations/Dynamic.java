package engine.neural.activations;

import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;
import engine.neural.Activation;

public class Dynamic implements Activation {
	
	private final Activation[] layer;
	private int layerNum = 0;

	public Dynamic(Activation... activations) {
		if (activations.length == 0) {
			throw new IllegalArgumentException();
		} for (Activation a : activations) {
			if (a == null) {
				throw new IllegalArgumentException();
			}
		} this.layer = activations;
	}
	
	public void next() {
		this.layerNum = this.layerNum == this.layer.length-1? 0 : ++this.layerNum;
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
	public Activation deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		Dynamic d = new Dynamic((Activation[]) b.readObjArr(prototypes[0]));
		d.layerNum = b.readInt(); return d;
	}

	@Override
	public Activation[] getProtoArray(int length) {
		return new Dynamic[length];
	}

}
