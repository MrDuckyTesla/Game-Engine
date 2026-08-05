package engine.neural.networks;

import engine.neural.Network;
import engine.util.ByteHelper;
import engine.util.Vector;

public class Recurrent implements Network{

	public Recurrent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void train(Vector[] inputs, Vector[] expected, int epochs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step(Vector input, Vector expected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector predict(Vector input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Network deserialize(ByteHelper bytes) {
		// TODO Auto-generated method stub
		return null;
	}

}
