package game.util;

import java.util.ArrayList;
import java.util.HashMap;

import game.Room;
import game.entity.Entity;

public class Chunk {
	
	public static int hash(int x, int y) {return 31*x+y;} // Apparently 31 is a magic number in hashing
	
	public static <E extends Entity> ArrayList<E> getNeighbors(E obj, HashMap<Integer, ArrayList<E>> h, int wid, int hgt, int size) {
		ArrayList<E> neighbors = new ArrayList<>(); int key;
		int x = (int) (obj.getRX()/Room.CHUNK_SIZE), y = (int) (obj.getRY()/Room.CHUNK_SIZE);
		for (int i = -size; i < 1+size; i++) {
			for (int j = -size; j < 1+size; j++) {
				int nX = x+i, nY = y+j;
				if (nX <= wid && nY <= hgt && nX >= 0 && nY >= 0) {
					key = hash(nX, nY);
					if (h.containsKey(key)) {neighbors.addAll(h.get(key));}
				}
			}
		} return neighbors;
	}
	
	private final int x, y;

	public Chunk(int x, int y) {
		this.x = x; this.y = y;
	}
	
	public Chunk() {
		this.x = 0; this.y = 0;
	}
	
//	public ArrayList<Chunk> getNeighbors(int wid, int hgt) {
//		ArrayList<Chunk> neighbors = new ArrayList<>();
//		for (int i = -1; i < 2; i++) {
//			for (int j = -1; j < 2; j++) {
//				int nX = x+i, nY = y+j;
//				if (nX <= wid && nY <= hgt && nX >= 0 && nY >= 0) {
//					neighbors.add(new Chunk(nX, nY));
//				}
//			}
//		} return neighbors;
//	}
	
	public <E> ArrayList<E> getNeighbors(HashMap<Integer, ArrayList<E>> h, int wid, int hgt) {
		ArrayList<E> neighbors = new ArrayList<>(); int key;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int nX = x+i, nY = y+j;
				if (nX <= wid && nY <= hgt && nX >= 0 && nY >= 0) {
					key = hash(nX, nY);
					if (h.containsKey(key)) {neighbors.addAll(h.get(key));}
				}
			}
		} return neighbors;
	}
	
	public int getX() {return x;}
	public int getY() {return y;}

	@Override
	public boolean equals(Object other) {return this.x == ((Chunk) other).getX() && this.y == ((Chunk) other).getY();}
	
	@Override
	public String toString() {return "("+x+", "+y+")";}
	
	@Override
	public int hashCode() {
//		return Objects.hash(x, y);
		return 31*x+y; // Apparently 31 is a magic number in hashing
	}
	
}
