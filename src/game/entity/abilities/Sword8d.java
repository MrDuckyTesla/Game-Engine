package game.entity.abilities;

import game.ToolKit;
import game.entity.Obstacle;
import game.entity.movement.EightDirectionalMove;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;

public class Sword8d extends Ability {
	
	private long timeUnlock = 0;  // REPLACE ME WITH ANIMATION END
	private boolean hasUnlocked = false;
	private boolean activate, lIsIdle = true;

	public Sword8d() {}
	public Sword8d(int key) {super(key);}
	public Sword8d(int[] keys) {super(keys);}

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		if (this.isActive()) {((EightDirectionalMove) m).halfSpeed(); this.setSwing(m, false); m.getAnimator().setAnim(o.getImg(), m, 48, 4, 12, this.lIsIdle == m.getIsIdle() && m.getIsIdle());}
		else {if (this.getKeys() != null) {for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {this.timeUnlock  = System.currentTimeMillis() + 1000;}}} 
			else if (this.activate) {this.timeUnlock  = System.currentTimeMillis() + 1000;}
			this.setSwing(m, true);
		} this.lIsIdle = m.getIsIdle();
	}
	
	private void setSwing(MoveSet m, boolean swing) {((EightDirectionalMove) m).setForceWalk(!swing); ((EightDirectionalMove) m).setCanChange(swing); }

	@Override
	public boolean isActive() {return System.currentTimeMillis() < this.timeUnlock;}
	@Override
	public void setActive(boolean activate) {this.activate = activate;}

}
