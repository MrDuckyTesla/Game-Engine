package engine.entity;

import engine.physics.util.Vector2D;

public interface Interaction {
	
	public Vector2D update(Entity... entities);
	
}
