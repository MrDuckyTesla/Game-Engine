package game.entity.movement;

import java.util.ArrayList;

import game.entity.Obstacle;
import game.entity.Point;
import game.entity.abilities.Ability;

// Legacy version without procedural animation
public class PlatformerSimpleMove extends MoveSet {
	
	Point coords = new Point();
	private Obstacle xywh;
	private float impulse, friction, speed, accelerationY;
	
	public PlatformerSimpleMove() {
		this.xywh = new Obstacle(0, 0, 9, 13);
		this.impulse = 17; this.friction = 0.8f;
		this.speed = 2.5f; this.accelerationY = 1;
	}
	
//	public PlatformerMove(Obstacle xywh, Obstacle bg) {
//		this.xywh = xywh; this.bg = bg;
//	}

	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
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
		return Moves.platformer;
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
