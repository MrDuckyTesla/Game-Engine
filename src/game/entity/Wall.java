package game.entity;

import game.Room;
import game.entity.abilities.Ability;
import game.entity.movement.*;
import game.entity.trigger.Trigger;
import processing.core.PImage;

public class Wall extends NonPlayerCharacter{

	public Wall(Room room, PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		super(room, img, move, abilities, colorLayers, colorTints, isTangible, isBreakable);
	}
	
	public Wall(float x, float y, float w, float h) {
		super(null, null, new MoveSet[] {new ObjectAffectedMove(x, y, w, h)}, null, null, null, true, true);
	}

	@Override
	public void interact(Trigger t) {
		
	}
	
	@Override
	public Entities getType() {return Entities.NON_PLAYER_CHARACTER;}

	@Override
	public boolean isDelete() {return false;}

}
