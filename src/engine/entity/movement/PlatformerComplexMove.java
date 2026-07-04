package engine.entity.movement;

import engine.entity.Entity;
import engine.entity.Move;

// Version with Inverse Kinematics, and procedural Animation (basically it will actually just use the physics engine)
public class PlatformerComplexMove extends PlatformerSimpleMove {
	
	@Override
	public void move(Entity c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Move get() {
		// TODO Auto-generated method stub
		return new PlatformerComplexMove();
	}

}
