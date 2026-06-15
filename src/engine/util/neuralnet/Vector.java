package engine.util.neuralnet;

public class Vector extends Matrix {
	
	public Vector(float[] v) {
		super(1, v.length);
		super.setMatrix(v);
	}
	
	public Vector(int hgt) {
		super(1, hgt);
	}
	
	public float dot(Vector b) {
		if (this.getHgt() == b.getHgt()) {
			float prod = 0;
			for (int i = 0; i < this.getHgt(); i++) {
				prod += this.get(i)*b.get(i);
			} return prod;
		} return Float.NaN;
	}
	
	public Vector getNormal() {
		float hypot = 0;
		for (int i = 0; i < this.getHgt(); i++) {
			hypot += this.get(i)*this.get(i);
		} if (hypot == 0) {return null;}
		float overH = 1 / (float)Math.sqrt(hypot);
		float[] norm = this.getMatrix();
		for (int i = 0; i < this.getHgt(); i++) {
			norm[i] *= overH;
		} return new Vector(norm);
	}
	
	public float get(int num) {return this.getMatrix()[num];}

}
