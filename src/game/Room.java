package game;

import java.util.*;
import game.entity.*;
import game.util.*;
import processing.core.PImage;

// A Room holds obstacles and by extension characters
public class Room {
	
	public static final int CHUNK_SIZE = 200;

	private HashMap<Integer, ArrayList<Entity>> hash = new HashMap<>();
	private ArrayList<Entity> add, sub, mod;  // Lists to keep track of what added, removed and modified
	
	private Player p;
	// BACKGROUND VARIABLES
	private PImage background;
	private Point backCoords;
	
	public Room(PImage background) {this.instantiate(background, new Point());}
	public Room(Entity o, PImage background) {this.instantiate(background, new Point()); add.add(o);}
	public Room(Entity[] o, PImage background) {this.instantiate(background, new Point()); this.add(o);}
	public Room(ArrayList<Entity> o, PImage background) {this.instantiate(background, new Point()); this.add(o);}
	
	public void add(Entity e) {add.add(e);}
	public void add(Entity[] l) {for (Entity e : l) {add.add(e);}}
	public void add(Iterable<? extends Entity> l) {for (Entity e : l) {add.add(e);}}
	public void add(float x, float y, float w, float h) {add.add(new Wall(x, y, w, h));}
	
	public boolean setPlayer(Player p) {if (this.p == null) {this.p = p; add.add(this.p); return true;} return false;}
	
	private void instantiate(PImage background, Point backCoords) {
		this.background = background; this.backCoords = backCoords; 
		this.add = new ArrayList<>(); this.sub = new ArrayList<>(); this.mod = new ArrayList<>();
	}
	
	// HASH ADD
	private void addHash(Entity e, int key) {hash.computeIfAbsent(key,  k -> new ArrayList<>()).add(e); e.setHash(key);} 
	private void addHash(Entity e) {this.addHash(e, Chunk.hash((int)(e.getRX()/CHUNK_SIZE), (int)(e.getRY()/CHUNK_SIZE)));}
	private void addHash(Iterable<? extends Entity> l) {for (Entity e : l) {this.addHash(e);}}
	
	// HASH REMOVE
	private void removeHash(Entity e, int key) {
		ArrayList<Entity> chunk = hash.get(key); if (chunk != null) {chunk.remove(e); if (chunk.isEmpty()) {hash.remove(key);}}
	} private void removeHash(Entity e) {this.removeHash(e, e.getHash());}
	private void removeHash(Iterable<? extends Entity> l) {for (Entity e : l) {this.removeHash(e);}}
	
//	public boolean setPlayer(Player p) {if (this.p == null) {this.p = p; room.add(this.p); return true;} return false;}
	
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
//		Collections.sort(room);  // Sort room to keep ordering correct
		
		
		if (this.background != null) {
//			ToolKit.getApp().image(this.background, this.backCoords.getX(), this.backCoords.getY());
		} 
		
		this.moveBackground();
		ArrayList<Entity> show = Chunk.getNeighbors(this.p, this.hash, this.background.width, this.background.height, 3);
		Collections.sort(show);
		for (Entity e : show) {e.show();}
		
		for (ArrayList<Entity> l : hash.values()) {
			for (Entity e : l) {
				if (e.isDelete()) {
					if (e.getType() == Entities.TRIGGER) {e.update();}
					this.sub.add(e);
				} 
				
				else {
					e.setXY(e.getRX()+this.backCoords.getX(), e.getRY()+this.backCoords.getY());
					e.update(); this.add.addAll(e.getMoveSet().getTriggers()); 
				}
				
				if (e.getHash() != Chunk.hash((int)(e.getRX()/CHUNK_SIZE), (int)(e.getRY()/CHUNK_SIZE))) {
					this.mod.add(e);
				}
			} 
		}
		
		for (Entity e : this.mod) {
			this.removeHash(e);
			e.setHash(Chunk.hash((int)(e.getRX()/CHUNK_SIZE), (int)(e.getRY()/CHUNK_SIZE)));
			this.addHash(e);
		}
		
		
		
		this.removeHash(sub); this.addHash(add); 
		sub.clear(); add.clear(); mod.clear();

		
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
	
	public ArrayList<Entity> getRoom(Entity e) {return Chunk.getNeighbors(e, this.hash, this.background.width, this.background.height, 1);}
	public Player getPlayer() {return this.p;}
	public Point getBackCoords() {return this.backCoords == null? new Point() : this.backCoords;}
	public int getImageWidth() {return this.background == null? ToolKit.getAppWidth() : this.background.width;}
	public int getImageHeight() {return this.background == null? ToolKit.getAppHeight() :this.background.height;}
	public int getSize() {return this.hash.size();}
	
}
