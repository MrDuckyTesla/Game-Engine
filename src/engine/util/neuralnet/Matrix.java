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
		Matrix trans = new Matrix(a.getHgt(), a.getWid());
		for (int i = 0; i < a.getWid(); i++) {
			trans.setCol(a.getRow(i));
		}
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
	
	public void scaleRow(float scale) {
		
	}
	
	public void scaleCol(float scale) {
		
	}
	
	public void swapRow(int rowA, int rowB) {
		
	}
	
	public void swapCol(int colA, int colB) {
		
	}
	
	// Getters and setters
	
	public Vector getRow(int row) {
		return null;
	}
	
	public Vector getCol(int col) {
		return null;
	}
	
	public boolean setRow(float[] row) {
		if (row.length == this.wid) {
			
		}
		return false;
	}
	
	public boolean setRow(Vector row) {
		if (row.getHgt() == this.wid) {
			
		}
		return false;
	}
	
	public boolean setCol(float[] col) {
		return false;
	}
	
	public boolean setCol(Vector col) {
		return false;
	}
	
	public boolean set(float[] matrix) {
		return false;
	}
	
	public int getWid() {return this.wid;}
	public int getHgt() {return this.hgt;}
	public float[] getMatrix() {return this.matrix;}
	
	public float get(int row, int col) {return this.matrix[col + row*this.wid];}
	
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
