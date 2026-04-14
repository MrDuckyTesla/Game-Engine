package game.entity;

import java.util.ArrayList;
import processing.core.PImage;
import game.util.*;
import game.entity.trigger.*;
import game.entity.movement.*;
import game.Room;
import game.entity.abilities.Ability;

public abstract class Entity implements Comparable<Entity> {
	
	// Static variables
	private static final int[] hurtColor = new int[] {255, 0, 0, 255, 0, 0, 255, 200, 0};
	private static long id = 0;
	
	// Instance variables
	private ArrayList<ArrayList<Integer>> colorLists = new ArrayList<ArrayList<Integer>>();
	private int totalStates, currMove = 0, framesDie = 10, deathDir = 0;
	private boolean isTangible, isBreakable, isMarked = false;
	private int[][][] colorLayers;
	private Ability[] abilities;
	private int[] colorTints;
	private PImage[] images;
	private MoveSet[] move;
	private Room currRoom;
	private Point showXY;
	private long entID;
	
	// THIS CLASS WILL BE ABSTRACT AND ONLY CONTAIN NECESSARY VARIABLES AND FUNCTIONS THAT APPLY TO ALL CHARACTERS 
	
	public Entity(Room room, PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {  // Allow pre-computing color list outside class and using it here in constructor
		this.entID = Entity.id; Entity.id++; this.showXY = new Point(); this.currRoom = room;
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
			this.abilities = new Ability[0]; this.isTangible = isTangible; this.isBreakable = isBreakable;
		}
	}
	
	public void update() {	
		this.move[this.currMove].move(this, this.abilities, this.showXY);
	    this.showXY.set(this.getMoveSet().getPoint());
	    if (this.isMarked) {this.framesDie--;}
	}
	
	public final void markDelete() {this.isMarked = true; ToolKit.changeColor(ToolKit.getApp(), this.images[0], this.colorLists.get(0), hurtColor);}
	public final boolean isMarked() {return this.isMarked;}
	public final boolean isDelete() {return this.framesDie < 0;}
	public boolean isTangible() {return this.isTangible;}
	public boolean isBreakable() {return this.isBreakable;}
	
	// Abstract methods
	public abstract void interact(Triggers t);
	public abstract Entities getType();
	
	// Getter methods
	public boolean getOverState() {return this.move[this.currMove].getIsIdle();}
	public int getOverDir() {return this.move[this.currMove].getDir();}
	public int getDeathDir() {return this.deathDir;}
	public float getX() {return this.getMoveSet().getX();}
	public float getY() {return this.getMoveSet().getY();}
	public float getW() {return this.getMoveSet().getSW();}
	public float getH() {return this.getMoveSet().getSH();}
	public Room getRoom() {return this.currRoom;}
	public ArrayList<Entity> getRoomList() {return this.currRoom.getRoom();}
	public int getRW() {return this.currRoom.getImageWidth();}
	public int getRH() {return this.currRoom.getImageHeight();}
	public float[] getXYWH() {return new float[] {this.getX(), this.getY(), this.getW(), this.getH()};}
	public Point getPotential() {return this.getMoveSetType() == Moves.eightDirectional? ((EightDirectionalMove) this.getMoveSet()).getMoveDist() : new Point();}
	public Point getXY() {return this.getMoveSet().getPoint();}
	public MoveSet getMoveSet() {return this.move[this.currMove];}
	public PImage getImg() {return this.images[this.currMove];};
	public Ability[] getAbilities() {return this.abilities;}
	public Moves getMoveSetType() {return this.move[this.currMove].getMoveType();}
	
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
	public String toString() {return "("+this.getX()+", "+this.getY() + ", "+this.getW()+", "+this.getH()+")"+", Type \""+this.getType()+"\"";}
	@Override
	public boolean equals(Object other) {if(other.getClass() != this.getClass()) {return false;} return this.entID == ((Entity) other).entID;}

}
