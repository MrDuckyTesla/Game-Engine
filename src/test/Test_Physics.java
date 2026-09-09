package test;

import engine.app.App;
import engine.physics.*;
import engine.physics.bodies.Rigidbody;
import engine.physics.shapes.Circle;
import engine.physics.shapes.Rectangle;
import engine.physics.util.State;
import engine.physics.util.Vector2D;

public class Test_Physics extends App {
	
	private static final App test = new Test_Physics();
//	private Container con;

	public static void main(String[] args) {
		test.getSettings().setWindowName("Physics Engine Test!");
		test.run();
	}
	
	@Override
	public void setup() {
//		Body rect = new Rectangle(200, 200);
//		con = new Container(state, rect);
//		con.addBody(new RigidBody(new RigidCircle(10)));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		test.background();
		test.rect(400, 400, 20, 20);
//		con.show(test);
		
	}
}
