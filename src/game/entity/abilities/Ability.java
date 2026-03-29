package game.entity.abilities;

import game.entity.Obstacle;
import game.entity.movement.MoveSet;

public abstract class Ability {
	
	private int[] keys;

	public Ability(int key) {this.keys = new int[] {key};}
	public Ability(int[] keys) {this.keys = keys;}
	
	public abstract void update(Obstacle o, MoveSet m) throws IllegalArgumentException;
	
	public abstract boolean isActive();
	
	public int[] getKeys() {return this.keys;}
	
	public void setKeys(int key) {this.keys = new int[] {key};}
	public void setKeys(int[] keys) {this.keys = keys;}
}
