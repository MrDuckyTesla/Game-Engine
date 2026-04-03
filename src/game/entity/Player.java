package game.entity;

import game.ToolKit;
import game.entity.abilities.Ability;
import game.entity.movement.MoveSet;
import game.entity.movement.Moves;
import processing.core.PImage;

public class Player extends Character {
	
	public Player(PImage[] img, MoveSet[] move, Ability[] abilities, int[][][] colorLayers, int[] colorTints) {super(img, move, abilities, colorLayers, colorTints);}

	@Override
	public void update() {
		super.update();
		
		if (this.getMoveSet().getMoveType() == Moves.eightDirectional) {
		    if (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39) || ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40) || ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37) || ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) {this.setOverState(false);}
		    else {this.setOverState(true);}
		    // Check direction
		    if ((ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) && (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40))) {this.setOverDir(1);}       // Walk Right - Down
		    else if ((ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) && (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37))) {this.setOverDir(3);}  // Walk Down - Left
		    else if ((ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) && (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38))) {this.setOverDir(5);}  // Left - Up
		    else if ((ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) && (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39))) {this.setOverDir(7);}  // Up - Right
		    else if (ToolKit.keyIsDown(68) || ToolKit.keyIsDown(39)) {this.setOverDir(0);}  // Walk Right
		    else if (ToolKit.keyIsDown(83) || ToolKit.keyIsDown(40)) {this.setOverDir(2);}  // Walk Down
		    else if (ToolKit.keyIsDown(65) || ToolKit.keyIsDown(37)) {this.setOverDir(4);}  // Walk Left
		    else if (ToolKit.keyIsDown(87) || ToolKit.keyIsDown(38)) {this.setOverDir(6);}  // Walk Up
		}
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Entity getType() {return Entity.Player;}

}
