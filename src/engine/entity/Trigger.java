package engine.entity;

import engine.entity.enums.Entities;
import engine.entity.enums.Triggers;
import engine.entity.movement.Interaction;

public abstract class Trigger extends Entity {
	
	private Entity caster;
	
	public Trigger(float x, float y, float w, float h, Entity caster) {
		super(null, null, new Interaction(x, y, w, h, caster.getMoveSet()), null, null, null, false, false);
		this.caster = caster;
	}
	
	public int getCastDir() {return this.caster.getOverDir();}
	
	public long getCastID() {return this.caster.getID();}
	
	public Entity getCaster() {return this.caster;}
	
	public abstract Triggers getTriggerType();
	
	@Override
	public abstract void update();
	
	@Override
	public Trigger getTrigger() {return this;}
	
	@Override
	public final void interact(Trigger t) {}
	
	@Override
	public final Entities getType() {return Entities.TRIGGER;}

}
