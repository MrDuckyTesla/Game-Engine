package move;

import java.util.ArrayList;
import entity.Character;
import entity.Obstacle;
import entity.Point;

public class BiDirectionalMove extends MoveSet {
	
	private final static int MOVE_TYPE = 0;
	private Obstacle xywh, bg;
	
	public BiDirectionalMove(Obstacle xywh, Obstacle bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public Point move(ArrayList<Obstacle> room, Character c) {
		// TODO Auto-generated method stub
		return new Point();
	}

	@Override
	public int getMoveType() {return BiDirectionalMove.MOVE_TYPE;}

}
