package game.entity;

import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public class NonPlayerCharacter extends Character{

//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public NonPlayerCharacter(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}

	public NonPlayerCharacter(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints, int[] scale) {
		super(img, move, colorLayers, colorTints, scale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity getType() {return Entity.NonPlayerCharacter;}

}
