package engine;

import engine.app.App;

public class Main extends App {
	
	private static App game = new Main();
	public int x = 0, add = 1;
	
	public static void main(String[] args) {
		game.getSettings().setWindowName("Game Engine Test!");
		game.run();
		
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
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
