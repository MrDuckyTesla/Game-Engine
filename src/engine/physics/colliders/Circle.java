package engine.physics.colliders;

import engine.physics.Collider;

public class Circle implements Collider {
	
	private int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public float collides(Collider other, float dt) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getRadius() {return this.radius;}

}
