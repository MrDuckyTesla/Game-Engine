package engine.entity.abilities;

import engine.entity.*;
import engine.entity.movement.*;
import engine.entity.trigger.*;
import engine.util.*;

public class Interact8d extends AbstractAbility {
	
	private boolean isActive = false, sent = false;
	private Trigger currTrig = null;

	public Interact8d() {}
	public Interact8d(int key) {super(key);}
	public Interact8d(int[] keys) {super(keys);}
	
	@Override
	public Trigger getTrigger() {return this.currTrig;}
	@Override
	public boolean sentTrigger() {return this.sent;}

	@Override
	public void update(AbstractEntity e, AbstractMove m) throws IllegalArgumentException {
		if (m.getMoveType() != Move.EIGHT) {throw new IllegalArgumentException();}
		if (this.getKeys() != null) {
			this.isActive = false;
			for (int key : this.getKeys()) {if (ToolKit.keyIsDown(key)) {this.isActive = true;}}
		} 
		
		if (this.isActive) {
			this.currTrig = this.sent? null : Interaction.createInteraction(e.getRoom(), m, e, this, Triggers.INTERACT);
			this.sent = true;
		} else {this.currTrig = null; this.sent = false;}
		
	}
	
	@Override
	public void setActive(boolean activate) {this.isActive = activate;}
	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public AbstractAbility get() {return this.getKeys() == null? new Interact8d() : new Interact8d(this.getKeys());}
	@Override
	public Ability getType() {return Ability.INTERACT_EIGHT_DIR;}

}
