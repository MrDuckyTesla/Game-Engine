package engine.physics.shapes;

import engine.app.App;
import engine.physics.Body;
import engine.physics.Collision;
import engine.physics.util.State;

public class Circle implements Body {
	
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public Collision getCollision(Body other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void show(App app) {
//		app.circ(x, y, this.radius);
	}
	
	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getRadius() {return this.radius;}

}
