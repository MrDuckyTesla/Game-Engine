package game;

import java.util.ArrayList;
import java.util.Collections;
import game.entity.*;
import processing.core.PImage;

// A Room holds obstacles and by extension characters
public class Room {

	private ArrayList<Entity> room = new ArrayList<Entity>();
	private Player p;
	// BACKGROUND VARIABLES
	private PImage background;
	private Point backCoords;
	private boolean backX, backY, hasInstantiated = false;
	
	public Room(Player p, PImage background) {this.instantiate(p, background);}
	public Room(Player p, Entity o, PImage background) {this.p = p; this.background = background; room.add(p); room.add(o);}
	public Room(Player p, Entity[] o, PImage background) {this.instantiate(p, background); this.add(o);}
	public Room(Player p,ArrayList<Entity> o, PImage background) {this.instantiate(p, background); this.add(o);}
	
	public void add(Entity o) {room.add(o);}
	public void add(Entity[] o) {for (int i = 0; i < o.length; i ++) {room.add(o[i]);}}
	public void add(ArrayList<Entity> o) {for (int i = 0; i < o.size(); i ++) {room.add(o.get(i));}}
	
	private void instantiate(Player p, PImage background) {
		this.p = p; this.background = background; room.add(this.p);
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
		Entity.setRoom(room);
		Collections.sort(room);
		
		for (Entity e : room) {
			e.update();
			e.setX(10);
		}
	}
	
//	private void update() {
//		this.p.update();
//		for (int i = 0; i < room.size(); i++) {room.get(i).update();}
//		for (Obstacle o : room) {if (!o.getClass().equals(Character.class)) {if (!p.isCollide(o)) {o.isCollide(p);}}}
//	}
	
	public void moveBackground() {
//		if (!this.hasInstantiated) {this.instantiateVars(); this.hasInstantiated = true;}
//	    if (this.backX) {  // If background is moving X
//	    	this.backCoords.changeX(-this.p.getMoveX());
//	    	if (this.backCoords.getX() <= (float) this.p.getApp().width - this.background.width || this.backCoords.getX() >= 0) {
//	    		this.backCoords.setX(this.backCoords.getX() <= (float) this.p.getApp().width - this.background.width? (float) this.p.getApp().width - this.background.width : 0);
//	    		this.backX = false;
//	    	}
//	    } else {  // Else moving character X
//	    	this.p.changeOverX();
//	    	float temp = (float) this.p.getApp().width/2 - this.p.getWidth()/2;
//	    	if ((this.p.getX() >= temp && this.backCoords.getX() >= 0) || (this.p.getX() <= temp && this.backCoords.getX() <= (float) this.p.getApp().width - this.background.width)) {
//	    		this.p.setX(temp);
//	    		this.backX = true;
//	    	}
//	    } if (this.backY) {  // If background is moving Y
//	    	this.backCoords.changeY(-this.p.getMoveY());
//	    	if (this.backCoords.getY() <= this.p.getApp().height - this.background.height || this.backCoords.getY() >= 0) {
//	    		this.backCoords.setY(this.backCoords.getY() <= this.p.getApp().height - this.background.height? this.p.getApp().height - this.background.height : 0);
//	    		this.backY = false;
//	    	}
//	    } else {  // Else moving character Y
//	    	this.p.changeOverY();
//	    	float temp = this.p.getApp().height/2 - this.p.getHeight()/2;
//	    	if ((this.p.getY() >= temp && this.backCoords.getY() >= 0) || (this.p.getY() <= temp && this.backCoords.getY() <= this.p.getApp().height - this.background.height)) {
//	    		this.p.setY(temp);
//	    		this.backY = true;
//	    	}
//	    } this.p.getApp().image(this.background, this.backCoords.getX(), this.backCoords.getY());
//	    System.out.println(this.backX + " " + this.backY);
	}
	
}
