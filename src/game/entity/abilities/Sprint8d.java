package game.entity.abilities;

import game.ToolKit;
import game.entity.Entity;
import game.entity.Obstacle;
import game.entity.movement.EightDirectionalMove;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;

//this class ONLY works on objects using EightDirectionalMove()
public class Sprint8d extends Ability {
	
	private boolean isActive = false;
	
	public Sprint8d(int key) {super(key);}
	public Sprint8d(int[] keys) {super(keys);}

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		for (int key : this.getKeys()) {this.isActive = ToolKit.keyIsDown(key);}
		if (this.isActive) {
			((EightDirectionalMove) m).doubSpeed();
			if (o.getType() == Entity.Player) {
				m.getAnimator().setAnimSpeed(6);
			}
		}
	}


	@Override
	public boolean isActive() {return this.isActive;}
	
}
