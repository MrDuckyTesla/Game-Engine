package game;

import game.entity.Character;
import game.entity.Obstacle;
import game.entity.Point;
import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public class Animator {
	
	private PImage i;
	private MoveSet m;
	private Animation currAnim;
	private int animSpeed;
	private int start, frames, frameEnd;
	private boolean isLetChange, isNewAnim;

	public Animator() {
		this.currAnim = new Animation();
		this.isLetChange = false;
		this.isNewAnim = false;
	}
	
	public void update() {
		this.currAnim.animate(Point.getApp(), i, m.getX(), m.getY(), 28, 28, 3, this.start, this.start + this.frames - 1, this.animSpeed, false);
	}
	
	public void setAnim (PImage i, MoveSet m, int start, int frames, int animSpeed) {
		this.i = i; this.m = m; this.animSpeed = animSpeed;
		this.frames = frames; this.start = start + m.getDir() * this.frames;
	}
	
	public void setAnimSpeed(int speed) {this.animSpeed = speed;}
	
	public int getFrameEnd() {return 0;}
	
	public boolean canAnimate() {return this.m != null;}

}
