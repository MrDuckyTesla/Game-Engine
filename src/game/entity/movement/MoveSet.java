package game.entity.movement;

import java.util.ArrayList;
import game.Animator;
import game.entity.Obstacle;
import game.entity.Point;
import game.entity.abilities.Ability;

public abstract class MoveSet {
	
	public abstract void move(ArrayList<Obstacle> r, Obstacle o);
	
	public void move(ArrayList<Obstacle> r, Obstacle o, Ability ab) {this.move(r, o); ab.update(o, this);}
	public void move(ArrayList<Obstacle> r, Obstacle o, Ability[] ab) {this.move(r, o); for (Ability a : ab) {a.update(o, this);}}
	
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
	public abstract Obstacle getObstacle();
	public abstract Moves getMoveType();
	
	public abstract void setDir(int d);
	public abstract void setIdle(boolean i);
	
	public abstract int getDir();
	public abstract boolean dirChanged();
	public abstract boolean getIsIdle();

}