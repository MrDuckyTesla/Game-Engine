package game.entity.movement;

import java.util.ArrayList;

import game.entity.Entity;
import game.entity.Point;

// Version with Inverse Kinematics, and procedural Animation
public class PlatformerComplexMove extends PlatformerSimpleMove {
	
	@Override
	public void move(ArrayList<Entity> room, Entity c, float[] bg, Point xy) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public MoveSet get() {
		// TODO Auto-generated method stub
		return new PlatformerComplexMove();
	}

}
