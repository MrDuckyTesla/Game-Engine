package engine.entity.abilities;

import engine.entity.AbstractEntity;
import engine.entity.movement.AbstractMove;
import engine.entity.movement.Move;

public class Walk8d extends AbstractAbility {
	
	private boolean lIsIdle = true;

	@Override
	public void update(AbstractEntity e, AbstractMove m) throws IllegalArgumentException {
		if (m.getMoveType() != Move.EIGHT) {throw new IllegalArgumentException();}
		if (m.getIsIdle()) {e.getAnimator().setAnim(e.getImg(), m, 0, 2, 12);}
		else {e.getAnimator().setAnim(e.getImg(), m, 16, 4, 12, this.lIsIdle != m.getIsIdle());}
		this.lIsIdle = m.getIsIdle();
	}
	
	@Override
	public void setActive(boolean activate) {}

	@Override
	public boolean isActive() {return true;}
	
	@Override
	public AbstractAbility get() {return new Walk8d();}
	@Override
	public Ability getType() {return Ability.WALK_EIGHT_DIR;}

}
