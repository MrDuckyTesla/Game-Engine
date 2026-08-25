package obsolete.entity.entities;

import obsolete.entity.Entity;
import obsolete.entity.Trigger;
import obsolete.entity.enums.Triggers;

public class Projectile extends Trigger {

	public Projectile(float x, float y, float w, float h, Entity caster) {
		super(x, y, w, h, caster);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Triggers getTriggerType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDelete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMarked() {
		// TODO Auto-generated method stub
		return false;
	}

}
