package engine.entity.movement;

import engine.entity.AbstractEntity;
import engine.util.Point;

public class InteractionMove extends AbstractMove {
	
	private float x, y, w, h;
	private AbstractMove m;

	public InteractionMove(float x, float y, float w, float h, AbstractMove m) {
		this.x = x; this.y = y; this.w = w; this.h = h; this.m = m;
	}

	@Override
	public void move(AbstractEntity e) {
		
		// ONLY HAVE TO DO THIS MATH ON DIRECTION CHANGES
		
		float halfW = m.getSW()/2, halfH = m.getSH()/2;
		int dirInt = m.getDir(); Point xy = new Point();
		if (dirInt % 4 != 2) {xy.setX(dirInt % 7 < 2? halfW : -halfW);}
		if (dirInt % 4 != 0) {xy.setY(dirInt < 4? halfH : -halfH);}
		if (dirInt % 2 == 1) {xy.multpilyXY(0.7071068f);}  // sin 45
		this.x = m.getX() + xy.getX() + halfW/2;
		this.y = m.getY() + xy.getY() + halfH/2;
	}

	@Override
	public AbstractMove get() {
		// TODO Auto-generated method stub
		return new InteractionMove(x, y, w, h, m);
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
	public float getSW() {return this.w;}
	@Override
	public float getSH() {return this.h;}

	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return new Point(x, y);
	}

	@Override
	public Moves getMoveType() {
		// TODO Auto-generated method stub
		return Moves.INTERACT;
	}

	@Override
	public void setDir(int d) {
		// TODO Auto-generated method stub

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
	public boolean dirChanged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getIsIdle() {
		// TODO Auto-generated method stub
		return false;
	}

}
