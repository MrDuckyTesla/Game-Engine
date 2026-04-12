package game.entity.trigger;

import game.entity.Entities;
import game.entity.Entity;
import game.entity.movement.MoveSet;
import game.entity.movement.ObjectAffectedMove;

public abstract class Trigger extends Entity {
	
	private Entity caster;
	
	public Trigger(float x, float y, float w, float h, Entity caster) {
		super(null, new MoveSet[] {new ObjectAffectedMove(x, y, w, h)}, null, null, null, false, false);
		this.caster = caster;
	}
	
	public Entity getCaster() {return this.caster;}
	
	public int getCastDir() {return this.caster.getOverDir();}
	
	public abstract Triggers getTrigger();
	
	@Override
	public abstract void update();
	
	@Override
	public final void interact(Triggers t) {}
	
	@Override
	public final Entities getType() {return Entities.TRIGGER;}

}
