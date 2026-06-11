package engine.util.neuralnet;

public class Matrix {
	
	public static Matrix multiply(Matrix a, Matrix b) throws IllegalArgumentException {
		float[] prod = new float[a.getHgt() * b.getWid()];
		if (b.getHgt() == a.getWid()) {
			for (int i = 0; i < a.getWid(); i++) {
				for (int j = 0; j < a.getHgt(); j++) {
					for (int k = 0; k < b.wid; k++) {
						prod[j*b.getWid() + k] += a.get(j, i) * b.get(i, k);
					}
				}
			} return new Matrix(prod, b.getWid(), a.getHgt());
		} throw new IllegalArgumentException();
	}
	
	public static Matrix transpose(Matrix a) {
		return null;
	}
	
	private float[] matrix;
	private int wid, hgt;
	
	// Constructors

	public Matrix(int wid, int hgt) {
		this.matrix = new float[wid*hgt];
		this.wid = wid; this.hgt = hgt;
	}
	
	private Matrix(float[] matrix, int wid, int hgt) {
		this.matrix = matrix;
		this.wid = wid; this.hgt = hgt;
	}
	
	public void propigate() {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i] = (float) (Math.random() * 2 - 1);
		}
	}
	
	// Basic operations
	
	public void scale(float scale) {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i] *= scale;
		}
	}
	
	public void swap(int rowA, int rowB) {
		
	}
	
	// Getters and setters
	
	public Vector getRow(int row) {
		return null;
	}
	
	public Vector getCol(int col) {
		return null;
	}
	
	public void setRow() {
		
	}
	
	public void setCol() {
		
	}
	
	public int getHgt() {return this.hgt;}
	public int getWid() {return this.wid;}
	public float get(int row, int col) {return this.matrix[col + row*this.wid];}
	public float[] getMatrix() {return this.matrix;}
	
	public void set(int row, int col, float payload) {this.matrix[col + row*this.wid] = payload;}
	public void add(int row, int col, float payload) {this.matrix[col + row*this.wid] += payload;}
	
	// To string and helper function
	
	public String toString() {
		String self = "";
		for (int i = 0; i < this.matrix.length; i++) {
			self += this.format(matrix[i]);
			if ((i+1) % this.wid == 0) {
				self+="\n\n";
			} 
		} return self;
	}
	
	private String format(float num) {
		String numS = String.format("%.5f", num);
		int len = Math.max(13-numS.length(), 0);
		numS = String.format("%."+len+"f", num);
		return len == 0? numS + "\t" : numS + "\t";
	}

}
