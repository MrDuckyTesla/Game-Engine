package engine.entity.util;

import engine.physics.util.Vector2D;

public class State {

	private Vector2D acc, vel, pos;

	public State(Vector2D acc, Vector2D vel, Vector2D pos) {
		this.acc = acc; this.vel = vel; this.pos = pos;
	}

}
