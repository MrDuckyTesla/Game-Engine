package engine.entity.abilities;

import engine.entity.AbstractEntity;
import engine.entity.movement.*;
import engine.entity.trigger.*;
import engine.util.ToolKit;

public class Sword8d extends AbstractAbility {
	
	private boolean lIsIdle = true, activate = false, isActive = false, sent = false;
	private Trigger currTrig = null;

	public Sword8d() {}
	public Sword8d(int key) {super(key);}
	public Sword8d(int[] keys) {super(keys);}
	
	@Override
	public Trigger getTrigger() {return this.currTrig;}
	@Override
	public boolean sentTrigger() {return this.sent;}

	@Override
	public void update(AbstractEntity e, AbstractMove m) throws IllegalArgumentException {
		if (m.getMoveType() != Move.EIGHT) {throw new IllegalArgumentException();}
		if (!e.getAnimator().getDoneAnimation(4) && this.isActive) {
			((EightDirectionalMove) m).halfSpeed(); this.setSwing(m, false); 
			e.getAnimator().setAnim(e.getImg(), m, 48, 4, 12, this.lIsIdle == m.getIsIdle() && m.getIsIdle()); 
		} else {
			this.isActive = false;
			if (this.getKeys() != null) {
				for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {e.getAnimator().resetAnim(); this.isActive = true;}}
			} else if (this.activate) {e.getAnimator().resetAnim(); this.isActive = true;}
			this.setSwing(m, true);
		} this.lIsIdle = m.getIsIdle();
		
		if (this.isActive) {
			this.currTrig = this.sent? null : Interaction.createInteraction(e.getRoom(), m, e, this, Triggers.DELETE);
			this.sent = true;
		} else {this.currTrig = null; this.sent = false;}
	}
	
	private void setSwing(AbstractMove m, boolean swing) {((EightDirectionalMove) m).setForceWalk(!swing); ((EightDirectionalMove) m).setCanChange(swing);}

	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public void setActive(boolean activate) {this.activate = activate;}
	@Override
	public AbstractAbility get() {return this.getKeys() == null? new Sword8d() : new Sword8d(this.getKeys());}
	@Override
	public Ability getType() {return Ability.SWORD_EIGHT_DIR;}

}
