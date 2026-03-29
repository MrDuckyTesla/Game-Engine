package entity.movement.abilities;

import entity.Obstacle;
import entity.movement.EightDirectionalMove;
import entity.movement.MoveSet;
import entity.movement.Moves;
import game.ToolKit;

//this class ONLY works on objects using EightDirectionalMove()
public class Sprint8d extends Ability {
	
	private boolean isActive = false;
	
	public Sprint8d(int key) {super(key);}
	public Sprint8d(int[] keys) {super(keys);}

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		for (int key : this.getKeys()) {this.isActive = ToolKit.keyIsDown(key);}
		if (this.isActive) {((EightDirectionalMove) m).doubSpeed();}
	}

	@Override
	public boolean isActive() {return this.isActive;}
	
}
