package game.entity.movement;

import java.util.ArrayList;

import game.entity.Obstacle;
import game.entity.abilities.Ability;

// Version with Inverse Kinematics, and procedural Animation
public class PlatformerComplexMove extends PlatformerSimpleMove {
	
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public MoveSet get() {
		// TODO Auto-generated method stub
		return new PlatformerComplexMove();
	}

}
