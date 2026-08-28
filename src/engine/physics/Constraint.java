package engine.physics;

public interface Constraint {
	
	public abstract Body getBodyA();
	
	public abstract Body getBodyB();
	
	public abstract void update();

}
