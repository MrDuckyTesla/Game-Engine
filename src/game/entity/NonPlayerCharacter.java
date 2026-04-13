package game.entity;

import game.Room;
import game.entity.abilities.Ability;
import game.entity.movement.*;
import game.entity.trigger.Triggers;
import processing.core.PImage;

public class NonPlayerCharacter extends Entity{

//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public NonPlayerCharacter(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}

	public NonPlayerCharacter(Room room, PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		super(room, img, move, abilities, colorLayers, colorTints, isTangible, isBreakable);
		// TODO Auto-generated constructor stub
	}
	
	public NonPlayerCharacter(float x, float y, float w, float h) {
		super(null, null, new MoveSet[] {new ObjectAffectedMove(x, y, w, h)}, null, null, null, true, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interact(Triggers t) {
//		this.markDelete();
		
	}
	
	@Override
	public Entities getType() {return Entities.NON_PLAYER_CHARACTER;}

}
