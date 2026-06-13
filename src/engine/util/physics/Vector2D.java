package engine.util.physics;

public class Vector2D {
	
	private float x, y;
	
	public Vector2D() {this.x = 0; this.y = 0;}
	public Vector2D(float x, float y) {this.x = x; this.y = y;}
	
	public float dot(Vector2D other) {
		return x*other.getX() + y*other.getY();
	}
	
	public Vector2D getNormal() {
		float hypot = x*x + y*y;
		if (hypot == 0) {return new Vector2D();}
		float overH = 1 / (float)Math.sqrt(hypot);
		return new Vector2D(x*overH, y*overH);
	}
	
	public float getX() {return this.x;}
	public float getY() {return this.y;}

}
