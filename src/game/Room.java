package game;

import java.util.ArrayList;
import java.util.Collections;
import game.entity.*;
import game.entity.movement.*;
import game.entity.trigger.Trigger;
import game.entity.trigger.Triggers;
import game.util.Point;
import game.util.ToolKit;
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
	private Room() {}
	
	public void add(Entity o) {room.add(o);}
	public void add(Entity[] o) {for (int i = 0; i < o.length; i ++) {room.add(o[i]);}}
	public void add(ArrayList<Entity> o) {for (int i = 0; i < o.size(); i ++) {room.add(o.get(i));}}
	public void add(float x, float y, float w, float h) {room.add(new NonPlayerCharacter(x, y, w, h));}
	
	private void instantiate(Player p, PImage background, Point backCoords) {
		this.p = p; this.background = background; this.backCoords = backCoords; if (this.p != null) {room.add(this.p);}// this.playCoords = p.getXY();
	}
	
	public boolean setPlayer(Player p) {if (this.p == null) {this.p = p; room.add(this.p); return true;} return false;}
	
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
		Collections.sort(room);  // Sort room to keep ordering correct
		
		if (this.background != null) {
			ToolKit.getApp().image(this.background, this.backCoords.getX(), this.backCoords.getY());
		}
		
		for (int i = 0; i < room.size(); i++) {
			Entity e = this.room.get(i);
			
			if (!e.isDelete()) {
				e.update();
				e.setXY(e.getX()+this.backCoords.getX(), e.getY()+this.backCoords.getY());
				if (e.getType() == Entities.TRIGGER) {this.room.remove(i); i--;}
			} else {this.room.remove(i); i--;}
		}
		
//		System.out.println(this.room.size());
		
		this.moveBackground();
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
	}
	
	public ArrayList<Entity> getRoom() {return this.room;}
	public Player getPlayer() {return this.p;}
	public Point getBackCoords() {return this.backCoords == null? new Point() : this.backCoords;}
	public int getImageWidth() {return this.background == null? ToolKit.getAppWidth() : this.background.width;}
	public int getImageHeight() {return this.background == null? ToolKit.getAppHeight() :this.background.height;}
	
	public static Interaction createInteraction(MoveSet m, Entity e, Triggers t) {
		float halfW = m.getSW()/2, halfH = m.getSH()/2;
		int dirInt = m.getDir(); Point xy = new Point();
		if (dirInt % 4 != 2) {xy.setX(dirInt % 7 < 2? halfW : -halfW);}
		if (dirInt % 4 != 0) {xy.setY(dirInt < 4? halfH : -halfH);}
		if (dirInt % 2 == 1) {xy.multpilyXY(0.7071068f);}  // sin 45
		return new Room().new Interaction(m.getX() + xy.getX() + halfW/2, m.getY() + xy.getY() + halfH/2, halfW, halfH, e, t);
	}
	
	private class Interaction extends Trigger {
		
		private Triggers t;

		public Interaction(float x, float y, float w, float h, Entity i, Triggers t) {
			super(x, y, w, h, i); this.t = t;
		}

		@Override
		public void update() {
			for (Entity e : room) {
				if (e.getType() != Entities.TRIGGER && !this.getCaster().equals(e)) {
					if (ToolKit.rectRectCollide (
							this.getX(), this.getY(), this.getW(), this.getH(), 
							e.getX(), e.getY(), e.getW(), e.getH()
					)) {e.interact(this.t);} // e.setDeathDir(this.getCaster().getOverDir());}
				}
			}
		}

		@Override
		public Triggers getTrigger() {return this.t;}

	}
	
}
