package game.entity.abilities;

import game.ToolKit;
import game.entity.Entity;
import game.entity.movement.EightDirectionalMove;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;

//this class ONLY works on objects using EightDirectionalMove()
public class Sprint8d extends Ability {
	
	private boolean activate, isActive = false;
	
	public Sprint8d() {}
	public Sprint8d(int key) {super(key);}
	public Sprint8d(int[] keys) {super(keys);}

	@Override
	public void update(Entity o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		if (this.getKeys() != null) {
			for (int key : this.getKeys()) {this.isActive = ToolKit.keyIsDown(key);}
		} else {this.isActive = this.activate;}
		if (this.isActive) {
			((EightDirectionalMove) m).doubSpeed();
			m.getAnimator().setAnimSpeed(6);
		}
	}

	@Override
	public void setActive(boolean activate) {this.activate = activate;}
	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public Ability get() {return this.getKeys() == null? new Sprint8d() : new Sprint8d(this.getKeys());}
	@Override
	public Abilities getType() {return Abilities.sprint8d;}
	
}
