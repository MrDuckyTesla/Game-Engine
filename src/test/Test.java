package test;

import engine.util.ToolKit;
import engine.util.neuralnet.Matrix;
import processing.core.PApplet;

public class Test extends PApplet {
	
	public PSystem test = new PSystem();

	public static void main(String[] args) {
//		PApplet.main(Test.class);
		Matrix m = new Matrix(2, 3);
		m.propigate();
//		m.set(1, 1, 1);
//		m.set(0, 1, 2);
//		m.set(0, 0, 3);
//		m.set(1, 0, 4);
//		m.set(9, 1, 4);
//		m.set(5, 5, 400);
		System.out.println(m);
		
		Matrix n = new Matrix(3, 2);
		n.propigate();
//		n.set(1, 1, 4);
//		n.set(0, 1, 3);
//		n.set(0, 0, 2);
//		n.set(1, 0, 1);
		System.out.println(n);
		
//		n.addMatrix(2);
//		System.out.println(n);
		
		System.out.println(Matrix.multiply(m, n).getCol(2));
	}
	
	// Only used for the size of the canvas
	@Override
	public void settings() {
		size(800, 800);
		noSmooth();
	}
	
	@Override
	public void setup() {
		surface.setTitle("3 Body System Test");
		textFont(createFont("src/Assets/Fonts/TeslaCrashToFont.ttf", 36, false));
		noCursor(); noStroke(); textSize(20); // frameRate(10);
	}
	
	@Override
	public void draw() {
		background(50);
		this.image(ToolKit.squareImage(this, this.width, this.height, 40, this.width, this.width), 0, 0, this.width, this.height);
		
		test.update(this);
		
		textSize(36); text(Math.round(this.frameRate)+"fps", 10, 30);
		
	}
}