package move;

import java.util.ArrayList;
import entity.Character;
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
	public Point move(ArrayList<Obstacle> room, Character c) {
		// TODO Auto-generated method stub
		return new Point();
	}

	@Override
	public int getMoveType() {return GravityAffectedMove.MOVE_TYPE;}

}
