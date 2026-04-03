package game.entity;

import game.entity.abilities.Ability;
import game.entity.movement.MoveSet;
import processing.core.PImage;

public class NonPlayerCharacter extends Character{
	
	private static Player p;

//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] colorTint) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer, colorTint);}
//	public NonPlayerCharacter(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(overPosit, battPosit, scale, overImage, battImage, overColorLayer, battColorLayer);}
//	public NonPlayerCharacter(Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {super(scale, overImage, battImage, overColorLayer, battColorLayer);}

	public NonPlayerCharacter(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints) {
		super(img, move, abilities, colorLayers, colorTints);
		// TODO Auto-generated constructor stub
	}
	
	public static void setPlayer(Player p) {
		if (NonPlayerCharacter.p == null) {
			NonPlayerCharacter.p = p;
		}
	}
	
	public Player getPlayer() {return NonPlayerCharacter.p;}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity getType() {return Entity.NonPlayerCharacter;}

}
