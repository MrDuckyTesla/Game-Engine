package engine.physics.shapes;

import engine.app.Applet;
import engine.physics.Collision;
import engine.physics.Shape;

public class Rectangle implements Shape {
	
	private int wid, hgt;

	public Rectangle(int wid, int hgt) {
		this.wid = wid; this.hgt = hgt;
	}

	@Override
	public Collision getCollision(Shape other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void show(Applet app, int x, int y) {
		app.rect(x, y, this.wid, this.hgt);
	}
	
	public int getWid() {return this.wid;}
	
	public int getHgt() {return this.hgt;}

}
