package move;

import java.util.ArrayList;

import entity.Character;
import entity.Obstacle;
import entity.Point;
import game.ToolKit;

public class EightDirectionalMove extends MoveSet {
	
	private final static int MOVE_TYPE = 1;
	private int state = 0, dir = 0;
	private Obstacle xywh, bg;
	
	public EightDirectionalMove(Obstacle xywh, Obstacle bg) {
		this.xywh = xywh; this.bg = bg;
	}

	@Override
	public Point move(ArrayList<Obstacle> room, Character c) {
		
		for (Obstacle o : room) {
			if (o != c && o.isTangible()) {
//				if (ToolKit.rectRectCollide(this.xywh.getX(), this.xywh.getY())) {
//					
//				}
			}
		}
		
		
		return new Point();
	}

	@Override
	public int getMoveType() {return EightDirectionalMove.MOVE_TYPE;}
	
	public Point calculateSpeed(float speed) {
		Point s = new Point();
		if (this.dir % 2 == 1) {speed *= 0.7071068f;}  // sin 45
		if (this.dir % 4 != 2) {s.setX(this.dir % 7 < 2? speed : -speed);}
		if (this.dir % 4 - 1 != -1) {s.setY(this.dir < 4? speed : -speed);}
		return s;
	}
	
	public void setDir(int d) {this.dir = d;}
	public void setState(int s) {this.state = s;}

}
