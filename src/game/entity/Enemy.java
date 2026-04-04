package game.entity;

import game.entity.abilities.Abilities;
import game.entity.abilities.Ability;
import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends NonPlayerCharacter {
	
	private long timeWander = 0;

//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public Enemy(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}
	
	public Enemy(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints) {
		super(img, move, abilities, colorLayers, colorTints);
	}

	@Override
	public void update() {
		super.update();
		this.wander();
//		if (this.getOverworld()) {
//		    // Check direction
//		    if (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) {this.setOverDir(0);}  // Walk Right
//		    else if (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) {this.setOverDir(2);}  // Walk Down
//		    else if (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) {this.setOverDir(4);}  // Walk Left
//		    else if (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) {this.setOverDir(6);}  // Walk Up
//		    else if ((ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) && (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40))) {this.setOverDir(1);}       // Walk Right - Down
//		    else if ((ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) && (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37))) {this.setOverDir(3);}  // Walk Down - Left
//		    else if ((ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) && (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38))) {this.setOverDir(5);}  // Left - Up
//		    else if ((ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) && (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39))) {this.setOverDir(7);}  // Up - Right
//		}
	}
	
	private void wander() {
		
		if (this.timeWander < System.currentTimeMillis()) {
			this.timeWander = System.currentTimeMillis() + Math.round(Math.random() * 1000);
			this.getMoveSet().setDir((int)(Math.random() * 8));
			this.getMoveSet().setIdle(Math.random() > 0.5);
			for (Ability a : this.getAbilities()) {
				if (a.getType() == Abilities.sword8d) {
					a.setActive(Math.random() > 0.99);
				} else {
					a.setActive(Math.random() > 0.8);
				}
			}
		}
	}
	
	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

}
