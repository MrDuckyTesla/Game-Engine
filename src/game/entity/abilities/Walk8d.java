package game.entity.abilities;

import game.entity.Obstacle;
import game.entity.Player;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;

public class Walk8d extends Ability {

	@Override
	public void update(Obstacle o, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		if (m.getIsIdle()) {m.getAnimator().setAnim(((Player) o).getImg(), m, 0, 2, 12);}
		else {m.getAnimator().setAnim(((Player) o).getImg(), m, 16, 4, 12);}
	}

	@Override
	public boolean isActive() {
		return true;
	}

}
