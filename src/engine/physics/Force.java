package engine.physics;

import engine.util.Vector2D;

public class Force {
	
	private Vector2D acc, vel, pos;

	public Force(Vector2D acc, Vector2D vel, Vector2D pos) {
		this.acc = acc; this.vel = vel; this.pos = pos;
	}

}
