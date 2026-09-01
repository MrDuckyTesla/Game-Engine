package engine.physics.bodies;

import java.util.ArrayList;

import engine.app.Applet;
import engine.physics.Body;
import engine.physics.Shape;
import engine.physics.util.State;
import engine.physics.util.Vector2D;

public class Container implements Body {
	
	public final ArrayList<Body> bodies = new ArrayList<>();
	private final State state;
	private final Shape collider;

	public Container(State state, Shape col, Body... bodies) {
		this.state = state; this.collider = col;
		for (Body b : bodies) {this.bodies.add(b);}
	}
	
	public void addBody(Body body) {this.bodies.add(body);}
	public void removeBody(Body body) {this.bodies.remove(body);}

	@Override
	public Shape[] getColliders() {
		return new Shape[] {this.collider};
	}

	@Override
	public void show(Applet app) {
		Vector2D pos = state.getPos();
		this.collider.show(app, (int) pos.getX(), (int) pos.getY());
		// Draw all bodies inside
		for (Body b : this.bodies) {b.show(app);}
		
	}

	@Override
	public State getState() {return this.state;}

}
