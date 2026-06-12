package engine.entity.movement;

import engine.entity.AbstractEntity;
import engine.util.Point;
import engine.util.Rect;

public class BiDirectionalMove extends AbstractMove {
	
	private Rect xywh, bg;
	
	public BiDirectionalMove(Rect xywh, Rect bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public void move(AbstractEntity e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public AbstractMove get() {
		return new BiDirectionalMove(this.xywh.get(), this.bg.get());
	}

	@Override
	public float getX() {return this.xywh.getX();}
	@Override
	public float getY() {return this.xywh.getY();}
	@Override
	public float getW() {return this.xywh.getW();}
	@Override
	public float getH() {return this.xywh.getH();}
	@Override
	public float getS() {return 0;}
	@Override
	public float getSW() {return this.xywh.getW();}
	@Override
	public float getSH() {return this.xywh.getH();}
	
	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Moves getMoveType() {
		// TODO Auto-generated method stub
		return Moves.DUO;
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
