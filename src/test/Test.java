package test;

import engine.neuralnet.*;
import engine.neuralnet.activations.GELU;
import engine.neuralnet.costs.MeanSquaredError;
import engine.neuralnet.networks.Feedforward;
import engine.util.Timer;
import engine.util.ToolKit;
import processing.core.PApplet;

public class Test extends PApplet {
	
	public PSystem test = new PSystem();

	public static void main(String[] args) {
//		PApplet.main(Test.class);
//		Timer.start(false);
		Matrix m = new Matrix(2, 3);
		m.propagate();
//		m.set(1, 1, 1);
//		m.set(0, 1, 2);
//		m.set(0, 0, 3);
//		m.set(1, 0, 4);
//		m.set(9, 1, 4);
//		m.set(5, 5, 400);
		System.out.println(m);
		
		Matrix n = new Matrix(3, 2);
		n.propagate();
//		n.set(1, 1, 4);
//		n.set(0, 1, 3);
//		n.set(0, 0, 2);
//		n.set(1, 0, 1);
		System.out.println(n);
		
//		m.swapRow(0, 2);
//		System.out.println(m);
		
		System.out.println(m.multiply(n));
		
		Vector[] inputs = {
		    new Vector(new float[]{0, 0}),
		    new Vector(new float[]{0, 1}),
		    new Vector(new float[]{1, 0}),
		    new Vector(new float[]{1, 1})
		};

		Vector[] expected = {
		    new Vector(new float[]{0}),
		    new Vector(new float[]{1}),
		    new Vector(new float[]{1}),
		    new Vector(new float[]{0})
		};
		
		Network test = new Feedforward(new int[] {2, 3, 1}, new GELU(), new MeanSquaredError());
		test.train(inputs, expected, 2);
		
		System.out.println("F F -> " + test.predict(new Vector(new float[] {0, 0})));
		System.out.println("F T -> " + test.predict(new Vector(new float[] {0, 1})));
		System.out.println("T F -> " + test.predict(new Vector(new float[] {1, 0})));
		System.out.println("T T -> " + test.predict(new Vector(new float[] {1, 1})));
		
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