package abilities;

import entity.Obstacle;
import game.ToolKit;
import movement.EightDirectionalMove;
import movement.MoveSet;
import movement.Moves;

public class Sword8d extends Ability {
	
	private boolean isActive = false;

	public Sword8d(int key) {
		super(key);
		// TODO Auto-generated constructor stub
	}
	
	public Sword8d(int[] keys) {
		super(keys);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {this.isActive = true;}}
		if (this.isActive) {((EightDirectionalMove) m).halfSpeed();}
	}

	@Override
	public boolean isActive() {return this.isActive;}

}
