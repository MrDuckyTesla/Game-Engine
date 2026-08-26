package engine.physics;

public interface Body {
	
	public abstract Collider getCollider();
	
	public abstract Force getForce();

}
