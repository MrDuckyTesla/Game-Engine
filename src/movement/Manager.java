package movement;

import entity.Obstacle;

public class Manager {
	
	public static MoveSet getMoveSet() {
		return new ObjectAffectedMove();
	}
	
	public static MoveSet getMoveSet(Obstacle xywh, float maxSpeed) {
		return new EightDirectionalMove(xywh, maxSpeed);
	}

}
