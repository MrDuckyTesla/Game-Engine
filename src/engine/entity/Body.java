package engine.entity;

import engine.util.Vector2D;

public interface Body {
	
	public Vector2D update(Entity... entities);
	
}
