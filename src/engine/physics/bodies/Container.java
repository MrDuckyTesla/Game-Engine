package engine.physics.bodies;

import java.util.ArrayList;

import engine.app.Applet;
import engine.physics.Body;
import engine.physics.Collider;
import engine.physics.util.State;

public class Container implements Body {
	
	private final Collider[] colliders;
	public ArrayList<Body> bodies = new ArrayList<>();

	public Container(Collider...colliders) {
		this.colliders = colliders;
	}
	
	public void addBody(Body body) {
		this.bodies.add(body);
	}
	
	public void removeBody(Body body) {
		this.bodies.remove(body);
	}

	@Override
	public Collider[] getColliders() {
		return this.colliders;
	}

	@Override
	public void show(Applet app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}

}
