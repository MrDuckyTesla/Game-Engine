package game.entity;

import game.entity.movement.MoveSet;

public abstract class Trigger extends Obstacle {

	public Trigger() {
		// TODO Auto-generated constructor stub
	}

	public Trigger(Point p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	public Trigger(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Trigger(Point p, Point q) {
		super(p, q);
		// TODO Auto-generated constructor stub
	}

	public Trigger(Point p, float w, float h) {
		super(p, w, h);
		// TODO Auto-generated constructor stub
	}

	public Trigger(float x, float y, float w, float h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	public Trigger(MoveSet m) {
		super(m);
		// TODO Auto-generated constructor stub
	}

	public Trigger(MoveSet s, boolean t, boolean m, boolean b) {
		super(s, t, m, b);
		// TODO Auto-generated constructor stub
	}

	public Trigger(Point p, float w, float h, boolean t, boolean m, boolean b) {
		super(p, w, h, t, m, b);
		// TODO Auto-generated constructor stub
	}

}
