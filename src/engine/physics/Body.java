package engine.physics;

public interface Body {
	
	public abstract void show(engine.app.App app);
	
	public abstract engine.physics.util.State getState();
	
	public abstract Collision getCollision(Body other);

}
