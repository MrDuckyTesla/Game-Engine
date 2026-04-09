package game.entity;

import java.util.ArrayList;
import game.Room;
import game.ToolKit;
import game.entity.abilities.Ability;
import game.entity.movement.*;
import processing.core.PImage;

public abstract class Entity implements Comparable<Entity> {
	
	// Static variables
	private static Room currRoom;
	private static int id = 0;
	
	// Static methods
	public static ArrayList<Entity> getRoom() {return Entity.currRoom.getRoom();}
	public static int getRW() {return Entity.currRoom.getImageWidth();}
	public static int getRH() {return Entity.currRoom.getImageHeight();}
	public static void setRoom(Room room) {Entity.currRoom = room;}
	
	// Instance variables
	private ArrayList<ArrayList<Integer>> colorLists = new ArrayList<ArrayList<Integer>>();
	private int entID, totalStates, currMove = 0;
	private boolean isTangible, isBreakable;
	private int[][][] colorLayers;
	private Ability[] abilities;
	private int[] colorTints;
	private PImage[] images;
	private MoveSet[] move;
	private Point showXY;
	
	// THIS CLASS WILL BE ABSTRACT AND ONLY CONTAIN NECESSARY VARIABLES AND FUNCTIONS THAT APPLY TO ALL CHARACTERS 
	
	public Entity(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		this.entID = Entity.id; Entity.id++; this.showXY = new Point();
		try {
			this.totalStates = move.length;
			this.images = new PImage[this.totalStates]; this.move = new MoveSet[this.totalStates];
			this.abilities = new Ability[abilities.length];
			for (int i = 0; i < Math.max(this.totalStates, abilities.length); i++) {
				if (i < this.totalStates) {this.images[i] = img[i].copy(); this.move[i] = move[i].get();} 
				if (i < abilities.length) {this.abilities[i] = abilities[i].get();}
			} this.colorLayers = colorLayers; this.colorTints = colorTints;
			for (int i = 0; i < move.length; i++) {this.colorLists.add(ToolKit.PreCompile(ToolKit.getApp(), this.images[i], this.colorLayers[i]));}
			ToolKit.changeColor(ToolKit.getApp(), this.images[0], this.colorLists.get(0), this.colorTints);
		} catch (NullPointerException e) {
			this.move = new MoveSet[] {new ObjectAffectedMove(move[0].getX(), move[0].getY(), move[0].getW(), move[0].getH())};
			this.abilities = new Ability[0];
			this.isTangible = true;
		}
	}
	
	public void update() {	
//		Point backCoords = Entity.currRoom.getBackCoords();
		this.move[this.currMove].move(this, this.abilities, this.showXY);
	    this.showXY.set(this.getMoveSet().getPoint());
	}
	
	// Abstract methods
	public abstract void interact();
	public abstract Entities getType();
	
	// Getter methods
	public boolean getOverState() {return this.move[this.currMove].getIsIdle();}
	public boolean isTangible() {return this.isTangible;}
	public boolean isBreakable() {return this.isBreakable;}
	public int getOverDir() {return this.move[this.currMove].getDir();}
	public float getX() {return this.getMoveSet().getX();}
	public float getY() {return this.getMoveSet().getY();}
	public float getW() {return this.getMoveSet().getSW();}
	public float getH() {return this.getMoveSet().getSH();}
	public Point getXY() {return this.getMoveSet().getPoint();}
	public float[] getXYWH() {return new float[] {this.getX(), this.getY(), this.getW(), this.getH()};}
	public MoveSet getMoveSet() {return this.move[this.currMove];}
	public PImage getImg() {return this.images[this.currMove];};
	public Ability[] getAbilities() {return this.abilities;}
	public Moves getMoveSetType() {return this.move[this.currMove].getMoveType();}
	public Point getPotential() {return this.getMoveSetType() == Moves.eightDirectional? ((EightDirectionalMove) this.getMoveSet()).getMoveDist() : new Point();}
	
	// Setter methods
	public void setOverState(boolean isIdle) {this.move[this.currMove].setIdle(isIdle);}
	public void setOverDir(int dir) {this.move[this.currMove].setDir(dir);}
	public void setX(float x) {this.showXY.setX(x);}
	public void setY(float y) {this.showXY.setY(y);}
	public void setXY(float x, float y) {this.showXY.setX(x); this.showXY.setY(y);}
	
	// Adder methods
	public void addX(float x) {this.showXY.addX(x);}
	public void addY(float y) {this.showXY.addY(y);}
	public void addXY(float x, float y) {this.showXY.addX(x); this.showXY.addY(y);}
	
	// Overridden methods
	@Override
	public int compareTo(Entity e) {return Float.compare(this.getY() + this.getH(), e.getY() + e.getH());}
	@Override
	public String toString() {return "("+this.getX()+", "+this.getY() + ", "+this.getW()+", "+this.getH()+")";}
	@Override
	public boolean equals(Object other) {if(other.getClass() != this.getClass()) {return false;} return this.entID == ((Entity) other).entID;}

}
