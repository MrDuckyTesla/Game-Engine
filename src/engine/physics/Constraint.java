package engine.physics;

import engine.physics.bodies.Rigidbody;

public interface Constraint {
	
	public abstract Rigidbody getBodyA();
	
	public abstract Rigidbody getBodyB();
	
	public abstract void update();

}
