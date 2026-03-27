package move;

import java.util.ArrayList;
import entity.Obstacle;
import entity.Point;

public abstract class MoveSet {

	public MoveSet() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void move(ArrayList<Obstacle> room);
	
	public abstract boolean isCollide();
	
	public abstract int getMoveType();
	
	public abstract Point getDisplayCoords();

}
