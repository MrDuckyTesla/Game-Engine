package game.entity.movement;

import java.util.ArrayList;

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
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Obstacle getBox() {
		return null;
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
