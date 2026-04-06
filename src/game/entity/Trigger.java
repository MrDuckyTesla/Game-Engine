package game.entity;

import game.entity.abilities.Ability;
import game.entity.movement.MoveSet;
import processing.core.PImage;

public abstract class Trigger extends Entity {

	public Trigger(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints) {
		super(img, move, abilities, colorLayers, colorTints);
		// TODO Auto-generated constructor stub
	}

}
