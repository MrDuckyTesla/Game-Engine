package game.entity.movement;

import game.Animator;
import game.entity.Entity;
import game.entity.Point;
import game.entity.abilities.Ability;

public abstract class MoveSet {
	
	public abstract void move(Entity e, Point xy);
	
	public void move(Entity e, Ability ab, Point xy) {this.move(e, xy); ab.update(e, this);}
	public void move(Entity e, Ability[] ab, Point xy) {this.move(e, xy); for (Ability a : ab) {a.update(e, this);}}
	
	public abstract Animator getAnimator();
	
	public abstract MoveSet get();
	public abstract float getX();
	public abstract float getY();
	public abstract float getW();
	public abstract float getH();
	public abstract float getS();
	public abstract float getSW();
	public abstract float getSH();
	public abstract Point getPoint();
	public abstract Moves getMoveType();
	
	public abstract void setDir(int d);
	public abstract void setIdle(boolean i);
	
	public abstract int getDir();
	public abstract boolean dirChanged();
	public abstract boolean getIsIdle();

}