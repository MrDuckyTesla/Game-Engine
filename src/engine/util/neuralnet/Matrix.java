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
			trans.setCol(i, a.getRow(i).getMatrix());
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
	
	public void scaleRow(int row, float scale) {
		for (int i = 0; i < this.wid; i++) {
			this.matrix[row*this.wid + i] *= scale;
		}
	}
	
	public void scaleRow(int row, float[] scale) {
		for (int i = 0; i < this.wid; i++) {
			this.matrix[row*this.wid + i] *= scale[i];
		}
	}
	
	public void scaleCol(int col, float scale) {
		for (int i = 0; i < this.wid; i++) {
			this.matrix[i*this.wid + col] *= scale;
		}
	}
	
	public void scaleCol(int col, float[] scale) {
		for (int i = 0; i < this.wid; i++) {
			this.matrix[i*this.wid + col] *= scale[i];
		}
	}
	
	public void swapRow(int rowA, int rowB) {
		float[] row1 = this.getRow(rowA).getMatrix();
		this.setRow(rowA, this.getRow(rowB).getMatrix());
		this.setRow(rowB, row1);
	}
	
	public void swapCol(int colA, int colB) {
		float[] row1 = this.getCol(colA).getMatrix();
		this.setCol(colA, this.getCol(colB).getMatrix());
		this.setCol(colB, row1);
	}
	
	// Getters and setters
	
	public float get(int row, int col) {return this.matrix[col + row*this.wid];}
	
	public Vector getRow(int row) {
		float[] rowL = new float[this.wid];
		for (int i = 0; i < this.wid; i++) {
			rowL[i] = this.matrix[row*this.wid + i];
		} return new Vector(rowL);
	}
	
	public Vector getCol(int col) {
		float[] colL = new float[this.wid];
		for (int i = 0; i < this.wid; i++) {
			colL[i] = this.matrix[i*this.wid + col];
		} return new Vector(colL);
	}
	
	public float[] getMatrix() {return this.matrix;}
	public int getWid() {return this.wid;}
	public int getHgt() {return this.hgt;}
	
	public void set(int row, int col, float payload) {this.matrix[col + row*this.wid] = payload;}
	
	public boolean set(float[] matrix) {
		if (matrix.length == this.matrix.length) {
			this.matrix = matrix; return true;
		} return false;
	}
	
	public boolean setRow(int row, float[] set) {
		if (set.length == this.wid) {
			for (int i = 0; i < this.wid; i++) {
				this.matrix[row*this.wid + i] = set[i];
			} return true;
		} return false;
	}
	
	public boolean setCol(int col, float[] set) {
		if (set.length == this.hgt) {
			for (int i = 0; i < this.hgt; i++) {
				this.matrix[i*this.wid + col] = set[i];
			} return true;
		} return false;
	}
	
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
