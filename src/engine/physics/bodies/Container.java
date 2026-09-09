package engine.physics.bodies;

import java.util.ArrayList;

import engine.app.App;
import engine.physics.Body;
import engine.physics.Collision;
import engine.physics.Shape;
import engine.physics.util.State;

public class Container implements Body {
	
	public final ArrayList<Body> bodies = new ArrayList<>();
	private final State state;
	private final Shape shape;

	public Container(State state, Shape shape, Body... bodies) {
		this.state = state; this.shape = shape;
		for (Body b : bodies) {this.bodies.add(b);}
	}
	
	public void addBody(Body body) {this.bodies.add(body);}
	public void removeBody(Body body) {this.bodies.remove(body);}

	@Override
	public void show(App app) {
		this.shape.show(app);
		// Draw all bodies inside
		for (Body b : this.bodies) {b.show(app);}
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collision getCollision(Body other) {
		// TODO Auto-generated method stub
		return null;
	}

}
