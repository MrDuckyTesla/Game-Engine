package engine.util.neuralnet;

public class Vector extends Matrix {
	
	public Vector(float[] ohYeah) {
		super(1, ohYeah.length);
		
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
