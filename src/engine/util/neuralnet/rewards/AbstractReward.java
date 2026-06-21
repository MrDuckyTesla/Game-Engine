package engine.util.neuralnet.rewards;

import engine.util.neuralnet.Vector;

public abstract class AbstractReward {
	
	public abstract float reward(Vector netOutput);

}
