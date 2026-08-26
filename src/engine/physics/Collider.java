package engine.physics;

public interface Collider {
	
	public abstract float collides(Collider other, float dt);

}
