package engine.physics.util;

public class State {
	
	private Vector2D pos, vel, acc;

	public State() {
		this.pos = new Vector2D();
		this.vel = new Vector2D();
		this.acc = new Vector2D();
	}
	
	public State(Vector2D pos) {
		this.pos = pos; 
		this.vel = new Vector2D();
		this.acc = new Vector2D();
	}
	
	public State(Vector2D pos, Vector2D vel) {
		this.pos = pos; this.vel = vel;
		this.acc = new Vector2D();
	}
	
	public State(Vector2D pos, Vector2D vel, Vector2D acc) {
		this.pos = pos; this.vel = vel; this.acc = acc;
	}
	
	public Vector2D getPos() {return this.pos;}
	public Vector2D getVel() {return this.vel;}
	public Vector2D getAcc() {return this.acc;}
	
	public void setPos(Vector2D pos) {this.pos = pos;}
	public void setVel(Vector2D vel) {this.vel = vel;}
	public void setAcc(Vector2D acc) {this.acc = acc;}
	
	public void addPos(Vector2D pos) {this.pos.addX(pos.getX()); this.pos.addY(pos.getY());}
	public void addVel(Vector2D vel) {this.vel.addX(vel.getX()); this.vel.addY(vel.getY());}
	public void addAcc(Vector2D acc) {this.acc.addX(acc.getX()); this.acc.addY(acc.getY());}

}
