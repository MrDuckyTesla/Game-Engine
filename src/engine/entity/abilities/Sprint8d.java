package engine.entity.abilities;

import engine.entity.AbstractEntity;
import engine.entity.movement.EightDirectionalMove;
import engine.entity.movement.AbstractMove;
import engine.entity.movement.Moves;
import engine.util.ToolKit;

//this class ONLY works on objects using EightDirectionalMove()
public class Sprint8d extends AbstractAbility {
	
	private boolean activate, isActive = false;
	
	public Sprint8d() {}
	public Sprint8d(int key) {super(key);}
	public Sprint8d(int[] keys) {super(keys);}

	@Override
	public void update(AbstractEntity e, AbstractMove m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.EIGHT) {throw new IllegalArgumentException();}
		if (this.getKeys() != null) {
			this.isActive = false;
			for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {this.isActive = true;}}
		} else {this.isActive = this.activate;}
		if (this.isActive) {
			((EightDirectionalMove) m).doubSpeed();
			e.getAnimator().setAnimSpeed(6);
		}
	}

	@Override
	public void setActive(boolean activate) {this.activate = activate;}
	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public AbstractAbility get() {return this.getKeys() == null? new Sprint8d() : new Sprint8d(this.getKeys());}
	@Override
	public Abilities getType() {return Abilities.SPRINT_EIGHT_DIR;}
	
}
