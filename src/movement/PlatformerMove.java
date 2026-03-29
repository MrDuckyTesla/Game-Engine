package movement;

import java.util.ArrayList;
import abilities.Ability;
import entity.Obstacle;
import entity.Point;

public class PlatformerMove extends MoveSet {
	
	Point coords = new Point();
	private Obstacle xywh, bg;
	
	public PlatformerMove(Obstacle xywh, Obstacle bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c, Ability ability) {ability.update(c, this); this.move(room, c);}
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c, Ability[] abilities) {for (Ability a : abilities) {a.update(c, this);} this.move(room, c);}

	@Override
	public Point getPoint() {
		// TODO Auto-generated method stub
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
