package entity.movement;

import java.util.ArrayList;

import entity.Obstacle;
import entity.Point;
import entity.movement.abilities.Ability;

public abstract class MoveSet {
	
	public abstract void move(ArrayList<Obstacle> r, Obstacle c);
	public abstract void move(ArrayList<Obstacle> r, Obstacle c, Ability a);
	public abstract void move(ArrayList<Obstacle> r, Obstacle c, Ability[] a);
	
	public abstract Point getPoint();
	public abstract Moves getMoveType();
	
	public abstract void setDir(int d);
	public abstract void setIdle(boolean i);
	
	public abstract int getDir();
	public abstract boolean getIsIdle();

}