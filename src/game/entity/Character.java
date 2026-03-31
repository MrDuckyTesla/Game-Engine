package game.entity;

import java.util.ArrayList;
import game.Animation;
import game.Room;
import game.ToolKit;
import game.entity.abilities.Ability;
import game.entity.movement.Manager;
import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public abstract class Character extends Obstacle {
	
	// Static variables
	final private static int OVER_WIDTH = 28, OVER_HEIGHT = 28, BATT_WIDTH = 9, BATT_HEIGHT = 13;
	private static int id = 0;
	// Constructor Variables
	public int charID;
	
	private PImage[] images;
	private ArrayList<Integer>[] colorLists;
	private int[][][] colorLayers;
	private int[] colorTints, scale;
	// Animation Variables
//	final private int OVER_ANIM_SPEED_CAP = 6;
//	private Animation animManager = new Animation();
//	private int[] animateState = new int[] {0, 0, 0, 0};
//	private int overAnimSpeed = 12, battAnimSpeed = 5;
//	private boolean changeAnim = false, unskipAnim = false;
	// World Variables
//	private float overScaledWidth, overScaledHeight, battScaledWidth, battScaledHeight;
//	private int overLastDir = 0, overCurrState = 1, overLastState = 0, battCurrDir = 0, battLastDir = 0, battCurrState = 0;
	private boolean overWorldCurr = true, overWorldLast = true;
	
	private MoveSet[] move;
	private int currMove = 0;
	
	// THIS CLASS WILL BE ABSTRACT AND ONLY CONTAIN NECESSARY VARIABLES AND FUNCTIONS THAT APPLY TO ALL CHARACTERS 
	
	public Character(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints, int[] scale) {
		super(move[0]);
		this.move = move;
		this.colorLayers = colorLayers;
		this.colorTints = colorTints;
		this.scale = scale;
//		this.instantiate(scale);
	}
	
//	private void instantiate(Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer) {
//		this.charID = Character.id; Character.id++; PApplet app = Point.getApp(); this.scale = scale.get();// this.battPositLast = battPosit.get();
//		this.overPosit = overPosit.get(); this.overImage = overImage.get(); this.battPosit = battPosit.get(); this.battImage = battImage.get();
//		this.overColorList = ToolKit.PreCompile(app, this.overImage, overColorLayer); this.battColorList = ToolKit.PreCompile(app,  this.battImage, battColorLayer);
//		ToolKit.changeColor(app,  this.overImage, overColorList, colorTint); ToolKit.changeColor(app, this.battImage, battColorList,colorTint);
//		
//		this.move = Manager.getMoveSet(this.get(), 3);
//	}
	
	public void move(ArrayList<Obstacle> r, Ability[] a) {
		this.move[this.currMove].move(r, this, a);
	}
	
	@Override
	public void update() {	
		// Check if we need to change the animation due to direction
//		if (this.overWorldLast != this.overWorldCurr) {this.changeAnim = false;}
//		else {this.changeAnim = this.overWorldCurr? this.overLastDir == this.move.getDir() : this.battLastDir == this.battCurrDir;}
//		if (this.overWorldCurr) {  // If in overworld
			
//			this.overStateCheck1(); // Check if sprinting or if doing sword animation
			// Overworld states start here
//			if (this.move.getIsIdle()) {this.overCurrState = 1;}
//			else {this.overCurrState = 3;}
			
//			if (this.overCurrState == 3) {this.animateMoveOver(16, 4, false, false);}  // Overworld Walk
//			if (this.overCurrState == 2) {this.animateMoveOver(48, 4, false, true);}  // Sword Swing (walking)
//			if (this.overCurrState == 1) {this.animateMoveOver(0, 2, true, false);}  // Overworld Idle
//			this.overStateCheck2();  // Reset Sprinting speed if sprinting
			
//		} 
		
		// Update last state variables
//	    this.overLastDir = this.move.getDir();
//	    this.overWorldLast = this.overWorldCurr;
//	    this.overLastState = this.overCurrState;
//	    this.battLastDir = this.battCurrDir;
	    this.set(this.getMoveSet().getPoint());
		
	}
	
//	public void showHitBox() {Point.pushApp(); Point.fillApp(255, 0, 0); Point.rectApp(this.overPosit.getX(), this.overPosit.getY(), overScaledWidth, overScaledHeight); Point.popApp();}
	
//	private void animateMoveOver(int start, int frames, boolean ignore, boolean fullAnim) {
//		int startReal = start + this.move.getDir() * frames; PApplet app = Point.getApp();
////		this.overMove.resetPoint();
//		if (fullAnim && this.animateState[3] != app.frameCount) {this.unskipAnim = true; this.animateState = new int[] {this.overCurrState, this.move.getDir(), startReal+frames-1, frames};}
////		if ((this.basicCollisionOver(this.overCurrState, 0, 0, app.width, app.height) != this.overCurrDir) || ignore) {
////			this.calculateOverMove(speed);
////			this.animManager.animate(app, this.overImage, super.getX(), super.getY(), Character.OVER_WIDTH, Character.OVER_HEIGHT, this.scale.getX(), startReal, startReal + frames - 1, this.overAnimSpeed, this.changeAnim, this.overLastState != this.overCurrState);
////		} 
////		if (ignore) {
////			this.calculateOverMove(speed);
//			this.animManager.animate(app, this.overImage, super.getX(), super.getY(), Character.OVER_WIDTH, Character.OVER_HEIGHT, this.scale.getX(), startReal, startReal + frames - 1, this.overAnimSpeed, this.changeAnim, this.overLastState != this.overCurrState);
////		}
////		else {this.unskipAnim = false;}
//	}
//	
//	private void overStateCheck1() {
////		if (this.overSprint) {this.overSpeed *= 2; this.overAnimSpeed /= 2;}  // If sprinting, double all speeds
////		if (this.overSprint) {this.overAnimSpeed /= 2;}  // If sprinting, double all speeds
//		if (this.unskipAnim) {  // If we are staying in an animation
//			if ((this.animManager.getIndexCount() + 1) % this.overAnimSpeed == 0 && this.animManager.getIndex() == this.animateState[2]) {this.unskipAnim = false;}  // If the animation has ended, end the animation
////			else {this.overCurrState = this.animateState[0]; this.overCurrDir = this.overLastDir;}  //Else, keep the character state and direction the same
//		}
//	}
	
//	private void overStateCheck2() {if (this.overSprint || (this.overSpeed >= this.OVER_MOVE_SPEED_CAP && this.overAnimSpeed <= this.OVER_ANIM_SPEED_CAP)) {this.overSpeed /= 2; this.overAnimSpeed *= 2;}}  // Reset speeds if not sprinting
	
	// Get
	public int getOverDir() {return this.move[this.currMove].getDir();}
	public boolean getOverState() {return this.move[this.currMove].getIsIdle();}
	public boolean getOverworld() {return this.overWorldCurr;}
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
	public float getW() {return this.getMoveSet().getW();}
	@Override
	public float getH() {return this.getMoveSet().getH();}
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

}
