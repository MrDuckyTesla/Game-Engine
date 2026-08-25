package obsolete.entity.movement;

import obsolete.entity.Entity;
import obsolete.entity.Move;

// Version with Inverse Kinematics, and procedural Animation (basically it will actually just use the physics engine)
public class PlatformerComplex extends PlatformerSimple {
	
	@Override
	public void move(Entity c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Move get() {
		// TODO Auto-generated method stub
		return new PlatformerComplex();
	}

}
