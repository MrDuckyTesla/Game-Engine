package test;

import engine.util.ToolKit;
import engine.util.math.Matrix;
import processing.core.PApplet;

public class Test extends PApplet {
	
	public PSystem test = new PSystem();

	public static void main(String[] args) {
//		PApplet.main(Test.class);
		Matrix m = new Matrix(3, 3);
//		m.propigate();
		m.set(1, 1, 10);
		m.set(0, 1, 10);
		m.set(0, 0, 10);
		m.set(1, 0, 10);
//		m.set(9, 1, 4);
//		m.set(5, 5, 400);
		System.out.println(m);
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