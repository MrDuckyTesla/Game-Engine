package engine.physics.util;

public class Point implements Comparable<Object> {
	
	private float x, y;
	
	public Point() {this.instantiate(0, 0);}
	public Point(float x) {this.instantiate(x, 0);}
	public Point(Point p) {this.instantiate(p.getX(), p.getY());}
	public Point(float x, float y) {this.instantiate(x, y);}
	public Point(float[] coord) {this.instantiate(coord[0], coord[1]);}
	
	private void instantiate(float x, float y) {this.x = x; this.y = y;}
	
	public boolean isZero() {return this.x == 0 && this.y == 0;}
	
	public void resetX() {this.x = 0;}
	public void resetY() {this.y = 0;}
	public void resetPoint() {this.x = 0; this.y = 0;}
	public void negatePoint() {this.x *= -1; this.y *= -1;}
	
	// Get
	public Point get() {return new Point(this.x, this.y);}
	public Point getN() {return new Point(-this.x, -this.y);}
	public float getX() {return this.x;}
	public float getY() {return this.y;}
	public float[] getXY() {return new float[] {x, y};}
	public Point getAdd(Point p) {return new Point(this.x + p.getX(), this.y + p.getY());}
	public Point getSub(Point p) {return new Point(this.x - p.getX(), this.y - p.getY());}
	// Set
	public void set(Point p) {this.x = p.getX(); this.y = p.getY();}
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public void setX(Point x) {this.x = x.getX();}
	public void setY(Point y) {this.y = y.getY();}
	public void setXY(float x, float y) {this.x = x; this.y = y;}
	public void setXY(Point p) {this.x = p.getX(); this.y = p.getY();}
	// Add
	public void addX(float x) {this.x += x;}
	public void addY(float y) {this.y += y;}
	public void addXY(float x) {this.addX(x); this.addY(x);}
	public void addXY(float x, float y) {this.addX(x); this.addY(y);}
	public void addXY(Point p) {this.addX(p.getX()); this.addY(p.getY());}
	public void subXY(Point p) {this.addX(-p.getX()); this.addY(-p.getY());}
	// Multiply
	public void multpilyXY(float x) {this.x *= x; this.y *= x;}
	
	// Overridden functions
	@Override
	public int compareTo(Object o) {return Float.compare(getY(), ((Point) o).getY());}
	@Override
	public boolean equals(Object other) {return this.x == ((Point) other).getX() && this.y == ((Point) other).getY();}
	@Override
	public String toString() {return "("+x+", "+y+")";}
}
