package engine.entity.movement;

import engine.entity.Entity;
import engine.entity.Move;
import engine.entity.enums.Moves;
import engine.util.Point;

public class ObjectAffectedMove extends Move {
	
	private float x, y, w, h;
	
	public ObjectAffectedMove(float x, float y, float w, float h) {this.x = x; this.y = y; this.w = w; this.h = h;}

	@Override
	public void move(Entity e) {
		// TODO Make object get moved around
	}

	@Override
	public float getX() {return this.x;}
	@Override
	public float getY() {return this.y;}
	@Override
	public float getW() {return this.w;}
	@Override
	public float getH() {return this.h;}
	@Override
	public float getS() {return 1;}
	@Override
	public float getSW() {return this.getW();}
	@Override
	public float getSH() {return this.getH();}
	
	@Override
	public Move get() {
		return new ObjectAffectedMove(x, y, w, h);
	}
	
	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return new Point(this.x, this.y);
	}

	@Override
	public Moves getMoveType() {
		// TODO Auto-generated method stub
		return Moves.OBJECT;
	}

	@Override
	public void setDir(int d) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean dirChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIdle(boolean i) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDir() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getIsIdle() {
		// TODO Auto-generated method stub
		return false;
	}

}
