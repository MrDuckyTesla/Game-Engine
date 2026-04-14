package game.entity.trigger;

import game.entity.Entities;
import game.entity.Entity;
import game.entity.movement.MoveSet;
import game.entity.movement.ObjectAffectedMove;

public abstract class Trigger extends Entity {
	
	private Entity caster;
	
	public Trigger(float x, float y, float w, float h, Entity caster) {
		super(null, null, new MoveSet[] {new ObjectAffectedMove(x, y, w, h)}, null, null, null, false, false);
		this.caster = caster;
	}
	
	public int getCastDir() {return this.caster.getOverDir();}
	
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
