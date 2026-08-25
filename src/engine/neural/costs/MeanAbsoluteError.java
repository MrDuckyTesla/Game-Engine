package engine.neural.costs;

import engine.util.*;
import engine.util.data.Serializable;
import engine.neural.Cost;

public class MeanAbsoluteError implements Cost {

	@Override
	public float calculate(Vector output, Vector target) {
		if (output.getHgt() == target.getHgt()) {
			float cost = 0;
			for (int i = 0; i < output.getHgt(); i++) {
				cost += Math.abs(output.get(i) - target.get(i));
			} return cost / output.getHgt();
		} return 0;
	}

	@Override
	public Vector derivative(Vector output, Vector target) {
		if (output.getHgt() == target.getHgt()) {
			Vector cost = new Vector(output.getHgt());
			for (int i = 0; i < cost.getHgt(); i++) {
				cost.set(i, (output.get(i) - target.get(i) > 0? 1 : -1) / output.getHgt());
			} return cost;
		} return new Vector(0);
	}
	
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Cost deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new MeanAbsoluteError();
	}

	@Override
	public Cost[] getProtoArray(int length) {
		return new MeanAbsoluteError[length];
	}

}
