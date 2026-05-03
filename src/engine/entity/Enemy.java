package engine.entity;

import engine.Room;
import engine.entity.abilities.*;
import engine.entity.movement.AbstractMove;
import processing.core.PImage;

public class Enemy extends NonPlayerCharacter {

//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public Enemy(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}
	
	public Enemy(Room room, PImage img, AbstractMove move, AbstractAbility[] abilities, int[][] colorLayers, int[] colorTints) {
		super(room, img, move, abilities, colorLayers, colorTints, false, true);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	public void update() {
//		
//	}
	
//	private void walkTowardsPlayer() {
//		
//	}
	
	@Override
	public Entity getType() {return Entity.ENEMY;}

}
