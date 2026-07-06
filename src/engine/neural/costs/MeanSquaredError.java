package engine.neural.costs;

import engine.neural.Cost;
import engine.neural.Vector;

public class MeanSquaredError implements Cost {

	@Override
	public float calculate(Vector output, Vector target) {
		if (output.getHgt() == target.getHgt()) {
			float cost = 0; float sum;
			for (int i = 0; i < output.getHgt(); i++) {
				sum = output.get(i) - target.get(i);
				cost += sum * sum;
			} return (float) (cost / (2.0*output.getHgt()));
		} return 0;
	}

	@Override
	public Vector derivative(Vector output, Vector target) {
		if (output.getHgt() == target.getHgt()) {
			Vector cost = new Vector(output.getHgt());
			for (int i = 0; i < cost.getHgt(); i++) {
				cost.set(i, (output.get(i) - target.get(i))/output.getHgt());
			} return cost;
		} return new Vector(0);
	}

}
