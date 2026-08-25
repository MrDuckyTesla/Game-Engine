package obsolete.entity.abilities;

import obsolete.ToolKit;
import obsolete.entity.*;
import obsolete.entity.enums.*;
import obsolete.entity.movement.EightDirectional;

//this class ONLY works on objects using EightDirectionalMove()
public class Sprint8d extends Ability {
	
	private boolean activate, isActive = false;
	
	public Sprint8d() {}
	public Sprint8d(int key) {super(key);}
	public Sprint8d(int[] keys) {super(keys);}

	@Override
	public void update(Entity e, Move m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.EIGHT) {throw new IllegalArgumentException();}
		if (this.getKeys() != null) {
			this.isActive = false;
			for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {this.isActive = true;}}
		} else {this.isActive = this.activate;}
		if (this.isActive) {
			((EightDirectional) m).doubSpeed();
			e.getAnimator().setAnimSpeed(6);
		}
	}

	@Override
	public void setActive(boolean activate) {this.activate = activate;}
	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public Ability get() {return this.getKeys() == null? new Sprint8d() : new Sprint8d(this.getKeys());}
	@Override
	public Abilities getType() {return Abilities.SPRINT_EIGHT_DIR;}
	
}
