package engine.entity.movement;

import java.util.ArrayList;

import engine.entity.AbstractEntity;
import engine.entity.abilities.AbstractAbility;
import engine.entity.trigger.Trigger;
import engine.util.Point;

public abstract class AbstractMove {
	
	private ArrayList<Trigger> triggers = new ArrayList<Trigger>();
	
//	public abstract void moveAs(Moves e);
	public abstract void move(AbstractEntity e);
	
	public final void move(AbstractEntity e, AbstractAbility ab) {this.reset(e); ab.update(e, this); this.check(ab);}
	public final void move(AbstractEntity e, AbstractAbility[] ab) {this.reset(e); for (AbstractAbility a : ab) {a.update(e, this); this.check(a);}}
	
	private void reset(AbstractEntity e) {this.triggers.clear(); this.move(e);}
	
	private void check(AbstractAbility ab) {
		Trigger t = ab.getTrigger();
		if (t != null) {triggers.add(t);}
	}
	
	public final ArrayList<Trigger> getTriggers() {return this.triggers;}
	
	public abstract AbstractMove get();
	public abstract float getX();
	public abstract float getY();
	public abstract float getW();
	public abstract float getH();
	public abstract float getS();
	public abstract float getSW();
	public abstract float getSH();
	public abstract Point getPoint();
	public abstract Move getMoveType();
	
	public abstract void setDir(int d);
	public abstract void setIdle(boolean i);
	
	public abstract int getDir();
	public abstract boolean dirChanged();
	public abstract boolean getIsIdle();

}