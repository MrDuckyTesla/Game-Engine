package engine.physics;

public interface Force {
	
	public abstract float getMass();
	
	public abstract engine.physics.util.Vector2D getAcceleration();
	
}
