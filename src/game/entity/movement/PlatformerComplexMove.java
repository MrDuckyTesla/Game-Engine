package game.entity.movement;

import java.util.ArrayList;

import game.entity.Obstacle;
import game.entity.abilities.Ability;

// Version with Inverse Kinematics, and procedural Animation
public class PlatformerComplexMove extends PlatformerSimpleMove {
	
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c, Ability ability) {ability.update(c, this); this.move(room, c);}
	@Override
	public void move(ArrayList<Obstacle> room, Obstacle c, Ability[] abilities) {for (Ability a : abilities) {a.update(c, this);} this.move(room, c);}

}
