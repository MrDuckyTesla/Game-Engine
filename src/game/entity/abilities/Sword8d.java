package game.entity.abilities;

import game.Room;
import game.entity.Entity;
import game.entity.movement.*;
import game.entity.trigger.*;
import game.util.ToolKit;

public class Sword8d extends Ability {
	
	private boolean lIsIdle = true, activate = false, isActive = false;;
	private Trigger currTrig = null;

	public Sword8d() {}
	public Sword8d(int key) {super(key);}
	public Sword8d(int[] keys) {super(keys);}
	
	@Override
	public Trigger getTrigger() {return this.currTrig;}

	@Override
	public void update(Entity e, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		if (!m.getAnimator().getDoneAnimation(4) && this.isActive) {
			((EightDirectionalMove) m).halfSpeed(); this.setSwing(m, false); 
			m.getAnimator().setAnim(e.getImg(), m, 48, 4, 12, this.lIsIdle == m.getIsIdle() && m.getIsIdle()); 
			this.currTrig = this.isActive? Room.createInteraction(m, e, Triggers.DELETE) : null;
		} else {
			this.isActive = false;
			if (this.getKeys() != null) {for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {m.getAnimator().resetAnim(); this.isActive = true;}}} 
			else if (this.activate) {m.getAnimator().resetAnim(); this.isActive = true;}
			this.setSwing(m, true);
		} this.lIsIdle = m.getIsIdle();
	}
	
	private void setSwing(MoveSet m, boolean swing) {((EightDirectionalMove) m).setForceWalk(!swing); ((EightDirectionalMove) m).setCanChange(swing);}

	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public void setActive(boolean activate) {this.activate = activate;}
	@Override
	public Ability get() {return this.getKeys() == null? new Sword8d() : new Sword8d(this.getKeys());}
	@Override
	public Abilities getType() {return Abilities.SWORD_EIGHT_DIR;}

}
