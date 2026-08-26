package test;

import engine.app.Applet;

public class Test_Physics extends Applet {
	
	private static final Applet test = new Test_Physics();

	public static void main(String[] args) {
		test.getSettings().setWindowName("Physics Engine Test!");
		test.run();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		test.background();
		test.rect(400, 400, 20, 20);
		
	}
}
