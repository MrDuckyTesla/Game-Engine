package game;

import game.entity.Point;
import game.entity.movement.MoveSet;
import processing.core.PImage;

public class Animator {
	
	private PImage i;
	private MoveSet m;
	private Animation currAnim;
	private int animSpeed;
	private int start, frames;
	private boolean changeAnim;

	public Animator() {
		this.currAnim = new Animation();
	}
	
	public void update() {
		this.currAnim.animate(Point.getApp(), i, m.getX(), m.getY(), (int) m.getW(), (int) m.getH(), m.getS(), this.start, this.start + this.frames - 1, this.animSpeed, false, this.changeAnim);
	}
	
	public void setAnim (PImage i, MoveSet m, int start, int frames, int animSpeed) {
		this.setAnim (i, m, start, frames, animSpeed, false);
	}
	
	public void setAnim (PImage i, MoveSet m, int start, int frames, int animSpeed, boolean change) {
		this.i = i; this.m = m; this.animSpeed = animSpeed; this.changeAnim = change;
		this.frames = frames; this.start = start + m.getDir() * this.frames;
	}
	
	public void setAnimSpeed(int speed) {this.animSpeed = speed;}
	
	public Animator get() {return new Animator();}
	
	public int getTotalFrames() {return this.currAnim.getTotalFrames();}
	
	public boolean canAnimate() {return this.m != null;}
	
	public boolean isDone() {
		return false;
	}

}
