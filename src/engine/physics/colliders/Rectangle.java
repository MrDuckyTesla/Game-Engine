package engine.physics.colliders;

import engine.physics.Collider;

public class Rectangle implements Collider {
	
	private int wid, hgt;

	public Rectangle(int wid, int hgt) {
		this.wid = wid; this.hgt = hgt;
	}

	@Override
	public float collides(Collider other, float dt) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int getWid() {return this.wid;}
	
	public int getHgt() {return this.hgt;}

}
