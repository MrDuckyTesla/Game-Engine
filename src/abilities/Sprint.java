package abilities;

import entity.Obstacle;
import game.ToolKit;
import movement.MoveSet;
import movement.EightDirectionalMove;
import movement.Moves;

public class Sprint extends Ability {
	
	private boolean isActive = false;
	
	public Sprint(int key) {
		super(key);
	}
	
	public Sprint(int[] keys) {
		super(keys);
	}

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		for (int key : this.getKeys()) {this.isActive = ToolKit.keyIsDown(key);}
		if (this.isActive) {((EightDirectionalMove) m).setDoubSpeed();}
		else {((EightDirectionalMove) m).setNormSpeed();}
	}

	@Override
	public boolean isActive() {return this.isActive;}
	
}
