package test;

import engine.app.Applet;
import engine.physics.*;
import engine.physics.bodies.Container;
import engine.physics.bodies.RigidBody;
import engine.physics.shapes.Circle;
import engine.physics.shapes.Rectangle;
import engine.physics.util.State;
import engine.physics.util.Vector2D;

public class Test_Physics extends Applet {
	
	private static final Applet test = new Test_Physics();
	private Container con;

	public static void main(String[] args) {
		test.getSettings().setWindowName("Physics Engine Test!");
		test.run();
	}
	
	@Override
	public void setup() {
		Shape rect = new Rectangle(200, 200);
		State state = new State(new Vector2D(200, 200));
		con = new Container(state, rect);
		con.addBody(new RigidBody(new Circle(10)));
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		test.background();
		test.rect(400, 400, 20, 20);
		con.show(test);
		
	}
}
