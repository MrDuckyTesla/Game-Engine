package game.entity;

import java.util.ArrayList;
import game.ToolKit;
import game.entity.abilities.Ability;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;
import processing.core.PImage;

public abstract class Character extends Obstacle {
	
	private static int id = 0;
	private int charID;
	
	private int[][][] colorLayers;
	private int[] colorTints;
	private ArrayList<ArrayList<Integer>> colorLists = new ArrayList<ArrayList<Integer>>();
	private PImage[] images;
	
	private MoveSet[] move;
	private int currMove = 0, totalStates = 0;
	
	// THIS CLASS WILL BE ABSTRACT AND ONLY CONTAIN NECESSARY VARIABLES AND FUNCTIONS THAT APPLY TO ALL CHARACTERS 
	
	public Character(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints) {
		super(move[0], abilities);
		this.instantiate(img, move, colorLayers, colorTints);
	}
	
	private void instantiate(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints) {
		this.charID = Character.id; Character.id++;
		this.totalStates = move.length;
		this.images = new PImage[this.totalStates];
		this.move = new MoveSet[this.totalStates];
		for (int i = 0; i < this.totalStates; i++) {
			this.images[i] = img[i].copy();
			this.move[i] = move[i].get();
		}
		
		this.colorLayers = colorLayers;
		this.colorTints = colorTints;
		
		for (int i = 0; i < move.length; i++) {this.colorLists.add(ToolKit.PreCompile(Point.getApp(), this.images[i], this.colorLayers[i]));}
		ToolKit.changeColor(Point.getApp(), this.images[0], this.colorLists.get(0), this.colorTints);
	}
	
	@Override
	public void update() {	
		this.move[this.currMove].move(Obstacle.getRoom(), this, this.getAbilities());
	    this.set(this.getMoveSet().getPoint());
		
	}
	
	// Get
	public int getOverDir() {return this.move[this.currMove].getDir();}
	public boolean getOverState() {return this.move[this.currMove].getIsIdle();}
	// Set
	protected void setOverState(boolean isIdle) {this.move[this.currMove].setIdle(isIdle);}
	protected void setOverDir(int dir) {this.move[this.currMove].setDir(dir);}
	
	// Overridden functions
	@Override
	public abstract void interact();
	@Override
	public abstract Entity getType();
	@Override
	public float getX() {return this.getMoveSet().getX();}
	@Override
	public float getY() {return this.getMoveSet().getY();}
	@Override
	public float getW() {return this.getMoveSet().getSW();}
	@Override
	public float getH() {return this.getMoveSet().getSH();}
	@Override
	public float[] getXYWH() {return new float[] {this.getX(), this.getY(), this.getW(), this.getH()};}
	@Override
	public String toString() {return "("+this.getX()+", "+this.getY() + ", "+this.getW()+", "+this.getH()+")";}
	@Override
	public boolean equals(Object other) {if(other.getClass() != this.getClass()) {return false;} return this.charID == ((Character) other).charID;}
	@Override
	public boolean isTangible() {return false;}
	@Override
	public MoveSet getMoveSet() {return this.move[this.currMove];}
	@Override
	public Moves getMoveSetType() {return this.move[this.currMove].getMoveType();}
	@Override
	public PImage getImg() {return this.images[this.currMove];};

}
