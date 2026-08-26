package engine.entity;

import engine.physics.Vector2D;

public interface Body {
	
	public Vector2D update(Entity... entities);
	
}
