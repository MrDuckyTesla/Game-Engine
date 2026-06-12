package engine.entity;

import engine.Room;
import engine.entity.abilities.AbstractAbility;
import engine.entity.movement.AbstractMove;
import engine.entity.trigger.Trigger;
import processing.core.PImage;

public class MultiStateEntity {
	
	private AbstractEntity[] entities;
	private int state = 0;

	public MultiStateEntity(Entities[] types, Room room[], PImage[] img, AbstractMove[] move, AbstractAbility[][] abilities, int[][][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		entities = new AbstractEntity[types.length];
		try {
			for (int i = 0; i < types.length; i++) {entities[i] = getType(types[i], room[i], img[i], move[i], abilities[i], colorLayers[i], colorTints, isTangible, isBreakable);}
		} catch (IndexOutOfBoundsException e) {throw new IllegalArgumentException();}
	}
	
	private AbstractEntity getType(Entities type, Room room, PImage img, AbstractMove move, AbstractAbility[] abilities, int[][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		switch (type) {
			case ENEMY:
				return new Enemy(room, img, move, abilities, colorLayers, colorTints);
			case NON_PLAYER_CHARACTER:
				return new NonPlayerCharacter(room, img, move, abilities, colorLayers, colorTints, isTangible, isBreakable);
			case PLAYER:
				return new Player(room, img, move, abilities, colorLayers, colorTints);
			default:
				return null;
		}
	}
	
	public AbstractEntity get() {return entities[this.state];}
	
	public void changeState(Trigger t) {this.state++;}

}
