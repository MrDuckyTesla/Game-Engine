package engine.physics.shapes;

import engine.app.App;
import engine.physics.Body;
import engine.physics.Collision;
import engine.physics.util.State;

public class Rectangle implements Body {
	
	private int wid, hgt;

	public Rectangle(int wid, int hgt) {
		this.wid = wid; this.hgt = hgt;
	}

	@Override
	public Collision getCollision(Body other) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void show(App app) {
//		app.rect(x, y, this.wid, this.hgt);
	}
	
	@Override
	public State getState() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getWid() {return this.wid;}
	
	public int getHgt() {return this.hgt;}

}
