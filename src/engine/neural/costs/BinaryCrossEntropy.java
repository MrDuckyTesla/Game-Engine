package engine.neural.costs;

import engine.neural.Cost;
import engine.neural.Vector;

public class BinaryCrossEntropy implements Cost {

	@Override
	// -(y log(y-hat) + (1 - y)log(1 - y-hat)) -> y-hat is prediction
	public float calculate(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	// y-hat - y
	public Vector derivative(Vector output, Vector target) {
		// TODO Auto-generated method stub
		return null;
	}

}
