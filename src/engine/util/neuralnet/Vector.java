package engine.util.neuralnet;

import engine.util.physics.Vector2D;

public class Vector extends Matrix {
	
	public static float dot(Vector a, Vector b) {
		if (a.getHgt() == b.getHgt()) {
			float prod = 0;
			for (int i = 0; i < a.getHgt(); i++) {
				prod += a.get(i)*b.get(i);
			} return prod;
		} return Float.NaN;
	}
	
	public Vector(float[] v) {
		super(1, v.length);
		super.setMatrix(v);
	}
	
	public Vector(int hgt) {
		super(1, hgt);
	}
	
	public Vector2D getNormal() {
//		float hypot = x*x + y*y;
//		if (hypot == 0) {return new Vector2D();}
//		float overH = 1 / (float)Math.sqrt(hypot);
//		return new Vector2D(x*overH, y*overH);
		return null;
	}
	
	public float get(int num) {
		return this.getMatrix()[num];
	}

}
