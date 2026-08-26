package engine;

import engine.app.Applet;

public class Main extends Applet {
	
	private static Applet game = new Main();
	public int x = 0, add = 1;
	
	public static void main(String[] args) {
		game.getSettings().setWindowName("Game Engine Test!");
		game.run();
		
	}

	@Override
	public void update() {
		x+=add;
		if (x > 780 || x< 0) {add *= -1;}
	}
	
	@Override
	public void render() {
		game.background();
		game.rect(x, 400, 20, 20);
	}

}
