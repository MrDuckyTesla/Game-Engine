package engine;

import engine.graphics.Graphics;

public class Main extends Graphics {
	
	private static Graphics game = new Main();
	public int x = 0, add = 1;
	
	public static void main(String[] args) {
//		Feedforward f = new Feedforward();
//		try {
//			Data<Network> d = new Data<>();
//			Feedforward f = (Feedforward) d.load();
//			f = new Feedforward(new ByteHelper(new Local().load()));
//		} 
//		catch (IOException e) {e.printStackTrace();} 
//		catch (ReflectiveOperationException e) {e.printStackTrace();}
		
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
