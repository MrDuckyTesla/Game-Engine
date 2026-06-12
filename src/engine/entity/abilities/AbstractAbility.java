package engine.entity.abilities;

import engine.entity.AbstractEntity;
import engine.entity.movement.AbstractMove;
import engine.entity.trigger.Trigger;

public abstract class AbstractAbility {
	
	private int[] keys;

	public AbstractAbility() {this.keys = null;}
	public AbstractAbility(int key) {this.keys = new int[] {key};}
	public AbstractAbility(int[] keys) {this.keys = keys;}
	
	public abstract void update(AbstractEntity e, AbstractMove m) throws IllegalArgumentException;
	
	public abstract void setActive(boolean activate);
	public abstract boolean isActive();
	
	/**
	 * Only override this if you want to create an interaction
	 * @return Returns the trigger being requested
	 */
	public Trigger getTrigger() {return null;}
	
	/**
	 * Only override this if you want to create an interaction
	 * @return Returns if the trigger has been made
	 */
	public boolean sentTrigger() {return true;}
	
	public abstract AbstractAbility get();
	
	public int[] getKeys() {return this.keys;}
	
	public void setKeys(int key) {this.keys = new int[] {key};}
	public void setKeys(int[] keys) {this.keys = keys;}
	
	public abstract Abilities getType();
}
