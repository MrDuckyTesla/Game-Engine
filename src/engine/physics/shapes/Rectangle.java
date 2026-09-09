package engine.physics.shapes;

import engine.app.App;
import engine.physics.Body;
import engine.physics.Collision;
import engine.physics.Shape;
import engine.physics.util.State;

public class Rectangle implements Shape {
	
	private int wid, hgt;

	public Rectangle(int wid, int hgt) {
		this.wid = wid; this.hgt = hgt;
	}
	
	@Override
	public void show(App app, int x, int y) {
		app.rect(x, y, this.wid, this.hgt);
	}
	
	public int getWid() {return this.wid;}
	
	public int getHgt() {return this.hgt;}

}
