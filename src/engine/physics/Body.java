package engine.physics;

public interface Body {
	
	public abstract Shape[] getColliders();
	
	public abstract void show(engine.app.Applet app);
	
	public abstract engine.physics.util.State getState();

}
