package engine.physics.shapes;

import engine.app.Applet;
import engine.physics.Collision;
import engine.physics.Shape;

public class Circle implements Shape {
	
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public Collision getCollision(Shape other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void show(Applet app, int x, int y) {
		app.circ(x, y, this.radius);
	}
	
	public int getRadius() {return this.radius;}

}
