package game;

import game.entity.Character;
import game.entity.Obstacle;
import game.entity.Point;
import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public class Animator {
	
	private PImage img;
	private MoveSet move;
	private Animation currAnim;
	private float animSpeed;
	private int start, frames, frameEnd;
	private boolean isLetChange, isNewAnim;

	public Animator() {
		this.img = null;
		this.currAnim = new Animation();
		this.isLetChange = false;
		this.isNewAnim = false;
	}
	
	public void update() {
		this.currAnim.animate(Point.getApp(), this.img, this.move.getPoint().getX(), this.move.getPoint().getY(), 28, 28, 3, this.start, this.start + this.frames - 1, 6, false);
	}
	
	public void changeAnimation(PImage i, MoveSet m, int start, int frames, float scale, int frameStart, int frameEnd, boolean changeAnimation, boolean resetIndex) {
		this.img = i; this.move = m; this.frames = frames; this.start = start + this.move.getDir() * this.frames; this.frameEnd = frameEnd;
	}
	
	public int getFrameEnd() {return 0;}

}
