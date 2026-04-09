package game.entity.trigger;

import game.entity.Entities;
import game.entity.Entity;
import game.util.ToolKit;

public final class Interaction extends Trigger {
	
	Triggers t;

	public Interaction(float x, float y, float w, float h, Entity i, Triggers t) {
		super(x, y, w, h, i);
		this.t = t;
	}

	@Override
	public void update() {
		for (Entity e : Entity.getRoom()) {
			if (e.getType() != Entities.TRIGGER && !this.getCaster().equals(e)) {
				if (ToolKit.rectRectCollide (
						this.getX(), this.getY(), this.getW(), this.getH(), 
						e.getX(), e.getY(), e.getW(), e.getH()
				)) {e.interact(this.t);}
			}
		}
	}

	@Override
	public Triggers getTrigger() {return this.t;}

}
