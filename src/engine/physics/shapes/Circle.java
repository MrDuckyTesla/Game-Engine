package engine.physics.shapes;

import engine.app.App;
import engine.physics.Shape;

public class Circle implements Shape {
	
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}
	
	@Override
	public void show(App app, int x, int y) {
		app.circ(x, y, this.radius);
	}
	
	public int getRadius() {return this.radius;}

}
