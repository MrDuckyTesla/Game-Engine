package engine;

import java.io.IOException;

import engine.graphics.Game;
import engine.neural.Network;
import engine.neural.networks.Feedforward;
import engine.util.ByteHelper;
import engine.util.data.Data;
import engine.util.data.storages.Local;

public class Main extends Game {
	
	private static Game game = new Main();
	public int x = 0, add = 1;
	
	public static void main(String[] args) {
		Feedforward f = new Feedforward();
//		try {
//			Data<Network> d = new Data<>();
//			Feedforward f = (Feedforward) d.load();
//			f = new Feedforward(new ByteHelper(new Local().load()));
//		} 
//		catch (IOException e) {e.printStackTrace();} 
//		catch (ReflectiveOperationException e) {e.printStackTrace();}
		
//		game.getSettings().setWindowName("Game Engine Test!");
//		game.main();
		
	}

	@Override
	public void loop() {
		game.rect(x, 400, 20, 20); x+=add;
		if (x > 800 || x< 0) {add *= -1;}
		
		
	}

}
