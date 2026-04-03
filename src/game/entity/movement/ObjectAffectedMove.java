package game.entity.movement;

import java.util.ArrayList;
import game.Animator;
import game.entity.Obstacle;
import game.entity.Point;
import game.entity.abilities.Ability;

public class ObjectAffectedMove extends MoveSet {
	
	public ObjectAffectedMove() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move(ArrayList<Obstacle> r, Obstacle c) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public Animator getAnimator() {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public float getX() {return 0;}
	@Override
	public float getY() {return 0;}
	@Override
	public float getW() {return 0;}
	@Override
	public float getH() {return 0;}
	@Override
	public float getS() {return 0;}
	@Override
	public float getSW() {return 0;}
	@Override
	public float getSH() {return 0;}
	
	@Override
	public MoveSet get() {
		return new ObjectAffectedMove();
	}
	
	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return new Point();
	}
	
	@Override
	public Obstacle getObstacle() {
		return new Obstacle();
	}

	@Override
	public Moves getMoveType() {
		// TODO Auto-generated method stub
		return Moves.objectAffected;
	}

	@Override
	public void setDir(int d) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public boolean dirChanged() {
		// TODO Auto-generated method stub
		return false;
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
