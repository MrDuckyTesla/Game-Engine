package obsolete.entity.entities;

import engine.Room;
import obsolete.entity.Ability;
import obsolete.entity.Move;
import obsolete.entity.abilities.*;
import obsolete.entity.enums.Entities;
import processing.core.PImage;

public class Enemy extends NPC {

//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public Enemy(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public Enemy(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}
	
	public Enemy(Room room, PImage img, Move move, Ability[] abilities, int[][] colorLayers, int[] colorTints) {
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
	public Entities getType() {return Entities.ENEMY;}

}
