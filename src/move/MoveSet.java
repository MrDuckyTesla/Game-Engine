package move;

import java.util.ArrayList;
//import entity.Character;
import entity.Obstacle;
import entity.Point;

public abstract class MoveSet {
	
	public abstract void move(ArrayList<Obstacle> room, Obstacle c);
	
	public abstract Point getPoint();
	public abstract int getMoveType();
	
	public abstract void setDir(int d);
	public abstract void setIdle(boolean i);
	
	public abstract int getDir();
	public abstract boolean getIsIdle();

}
