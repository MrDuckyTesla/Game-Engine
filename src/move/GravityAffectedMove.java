package move;

import java.util.ArrayList;
import entity.Obstacle;
import entity.Point;

public class GravityAffectedMove extends MoveSet {
	
	private final static int MOVE_TYPE = 2;
	Point coords = new Point();
	private Obstacle xywh, bg;
	
	public GravityAffectedMove(Obstacle xywh, Obstacle bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMoveType() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean getIsIdle() {
		// TODO Auto-generated method stub
		return false;
	}


}
