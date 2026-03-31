package game.entity;

import game.ToolKit;
import game.entity.movement.Manager;
import game.entity.movement.MoveSet;
import processing.core.PApplet;

public class Obstacle extends Point {
	
	private MoveSet move = Manager.getMoveSet();
	private float w, h;
	private boolean isTangible, isMovable , isBreakable;
	
	public Obstacle() {super(); this.instantiate(0, 0);}
	public Obstacle(Point p) {super(p.getX(), p.getY()); this.instantiate(w, h);}
	public Obstacle(float x, float y) {super(x, y); this.instantiate(w, h);}
	public Obstacle(Point p, Point q) {super(p.compareTo(q) < 0? p : q); this.w = PApplet.abs(p.getX() - q.getX()); this.h = PApplet.abs(p.getY() - q.getY());}
	public Obstacle(Point p, float w, float h) {super(p.getX(), p.getY()); this.instantiate(w, h);}
	public Obstacle(float x, float y, float w, float h) {super(x, y); this.instantiate(w, h);}
	
	public Obstacle(MoveSet m) {super(m.getX(), m.getY()); this.instantiate(m, false, true, false);}
	public Obstacle(MoveSet s, boolean t, boolean m, boolean b) {super(s.getX(), s.getY()); this.instantiate(s, t, m, b);}
	public Obstacle(Point p, float w, float h, boolean t, boolean m, boolean b) {super(p.getX(), p.getY()); this.instantiate(null, t, m, b);}
	
	public boolean isTLInside(Obstacle other) {return ToolKit.pointRectCollide(getX(), getY(), other.getX(), other.getY(), other.getW(), other.getH());}
	public boolean isTRInside(Obstacle other) {return ToolKit.pointRectCollide(getX()+w, getY(), other.getX(), other.getY(), other.getW(), other.getH());}
	public boolean isBRInside(Obstacle other) {return ToolKit.pointRectCollide(getX()+w, getY()+h, other.getX(), other.getY(), other.getW(), other.getH());}
	public boolean isBLInside(Obstacle other) {return ToolKit.pointRectCollide(getX(), getY()+h, other.getX(), other.getY(), other.getW(), other.getH());}
	public boolean isInside(Obstacle other) {return this.isTLInside(other) && this.isTRInside(other) && this.isBRInside(other) && this.isBLInside(other);} // Checks if this has all four corners inside other
	
	private void instantiate(MoveSet s, boolean t, boolean m, boolean b) {if (s != null) {this.move = s; this.w = s.getW(); this.h = s.getH();} this.isTangible = t; this.isMovable = m; this.isBreakable = b;}
	private void instantiate(float w, float h) {this.instantiate(null, true, false, false); this.w = w; this.h = h;}
	
	public void update() {this.displayRect();}  // Function for children to inherit, will probably be used for animated obstacles
	public void interact() {}  // Another function for children to inherit, will probably be used for text box
	
	public boolean isCollide(Obstacle o) {return ToolKit.rectRectCollide(this.getX(), this.getY(), this.w, this.h, o.getX(), o.getY(), o.getW(), o.getH());}
	public boolean isTangible() {return this.isTangible;}
	public boolean isMovable() {return this.isMovable;}
	public boolean isBreakable() {return this.isBreakable;}
	
	public boolean displayRect() {if (!Point.getHasApp()) {return false;} Point.getApp().rect(getX(), getY(), w, h); return true;}
	public boolean displayRect(float s) {if (!Point.getHasApp()) {return false;} Point.getApp().rect(getX()*s, getY()*s, w*s, h*s); return true;}
	public boolean displayRect(PApplet app, float s) {app.rect(getX()*s, getY()*s, w*s, h*s); return true;}
	public boolean displayRect(PApplet app) {app.rect(getX(), getY(), w, h); return true;}
	public boolean displayRect(PApplet app, int[] color) {app.push(); app.fill(app.color(color[0], color[1], color[2])); app.rect(getX(), getY(), w, h); app.pop(); return true;}
	
	public boolean appRect(float w, float h) {Point.rectApp(this.getX(), this.getY(), this.w, this.h); return true;}
	
	// Get
	public float getW() {return this.w;}
	public float getH() {return this.h;}
	public float[] getXYWH() {return new float[] {getX(), getY(), w, h};}
	public float getArea() {return this.w * this.h;}
	public float getPerimeter() {return 2*this.w + 2*this.h;}
	public Point getPoint() {return super.get();}
	public Point getCorner(boolean topSide, boolean rightSide) {return new Point(rightSide? getX()+w : getX(), topSide? getY() : getY()+h);}
	public Point[] getCorners() {return new Point[] {getCorner(true, false), getCorner(true, true), getCorner(false, true), getCorner(false, false)};}
	public Entity getType() {return Entity.Obstacle;}
	public MoveSet getMoveSet() {return this.move;}
	
	// Overridden functions
	@Override
	public Obstacle get() {return new Obstacle(this.getX(), this.getY(), this.w, this.h);}
	@Override
	public boolean equals(Object other) {return this.getX() == ((Obstacle) other).getX() && this.getY() == ((Obstacle) other).getY() && this.w == ((Obstacle) other).getW() && this.h == ((Obstacle) other).getH();}
	@Override
	public String toString() {return "("+getX()+", "+getY() + ", "+w+", "+h+")";}
	@Override
	public int compareTo(Object o) {return (int) (getY() + getH() - ((Obstacle) o).getY() - ((Obstacle) o).getH());}

}
