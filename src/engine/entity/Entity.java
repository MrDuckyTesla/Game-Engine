package engine.entity;

import java.util.ArrayList;

import engine.Room;
import engine.entity.enums.Entities;
import engine.entity.enums.Moves;
import engine.entity.movement.*;
import engine.util.*;
import processing.core.PImage;

public abstract class Entity implements Comparable<Entity> {
	
	// Static variables
	private static long id = 0;
	public static final int[] hurtColor = new int[] {255, 0, 0, 255, 0, 0, 255, 200, 0};
	
	// Instance variables
	private boolean isTangible, isBreakable, isPushable, isKnockable;  // Make These MoveSet Exclusive
	private int hash;
	private long entID;
	private int[] colorTints;
	private int[][] colorLayer;
	private ArrayList<Integer> colorLists;
	private Animator anim;
	private Move moveset;
	private PImage image;
	private Point showXY;
	private Room currRoom;
	private Ability[] ability;
	
	// THIS CLASS WILL BE ABSTRACT AND ONLY CONTAIN NECESSARY VARIABLES AND FUNCTIONS THAT APPLY TO ALL CHARACTERS 
	
	public Entity(Room room, PImage img, Move move, Ability[] abilities, int[][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {  // Allow pre-computing color list outside class and using it here in constructor
		this.entID = Entity.id; Entity.id++; this.showXY = move.getPoint(); anim = new Animator(); this.currRoom = room; this.hash = 0;
		try {
			this.ability = new Ability[abilities.length]; this.image = img.copy(); this.moveset = move.get();
			for (int i = 0; i < abilities.length; i++) {this.ability[i] = abilities[i].get();} 
			this.colorLayer = colorLayers; this.colorTints = colorTints;
			
			if (this.colorLayer.length != 0) {
				this.colorLists = ToolKit.PreCompile(ToolKit.getApp(), this.image, this.colorLayer);
				ToolKit.changeColor(ToolKit.getApp(), this.image, this.colorLists, this.colorTints);
			}
		} catch (NullPointerException e) {
			this.moveset = move == null? new ObjectAffectedMove(move.getX(), move.getY(), move.getW(), move.getH()) : move;
			this.ability = new Ability[0]; this.isTangible = isTangible; this.isBreakable = isBreakable;
		}
	}
	
	public void update() {
		this.moveset.move(this, this.ability);
	}
	
	public void show() {if (this.anim.canAnimate()) {this.anim.update(this.showXY);}}
	
	public void calculateHash() {this.hash = ToolKit.hash((int)(this.getRX()/Room.CHUNK_SIZE), (int)(this.getRY()/Room.CHUNK_SIZE));}
	
	// Abstract methods
	public abstract void interact(Trigger t);
	public abstract boolean isDelete();  // Make This MoveSet Exclusive
	public abstract boolean isMarked();  // Make This MoveSet Exclusive
	public abstract Entities getType();
	public abstract Trigger getTrigger();
	
	// Getter methods
	public boolean isTangible() {return this.isTangible;}  // Make This MoveSet Exclusive
	public boolean isBreakable() {return this.isBreakable;}  // Make This MoveSet Exclusive
	public boolean getOverState() {return this.moveset.getIsIdle();}
	public int getOverDir() {return this.moveset.getDir();}
	public int getRW() {return this.currRoom.getImageWidth();}
	public int getRH() {return this.currRoom.getImageHeight();}
	public int getHash() {return this.hash;}
	public long getID() {return this.entID;}
	public float getRX() {return this.getMoveSet().getX();}
	public float getRY() {return this.getMoveSet().getY();}
	public float getX() {return this.showXY.getX();}
	public float getY() {return this.showXY.getY();}
	public float getW() {return this.getMoveSet().getSW();}
	public float getH() {return this.getMoveSet().getSH();}
	public float[] getXYWH() {return new float[] {this.getRX(), this.getRY(), this.getW(), this.getH()};}
	public Moves getMoveSetType() {return this.moveset.getMoveType();}
	public Animator getAnimator() {return this.anim;}
	public Move getMoveSet() {return this.moveset;}
	public PImage getImg() {return this.image;}
	public Point getPotential() {return this.getMoveSetType() == Moves.EIGHT? ((EightDirectionalMove) this.getMoveSet()).getMoveDist() : new Point();}
	public Point getXY() {return this.getMoveSet().getPoint();}
	public Room getRoom() {return this.currRoom;}
	public Ability[] getAbilities() {return this.ability;}
	public ArrayList<Integer> getColorList() {return colorLists;}
	public ArrayList<Entity> getRoomList() {return this.currRoom.getRoom(this);} // GET ONLY WHAT IS AROUND ENTITY
	
	
	// Setter methods
	public void setOverState(boolean isIdle) {this.moveset.setIdle(isIdle);}
	public void setOverDir(int dir) {this.moveset.setDir(dir);}
	public void setX(float x) {this.showXY.setX(x);}
	public void setY(float y) {this.showXY.setY(y);}
	public void setXY(float x, float y) {this.showXY.setX(x); this.showXY.setY(y);}
	
	// Adder methods
	public void addX(float x) {this.showXY.addX(x);}
	public void addY(float y) {this.showXY.addY(y);}
	public void addXY(float x, float y) {this.showXY.addX(x); this.showXY.addY(y);}
	
	// Implemented methods
	@Override
	public int compareTo(Entity e) {return Float.compare(this.getRY() + this.getH(), e.getRY() + e.getH());}
	
	// Overridden methods
	@Override
	public boolean equals(Object other) {if(other.getClass() != this.getClass()) {return false;} return this.entID == ((Entity) other).entID;}
	@Override
	public String toString() {return "("+this.getRX()+", "+this.getRY() + ", "+this.getW()+", "+this.getH()+")"+", Type \""+this.getType()+"\"";}

}
