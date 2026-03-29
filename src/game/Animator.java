package game;

import game.entity.Character;
import game.entity.Obstacle;
import game.entity.Point;
import processing.core.PImage;

public class Animator {
	
	private Animation currAnim;
	private boolean isLetChange, isNewAnim;

	public Animator() {currAnim = new Animation();}
	
	public void update() {
		
	}
	
	public void changeAnimation(PImage i, Obstacle xywh, float scale, int frameStart, int frameEnd, int frame, boolean changeAnimation, boolean resetIndex) {
		if (this.isLetChange) {
//			this.animManager.animate(app, this.overImage, super.getX(), super.getY(), Character.OVER_WIDTH, Character.OVER_HEIGHT, this.scale.getX(), startReal, startReal + frames - 1, this.overAnimSpeed, this.changeAnim, this.overLastState != this.overCurrState);
		}
	}
	
	public int getFrameEnd() {return 0;}

}
