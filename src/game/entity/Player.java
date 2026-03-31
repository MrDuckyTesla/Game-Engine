package game.entity;

import game.ToolKit;
import game.entity.movement.MoveSet;
import processing.core.PImage;

public class Player extends Character {

//	public Player(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
	
	public Player(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints, int[] scale) {
		super(img, move, colorLayers, colorTints, scale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		super.update();
		
		if (this.getOverworld()) {
//			if (ToolKit.keyIsDown(88)) {this.setOverState(2);} // Sword Swing
		    if (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39) || ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40) || ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37) || ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) {this.setOverState(false);}
		    else {this.setOverState(true);}
		    // Check direction
		    if ((ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) && (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40))) {this.setOverDir(1);}       // Walk Right - Down
		    else if ((ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) && (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37))) {this.setOverDir(3);}  // Walk Down - Left
		    else if ((ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) && (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38))) {this.setOverDir(5);}  // Left - Up
		    else if ((ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) && (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39))) {this.setOverDir(7);}  // Up - Right
		    else if (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) {this.setOverDir(0);}  // Walk Right
		    else if (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) {this.setOverDir(2);}  // Walk Down
		    else if (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) {this.setOverDir(4);}  // Walk Left
		    else if (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) {this.setOverDir(6);}  // Walk Up
		}
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity getType() {return Entity.Player;}

}
