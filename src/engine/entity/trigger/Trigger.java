package engine.entity.trigger;

import engine.entity.*;
import engine.entity.movement.InteractionMove;

public abstract class Trigger extends AbstractEntity {
	
	private AbstractEntity caster;
	
	public Trigger(float x, float y, float w, float h, AbstractEntity caster) {
		super(null, null, new InteractionMove(x, y, w, h, caster.getMoveSet()), null, null, null, false, false);
		this.caster = caster;
	}
	
	public int getCastDir() {return this.caster.getOverDir();}
	
	public long getCastID() {return this.caster.getID();}
	
	public AbstractEntity getCaster() {return this.caster;}
	
	public abstract Triggers getTriggerType();
	
	@Override
	public abstract void update();
	
	@Override
	public Trigger getTrigger() {return this;}
	
	@Override
	public final void interact(Trigger t) {}
	
	@Override
	public final Entity getType() {return Entity.TRIGGER;}

}
