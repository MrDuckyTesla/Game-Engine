package game.entity.movement;

import java.util.ArrayList;

import game.entity.Character;
import game.entity.Obstacle;
import game.entity.Point;
import game.entity.abilities.Ability;

public class BiDirectionalMove extends MoveSet {
	
	private Obstacle xywh, bg;
	
	public BiDirectionalMove(Obstacle xywh, Obstacle bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		// TODO Auto-generated method stub
	}

	@Override
	public float getX() {return this.xywh.getX();}
	@Override
	public float getY() {return this.xywh.getY();}
	@Override
	public float getW() {return this.xywh.getW();}
	@Override
	public float getH() {return this.xywh.getH();}
	
	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Obstacle getObstacle() {
		return null;
	}

	@Override
	public Moves getMoveType() {
		// TODO Auto-generated method stub
		return Moves.biDirectional;
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
