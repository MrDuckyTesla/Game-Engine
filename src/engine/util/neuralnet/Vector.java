package engine.util.neuralnet;

public class Vector extends Matrix {
	
	public Vector(float[] v) {
		super(1, v.length);
		super.set(v);
	}
	
	public Vector(int hgt) {
		super(1, hgt);
	}
	
	public float dot(Vector other) {
		return -1;
	}
	
	public void normalize() {
		
	}

}
