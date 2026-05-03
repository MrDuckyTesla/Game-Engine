package engine.entity.trigger;

import engine.Room;
import engine.entity.*;
import engine.entity.abilities.AbstractAbility;
import engine.entity.movement.AbstractMove;
import engine.util.*;

public class Interaction extends Trigger {
	
	public static Interaction createInteraction(Room r, AbstractMove m, AbstractEntity e, AbstractAbility a, Triggers t) {
		float halfW = m.getSW()/2, halfH = m.getSH()/2;
		int dirInt = m.getDir(); Point xy = new Point();
		if (dirInt % 4 != 2) {xy.setX(dirInt % 7 < 2? halfW : -halfW);}
		if (dirInt % 4 != 0) {xy.setY(dirInt < 4? halfH : -halfH);}
		if (dirInt % 2 == 1) {xy.multpilyXY(0.7071068f);}  // sin 45
		return new Interaction(m.getX() + xy.getX() + halfW/2, m.getY() + xy.getY() + halfH/2, halfW, halfH, e, a, t);
	}
	
	private AbstractAbility a;
	private Triggers t;

	public Interaction(float x, float y, float w, float h, AbstractEntity i, AbstractAbility a, Triggers t) {
		super(x, y, w, h, i); this.a = a; this.t = t;
	}

	@Override
	public void update() {
		this.getMoveSet().move(null);
		for (AbstractEntity e : this.getCaster().getRoomList()) {
			if (e.getType() != Entity.TRIGGER && !this.getCaster().equals(e)) {
				if (ToolKit.rectRectCollide (
						this.getRX(), this.getRY(), this.getW(), this.getH(), 
						e.getRX(), e.getRY(), e.getW(), e.getH()
				)) {e.interact(this);}
			}
		}
	}
	
	@Override
	public void show() {
//		ToolKit.rectApp(this.getX(), this.getY(), getW(), getH());
	}

	@Override
	public Triggers getTriggerType() {return this.t;}

	@Override
	public boolean isDelete() {return !this.a.isActive() || this.getCaster() == null || this.getCaster().isDelete();}

	@Override
	public boolean isMarked() {return false;}
}
