package game;

import java.util.*;
import game.entity.*;
import game.util.*;
import processing.core.PImage;

// A Room holds obstacles and by extension characters
public class Room {
	
	public static final int CHUNK_SIZE = 200;

	private HashMap<Integer, ArrayList<Entity>> hash = new HashMap<>();
	private ArrayList<Entity> room = new ArrayList<>();
	private ArrayList<Entity> add = new ArrayList<>(), remove = new ArrayList<>();
	
	private Player p;
	// BACKGROUND VARIABLES
	private PImage background;
	private Point backCoords;
	
	public Room(Player p, PImage background) {this.instantiate(p, background, new Point());}
	public Room(Player p, Entity o, PImage background) {this.instantiate(p, background, new Point()); room.add(o);}
	public Room(Player p, Entity[] o, PImage background) {this.instantiate(p, background, new Point()); this.add(o);}
	public Room(Player p,ArrayList<Entity> o, PImage background) {this.instantiate(p, background, new Point()); this.add(o);}
	
	public void add(Entity o) {room.add(o);}
	public void add(Entity[] o) {for (int i = 0; i < o.length; i ++) {room.add(o[i]);}}
	public void add(ArrayList<Entity> o) {for (int i = 0; i < o.size(); i ++) {room.add(o.get(i));}}
	public void add(float x, float y, float w, float h) {room.add(new Wall(x, y, w, h));}
	
	private void instantiate(Player p, PImage background, Point backCoords) {
		this.p = p; this.background = background; this.backCoords = backCoords; 
		if (this.p != null) {room.add(this.p);}
	}
	
	private void addHash(ArrayList<Entity> list) {
		int key;
		for (Entity e : list) {
			key = Chunk.hash((int)(e.getRX()/CHUNK_SIZE), (int)(e.getRY()/CHUNK_SIZE));
			hash.computeIfAbsent(key,  k -> new ArrayList<>()).add(e);
			e.setHash(key);
		}
	}
	
	private void addHash(Entity e, int key) {
		hash.computeIfAbsent(key,  k -> new ArrayList<>()).add(e);
		e.setHash(key);
	}
	
	private void removeHash(ArrayList<Entity> list) {
		for (Entity e : list) {
			ArrayList<Entity> chunk = hash.get(e.getHash());
			if (chunk != null) {
				chunk.remove(e);
				if (chunk.isEmpty()) {hash.remove(e.getHash());}
			}
		}
	}
	
	private void removeHash(Entity e, int key) {
		ArrayList<Entity> chunk = hash.get(key);
		if (chunk != null) {
			chunk.remove(e);
			if (chunk.isEmpty()) {hash.remove(key);}
		}
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
//		System.out.println(hash.size());
		Collections.sort(room);  // Sort room to keep ordering correct
		
		if (this.hash.size() == 0) {this.addHash(this.room);}
		
		if (this.background != null) {
			ToolKit.getApp().image(this.background, this.backCoords.getX(), this.backCoords.getY());
		} 
		
		int newKey;
		for (Entity e : room) {
			if (e.isDelete()) {
				if (e.getType() == Entities.TRIGGER) {e.update();}
				this.remove.add(e);
			} else {
				e.setXY(e.getRX()+this.backCoords.getX(), e.getRY()+this.backCoords.getY());
				e.update(); this.add.addAll(e.getMoveSet().getTriggers()); 
			}
			newKey = Chunk.hash((int)(e.getRX()/CHUNK_SIZE), (int)(e.getRY()/CHUNK_SIZE));
			if (e.getHash() != newKey) {
				this.removeHash(e, e.getHash());
				this.addHash(e, newKey); 
				e.setHash(newKey);
			}
		} this.moveBackground(); this.room.removeAll(remove); this.room.addAll(add); 
		this.addHash(add); 
		this.removeHash(remove);
		remove.clear(); add.clear();
		
	}

	private void moveBackground() {
		Point pot = this.p.getPotential();
		boolean left = p.getRX() + pot.getX() > ToolKit.getAppWidth()/2 - p.getW()/2;
		if (left && p.getRX() + pot.getX() < this.background.width - ToolKit.getAppWidth()/2 - p.getW()/2) {
			this.backCoords.addX(-pot.getX());  // Move background X coord
		} else {this.backCoords.setX(left? -this.background.width + ToolKit.getAppWidth(): 0);}
		boolean up = p.getRY() + pot.getY() > ToolKit.getAppHeight()/2 - p.getH()/2;
		if (up && p.getRY() + pot.getY() < this.background.height - ToolKit.getAppHeight()/2 - p.getH()/2) {
			this.backCoords.addY(-pot.getY());  // Move background Y coord
		} else {this.backCoords.setY(up? -this.background.height + ToolKit.getAppHeight(): 0);}
	}
	
	public ArrayList<Entity> getRoom(float x, float y) {return new Chunk((int)(x/CHUNK_SIZE), (int)(y/CHUNK_SIZE)).getNeighbors(this.hash, background.width, background.height);}
	public Player getPlayer() {return this.p;}
	public Point getBackCoords() {return this.backCoords == null? new Point() : this.backCoords;}
	public int getImageWidth() {return this.background == null? ToolKit.getAppWidth() : this.background.width;}
	public int getImageHeight() {return this.background == null? ToolKit.getAppHeight() :this.background.height;}
	public int getSize() {return this.room.size();}
	
}
