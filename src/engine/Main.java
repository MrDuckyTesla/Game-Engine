package engine;

import engine.graphics.Game;

public class Main extends Game {
	
	private static Game game = new Main();
	public int x = 0, add = 1;
	
	public static void main(String[] args) {
		game.getSettings().setWindowName("Game Engine Test!");
		game.main();
		
	}

	@Override
	public void loop() {
		game.rect(x, 400, 20, 20); x+=add;
		if (x > 800 || x< 0) {add *= -1;}
	}

}
