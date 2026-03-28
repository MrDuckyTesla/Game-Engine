package move;

import java.util.ArrayList;
import entity.Character;
import entity.Obstacle;
import entity.Point;

public abstract class MoveSet {
	
	public abstract Point move(ArrayList<Obstacle> room, Character c);
	
	public abstract int getMoveType();

}
