package engine.entity.abilities;

import engine.entity.*;
import engine.entity.enums.*;

public class Walk8d extends Ability {
	
	private boolean lIsIdle = true;

	@Override
	public void update(Entity e, Move m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.EIGHT) {throw new IllegalArgumentException();}
		if (m.getIsIdle()) {e.getAnimator().setAnim(e.getImg(), m, 0, 2, 12);}
		else {e.getAnimator().setAnim(e.getImg(), m, 16, 4, 12, this.lIsIdle != m.getIsIdle());}
		this.lIsIdle = m.getIsIdle();
	}
	
	@Override
	public void setActive(boolean activate) {}

	@Override
	public boolean isActive() {return true;}
	
	@Override
	public Ability get() {return new Walk8d();}
	@Override
	public Abilities getType() {return Abilities.WALK_EIGHT_DIR;}

}
