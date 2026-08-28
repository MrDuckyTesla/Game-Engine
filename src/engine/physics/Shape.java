package engine.physics;

public interface Shape {
	
	public abstract Collision getCollision(Shape other);
	
	public abstract void show(engine.app.Applet app, int x, int y);

}
