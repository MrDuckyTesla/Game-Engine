package game;

import game.entity.*;
import game.entity.abilities.Ability;
import game.entity.abilities.Sprint8d;
import game.entity.abilities.Sword8d;
import game.entity.abilities.Walk8d;
import game.entity.movement.*;
import game.entity.movement.MoveSet;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
	
	Rect[] obstacles;
	private final int[][][] PlayerSpriteLayers = {{{180, 157, 130, 31}, {187, 171}, {190, 163, 140}}, {{105, 85, 34}, {104}}};
	private int[] PlayerColorTints = {111, 111, 255, 255, 111, 111, 255, 200, 0};
	private PImage[] tesla = new PImage[] {null, null};
	private MoveSet[] moves = new MoveSet[] {new EightDirectionalMove(new Rect(400, 400, 28, 28), 3, 3), new PlatformerSimpleMove()};
	private Ability[] abilities = new Ability[] {new Walk8d(), new Sword8d(88), new Sprint8d(16)};
	private Ability[] abilities2 = new Ability[] {new Walk8d(), new Sword8d(), new Sprint8d()};
	PImage bck1, tile1;
	Level tutorial;
	Room test;
	Player p;

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
		surface.setTitle("Tesla: Crash to Earth");
		surface.setIcon(loadImage("src/Assets/Sprites/icon64.png"));
		textFont(createFont("src/Assets/Fonts/TeslaCrashToFont.ttf", 36, false));
		noCursor(); noStroke(); textSize(20);
		tesla[0] = loadImage("src/Assets/Sprites/Tesla/Tesla_Overworld.png");
		tesla[1] = loadImage("src/Assets/Sprites/Tesla/Tesla_Battle.png");
//		tesla[0] = image1;
//		colorList = Engine.PreCompile(this, image1, new int[][] {{180, 157, 130, 31}, {187, 171}, {190, 163, 140}});
//		Engine.changeColor(this, image1, colorList, new int[] {111, 111, 255, 255, 111, 111, 255, 200, 0});
		obstacles = new Rect[] {new Rect(150, 200, 500, 100), new Rect(0, 380, 120, 100), new Rect(600, 600, 120, 160), new Rect(260, 500, 180, 80), new Rect(600, 175, 100, 300)};
		CObstacle cobstacle = new CObstacle(obstacles);
		cobstacle.cleanArray();
		obstacles = cobstacle.getObstacleArray().clone();
		Point.setApp(this);
//		Point p1 = new Point(400, 400);
//		Point p2 = new Point(400, 500);
//		(PImage[] img, MoveSet[] move, int[][][] colorLayers, int[] colorTints, int[] scale)
		p = new Player(tesla, moves, abilities, PlayerSpriteLayers, PlayerColorTints);
		NonPlayerCharacter.setPlayer(p);
		test = new Room(p, bck1);
//		test.add(new Enemy(new PImage[] {tesla[0].copy(), tesla[1].copy()}, moves, abilities2, PlayerSpriteLayers, EnemyColorTints));
//		test.add(new Enemy(new PImage[] {tesla[0].copy(), tesla[1].copy()}, moves2, abilities2, PlayerSpriteLayers, EnemyColorTints));
//		test.add(200, 200, 100, 100);
//		test.add(300, 300, 100, 100);
		
		for (int i = 0; i < 5; i++) {
//			test.add(new float[] {(float) Math.random() * (800-28*3), (float) Math.random() * (800-28*3), (float) (Math.random() * 190)+10, (float) (Math.random() * 190)+10});
		}
		// five hundred teslas
		for (int i = 0; i < 10; i++) {
			MoveSet[] moves = new MoveSet[] {new EightDirectionalMove(new Rect((float)Math.random()*(800-28*3), (float)Math.random()*(800-28*3), 28, 28), 3, 3), new PlatformerSimpleMove()};
			int[] EnemyColorTints = {255, 111, 111, 111, 111, 255, 255, 200, 0};
			for (int j = 0; j < EnemyColorTints.length; j++) {
				EnemyColorTints[j] = (int) (Math.random() * 256);
			}
			test.add(new Enemy(tesla, moves, abilities2, PlayerSpriteLayers, EnemyColorTints));
		}
		
//		Level l1 = new Level();
//		Room r1 = new Room();
//		(PApplet app, Point overPosit, Point battPosit, Point scale, PImage overImage, PImage battImage, int[][] overColorLayer, int[][] battColorLayer, int[] overColorTint, int[] battColorTint)
//		r1.add(p);
//		l1.addRoom(r1);
	}
	
	@Override
	public void draw() {
		background(50);
		// PATHFINDIING
		
//		for (int i = 0; i < obstacles.length; i++) {
//			obstacles[i].display(this, new int[] {(int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)});
//		}
//		
//		ArrayList<Point> criticalPoints = Engine.pathfind(50, 700, min(mouseX, width-100), min(mouseY, height-100), 100, 100, obstacles, 0, 0, width, height);
//		for (int i = 0; i < criticalPoints.size(); i++) {
//			criticalPoints.get(i).display(this, 30);
//		}
//		System.out.println(criticalPoints);
//		Engine.lineDraw(this, min(mouseX, width-100)+50, min(mouseY, height-100)+50, 25*2, 350*2);
//		
//		rect(min(mouseX, width-100), min(mouseY, height-100), 100, 100);
//		circle(50, 700, 50);

		test.update();
//		p.update();
		
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