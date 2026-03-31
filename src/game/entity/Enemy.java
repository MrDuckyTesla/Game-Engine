package game.entity;

import game.ToolKit;
import game.entity.movement.MoveSet;
import processing.core.PImage;

public class Enemy extends NonPlayerCharacter {

//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public Enemy(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}
	
	public Enemy(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints, int[] scale) {
		super(img, move, colorLayers, colorTints, scale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		super.update();
		
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
	
	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

}
