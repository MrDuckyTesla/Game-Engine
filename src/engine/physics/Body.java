package engine.physics;

public interface Body {
	
	public abstract Collider[] getColliders();
	
	public abstract void show(engine.app.Applet app);
	
	public abstract engine.physics.util.State getState();
	
	

}
