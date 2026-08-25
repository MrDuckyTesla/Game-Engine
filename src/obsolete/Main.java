package obsolete;

import engine.Level;
import engine.Room;
import obsolete.entity.*;
import obsolete.entity.abilities.*;
import obsolete.entity.entities.Enemy;
import obsolete.entity.entities.MultiState;
import obsolete.entity.enums.Entities;
import obsolete.entity.movement.*;
import processing.core.*;

public final class Main extends PApplet {
	
	Rect[] obstacles;
	private final int[][][] PlayerSpriteLayers = {{{180, 157, 130, 31}, {187, 171}, {190, 163, 140}}, {{105, 85, 34}, {104}}};
	private int[] PlayerColorTints = {111, 111, 255, 255, 111, 111, 255, 200, 0};
	private PImage[] tesla = new PImage[2];
	private Move[] moves = new Move[] {new EightDirectional(new Rect(400-14*3, 400-14*3, 28, 28), 3, 3), new PlatformerSimple()};
	private Ability[][] abilities = new Ability[][] {{new Walk8d(), new Sword8d(88), new Sprint8d(16), new Interact8d(90)}, {}};
	private Ability[] abilities2 = new Ability[] {new Walk8d(), new Sword8d(), new Sprint8d()};
	PImage bck1, tile1;
	Level tutorial;
	Room[] test = new Room[2];
	MultiState p;

	public static void main(String[] args) {
		PApplet.main(Main.class);
	}
	
	// Only used for the size of the canvas
	@Override
	public void settings() {
		size(800, 800);
		noSmooth();
	}
	
	@Override
	public void setup() {
//		frameRate(9999);
		surface.setTitle("GAME TEST");
		surface.setIcon(loadImage("src/Assets/Sprites/icon64.png"));
		textFont(createFont("src/Assets/Fonts/TeslaCrashToFont.ttf", 36, false));
		noCursor(); noStroke(); textSize(20);
		tesla[0] = loadImage("src/Assets/Sprites/Tesla/Tesla_Overworld.png");
		tesla[1] = loadImage("src/Assets/Sprites/Tesla/Tesla_Battle.png");
		bck1 = loadImage("src/Assets/Sprites/Background/background1.png");
		ToolKit.setApp(this);
		test[0] = new Room(bck1); test[1] = new Room(bck1);
		p = new MultiState(new Entities[] {Entities.PLAYER, Entities.PLAYER}, test, tesla, moves, abilities, PlayerSpriteLayers, PlayerColorTints, false, false);
		
		for (int i = 0; i < 40; i++) {
			test[0].add((float) Math.random() * (bck1.width-28*3), (float) Math.random() * (bck1.height-28*3), (float) (Math.random() * 190)+10, (float) (Math.random() * 190)+10);
		}
		// five hundred teslas
		for (int i = 0; i < 500; i++) {
			Move[] moves = new Move[] {new EightDirectional(new Rect((float)Math.random()*(bck1.width-28*3), (float)Math.random()*(bck1.height-28*3), 28, 28), 3, 3), new PlatformerSimple()};
//			MoveSet[] moves = new MoveSet[] {new EightDirectionalMove(new Rect(0, 0, 28, 28), 3, 3), new PlatformerSimpleMove()};
			int[] EnemyColorTints = {255, 111, 111, 111, 111, 255, 255, 200, 0};
			for (int j = 0; j < EnemyColorTints.length; j++) {EnemyColorTints[j] = (int) (Math.random() * 256);}
			test[0].add(new Enemy(test[0], tesla[0], moves[0], abilities2, PlayerSpriteLayers[0], EnemyColorTints));
		}
//		
	}
	
	@Override
	public void draw() {
		background(50);
		
		test[0].update();
		
//		System.out.println(test.getSize());
		
//		for (int i = 0; i < 2; i++) {
//			MoveSet[] moves = new MoveSet[] {new EightDirectionalMove(new Rect((float)Math.random()*(bck1.width-28*3), (float)Math.random()*(bck1.height-28*3), 28, 28), 3, 3), new PlatformerSimpleMove()};
//			int[] EnemyColorTints = {255, 111, 111, 111, 111, 255, 255, 200, 0};
//			for (int j = 0; j < EnemyColorTints.length; j++) {EnemyColorTints[j] = (int) (Math.random() * 256);}
//			test[0].add(new Enemy(test[0], tesla[0], moves[0], abilities2, PlayerSpriteLayers[0], EnemyColorTints));
//		}
		
		textSize(36); text(Math.round(this.frameRate)+"fps", 10, 30);
		
	}
	
	@Override
	public void keyPressed() {
		ToolKit.setKey(this.keyCode, true);
	}
	
	@Override
	public void keyReleased() {
		ToolKit.setKey(this.keyCode, false);
	}
}