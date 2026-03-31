package game.entity.movement;

import java.util.ArrayList;
import game.ToolKit;
import game.entity.Obstacle;
import game.entity.Point;
import processing.core.PApplet;

public class EightDirectionalMove extends MoveSet {
	
	private boolean isIdle = true, forceWalk = false, canChange = true;
	private int dir = 0, scale = 1;
	private float maxSpeed, currSpeed;  // How much object is allowed to move in a frame
	private Point totalDist = new Point();  // The total amount that the object has moved in a frame
	private Obstacle xywh;
	
	public EightDirectionalMove() {
		this.xywh = new Obstacle(0, 0, 28, 28); this.maxSpeed = 6;
	}
	
	public EightDirectionalMove(Obstacle xywh, float speed) {
		this.xywh = xywh; this.maxSpeed = speed*2; this.currSpeed = speed;
	}

	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		Point p = this.getPotential(); this.totalDist.resetPoint();
		for (Obstacle o : room) {
			if (o != c && o.isTangible()) {  // If o isn't c and o is tangible, then if c collides with o
				if (ToolKit.rectRectCollide(this.xywh.getX()+p.getX(), this.xywh.getY() + p.getY(), this.xywh.getW(), this.xywh.getH(), o.getX(), o.getY(), o.getW(), o.getH())) {
					if (p.getX() < 0) {if (this.setX(o.getX() + o.getW() + 0.0001f)) {p.resetX();}}
					else if (p.getX() > 0) {if (this.setX(o.getX() - c.getW() - 0.0001f)) {p.resetX();}}
					if (p.getY() < 0) {if (this.setY(o.getY() + o.getH() + 0.0001f)) {p.resetY();}}
					else if (p.getY() > 0) {if (this.setY(o.getY() - c.getH() - 0.0001f)) {p.resetY();}}
				} 
			} 
		} PApplet app = Point.getApp();  // If c collides with window border
		if (ToolKit.nRectRectCollide(this.xywh.getX()+p.getX(), this.xywh.getY() + p.getY(), this.xywh.getW(), this.xywh.getH(), 0, 0, app.width, app.height)) {
			if (p.getX() < 0) {if (this.setX(0.0001f)) {p.resetX();}}
			else if (p.getX() > 0) {if (this.setX(app.width - c.getW() - 0.0001f)) {p.resetX();}}
			if (p.getY() < 0) {if (this.setY(0.0001f)) {p.resetY();}}
			else if (p.getY() > 0) {if (this.setY(app.height - c.getH() - 0.0001f)) {p.resetY();}}
		} this.isIdle = p.isZero() && !this.forceWalk; this.xywh.addXY(p); this.setNormSpeed();
		this.showHitBox();
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
	public Point getPoint() {return this.xywh.getPoint();}
	@Override
	public Obstacle getObstacle() {return this.xywh.get();}

	@Override
	public Moves getMoveType() {return Moves.eightDirectional;}
	
	private Point getPotential() {
		Point s = new Point();
		if (this.isIdle) {return s;}
		float speed = this.currSpeed;
		if (this.dir % 2 == 1) {speed *= 0.7071068f;}  // sin 45
		if (this.dir % 4 != 2) {s.setX(this.dir % 7 < 2? speed : -speed);}
		if (this.dir % 4 != 0) {s.setY(this.dir < 4? speed : -speed);}
		return s;
	}
	
	public void halfSpeed() {this.currSpeed = this.currSpeed/2;}
	public void doubSpeed() {this.currSpeed = Math.min(this.maxSpeed, this.currSpeed*2);}
	public void setHalfSpeed() {this.currSpeed = this.maxSpeed/4;}
	public void setNormSpeed() {this.currSpeed = this.maxSpeed/2;}
	public void setDoubSpeed() {this.currSpeed = this.maxSpeed;}
	
	private boolean setX(float x) {if (this.setHelper(x, true)) {this.xywh.setX(x); return true;} return false;}
	private boolean setY(float y) {if (this.setHelper(y, false)) {this.xywh.setY(y); return true;} return false;}
	private boolean setHelper(float x, boolean isX) {return PApplet.abs((isX? this.xywh.getX() : this.xywh.getY()) - x) <= this.currSpeed;}
	
	public void showHitBox() {
		Point.pushApp();
		if (isIdle) {Point.fillApp(255, 0, 0);}
		Point.getApp().rect(xywh.getX(), xywh.getY(), xywh.getW(), xywh.getH());
		Point.popApp();
	}
	
	public void setForceWalk(boolean forceWalk) {this.forceWalk = forceWalk;}
	public void setCanChange(boolean canChange) {this.canChange = canChange;}
	
	@Override
	public void setDir(int d) {if (this.canChange) {this.dir = d;}}
	@Override
	public void setIdle(boolean i) {if (!this.forceWalk) {this.isIdle = i;}}
	@Override
	public int getDir() {return this.dir;}
	@Override
	public boolean getIsIdle() {return this.isIdle;}

}
