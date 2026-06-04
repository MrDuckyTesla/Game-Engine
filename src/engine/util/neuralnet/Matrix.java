package engine.util.neuralnet;

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
	
	public void scale(float scale) {
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i] *= scale;
		}
	}
	
	public void multiply(Matrix b) {
		float[] mult = new float[this.hgt * b.getWid()];
		if (b.getHgt() == this.wid) {
			for (int i = 0; i < this.wid; i++) {
				for (int j = 0; j < this.hgt; j++) {
					for (int k = 0; k < b.wid; k++) {
						mult[i*this.wid + j] += this.matrix[i*this.wid + j] * b.get(j, i);
					}
				}
			}
		}
	}
	
	//
	//		A B C D			M N O			AM+BP+CS+DV AN+BQ+CT+DW AO+BR+CU+DX
	//		E F G H	   *  	P Q R   =  		EM+FP+GS+HV EN+FQ+GT+HW EO+FR+GU+HX
	//		I J K L			S T U			IM+JP+KS+LV IN+JQ+KT+LW IO+JR+KU+LX
	//						V W X
	//
	//		 3 x 4			4 x 3
	//
	
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
	
	// Getters and setters
	
	public float get(int row, int col) {
		return this.matrix[col + row*this.wid];
	}
	
	public void set(int row, int col, float payload) {
		this.matrix[col + row*this.wid] = payload;
	}
	
	public void add(int row, int col, float payload) {
		this.matrix[col + row*this.wid] += payload;
	}
	
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
