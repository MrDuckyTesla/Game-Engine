package test;

import engine.app.Applet;
import engine.physics.*;
import engine.physics.bodies.Container;
import engine.physics.colliders.Rectangle;

public class Test_Physics extends Applet {
	
	private static final Applet test = new Test_Physics();

	public static void main(String[] args) {
		test.getSettings().setWindowName("Physics Engine Test!");
		test.run();
	}
	
	@Override
	public void setup() {
		Collider rect = new Rectangle(200, 200);
		Body con = new Container(rect);
		
	}

	@Override
	public void update() {
		
		
	}

	@Override
	public void render() {
		test.background();
		test.rect(400, 400, 20, 20);
		
	}
}
