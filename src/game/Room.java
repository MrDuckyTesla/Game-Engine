package game;

import java.util.ArrayList;
import java.util.Collections;
import game.entity.*;
import game.entity.movement.*;
import processing.core.PImage;

// A Room holds obstacles and by extension characters
public class Room {

	private ArrayList<Entity> room = new ArrayList<Entity>();
	private Player p;
	// BACKGROUND VARIABLES
	private PImage background;
	private Point backCoords;
	
	public Room(Player p, PImage background) {this.instantiate(p, background, new Point());}
	public Room(Player p, Entity o, PImage background) {this.instantiate(p, background, new Point()); room.add(p); room.add(o);}
	public Room(Player p, Entity[] o, PImage background) {this.instantiate(p, background, new Point()); this.add(o);}
	public Room(Player p,ArrayList<Entity> o, PImage background) {this.instantiate(p, background, new Point()); this.add(o);}
	
	public void add(Entity o) {room.add(o);}
	public void add(Entity[] o) {for (int i = 0; i < o.length; i ++) {room.add(o[i]);}}
	public void add(ArrayList<Entity> o) {for (int i = 0; i < o.size(); i ++) {room.add(o.get(i));}}
	public void add(float x, float y, float w, float h) {room.add(new NonPlayerCharacter(x, y, w, h));}
	
	private void instantiate(Player p, PImage background, Point backCoords) {
		this.p = p; this.background = background; this.backCoords = backCoords; room.add(this.p);// this.playCoords = p.getXY();
	}
	
	//TODO implement reading from file
	public void add(String file) {
			
	}
	//TODO implement loading assets dynamically
	public void load() {
		
	}
	//TODO implement unloading assets
	public void unLoad() {
		
	}
	
	public void update() {
		Entity.setRoom(this);
		Collections.sort(room);
		if (this.background != null) {this.moveBackground();}
		
		((EightDirectionalMove) p.getMoveSet()).showHitBG();
		
		for (Entity e : room) {
			e.update();
			e.setXY(e.getX()+this.backCoords.getX(), e.getY()+this.backCoords.getY());
		}
	}

	private void moveBackground() {
		Point pot = this.p.getPotential();
		boolean left = p.getX() + pot.getX() > ToolKit.getAppWidth()/2 - p.getW()/2;
		if (left && p.getX() + pot.getX() < this.background.width - ToolKit.getAppWidth()/2 - p.getW()/2) {
			this.backCoords.addX(-pot.getX());  // Move background X coord
		} else {this.backCoords.setX(left? -this.background.width + ToolKit.getAppWidth(): 0);}
		boolean up = p.getY() + pot.getY() > ToolKit.getAppHeight()/2 - p.getH()/2;
		if (up && p.getY() + pot.getY() < this.background.height - ToolKit.getAppHeight()/2 - p.getH()/2) {
			this.backCoords.addY(-pot.getY());  // Move background Y coord
		} else {this.backCoords.setY(up? -this.background.height + ToolKit.getAppHeight(): 0);}
	    ToolKit.getApp().image(this.background, this.backCoords.getX(), this.backCoords.getY());
	}
	
	public ArrayList<Entity> getRoom() {return this.room;}
	public Point getBackCoords() {return this.backCoords == null? new Point() : this.backCoords;}
	public int getImageWidth() {return this.background == null? ToolKit.getAppWidth() : this.background.width;}
	public int getImageHeight() {return this.background == null? ToolKit.getAppHeight() :this.background.height;}
	
}
