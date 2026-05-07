package engine.util.math;

public class Matrix {
	
	public static Matrix multiply(Matrix a, Matrix b) {
		return null;
	}
	
	public static Matrix transpose(Matrix a) {
		return null;
	}
	
	private float[] matrix;
	private int wid, hgt;

	public Matrix(int wid, int hgt) {
		this.matrix = new float[wid*hgt];
		this.wid = wid; this.hgt = hgt;
	}
	
	public void propigate() {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i] = (float) Math.random();
		}
	}

	public void set(int row, int col, float payload) {
		this.matrix[col + row*this.wid] = payload;
	}
	
	public void scale(float scale) {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i] *= scale;
		}
	}
	
	public void multiply(Matrix b) {
		
	}
	
	public void swap(int rowA, int rowB) {
		
	}
	
	public void transpose() {
		
	}
	
	public Vector getRow(int row) {
		return null;
	}
	
	public Vector getCol(int col) {
		return null;
	}
	
	public int getHgt() {return this.hgt;}
	public int getWid() {return this.wid;}
	public float[] getMatrix() {return this.matrix;}
	
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
		return len == 0? numS + " \t" : numS + "\t";
	}

}
