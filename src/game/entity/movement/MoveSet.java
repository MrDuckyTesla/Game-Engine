package game.entity.movement;

import java.util.ArrayList;

import game.Animator;
import game.entity.Entity;
import game.entity.Point;
import game.entity.abilities.Ability;

public abstract class MoveSet {
	
	public abstract void move(ArrayList<Entity> r, Entity e, float[] bg, Point xy);
	
	public void move(ArrayList<Entity> r, Entity e, float[] bg, Ability ab, Point xy) {this.move(r, e, bg, xy); ab.update(e, this);}
	public void move(ArrayList<Entity> r, Entity e, float[] bg, Ability[] ab, Point xy) {this.move(r, e, bg, xy); for (Ability a : ab) {a.update(e, this);}}
	
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