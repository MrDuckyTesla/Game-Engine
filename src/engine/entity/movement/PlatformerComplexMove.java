package engine.entity.movement;

import engine.entity.AbstractEntity;

// Version with Inverse Kinematics, and procedural Animation
public class PlatformerComplexMove extends PlatformerSimpleMove {
	
	@Override
	public void move(AbstractEntity c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public AbstractMove get() {
		// TODO Auto-generated method stub
		return new PlatformerComplexMove();
	}

}
