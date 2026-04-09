package game.entity.abilities;

import game.entity.*;
import game.entity.movement.*;
import game.entity.trigger.Interaction;
import game.entity.trigger.Triggers;
import game.util.*;

public class Interact8d extends Ability {
	
	private boolean isActive = false;

	public Interact8d() {}
	public Interact8d(int key) {super(key);}
	public Interact8d(int[] keys) {super(keys);}
	
	public static void createInteraction8d(MoveSet m, Entity e, Triggers t) {
		float halfW = m.getSW()/2, halfH = m.getSH()/2;
		int dir = m.getDir(); Point xy = new Point();
		if (dir % 4 != 2) {xy.setX(dir % 7 < 2? halfW : -halfW);}
		if (dir % 4 != 0) {xy.setY(dir < 4? halfH : -halfH);}
		if (dir % 2 == 1) {xy.multpilyXY(0.7071068f);}  // sin 45
		Entity.addtrigger(new Interaction(m.getX() + xy.getX() + halfW/2, m.getY() + xy.getY() + halfH/2, halfW, halfH, e, t));
	}

	@Override
	public void update(Entity e, MoveSet m) throws IllegalArgumentException {
		if (m.getMoveType() != Moves.eightDirectional) {throw new IllegalArgumentException();}
		if (this.getKeys() != null) {
			for (int key : this.getKeys()) {this.isActive = ToolKit.keyIsDown(key);}
		}
		if (this.isActive) {
			Interact8d.createInteraction8d(m, Entity.getPlayer(), Triggers.INTERACT);
//			ToolKit.getApp().rect(m.getX() + xy.getX() + halfW/2, m.getY() + xy.getY() + halfH/2, halfW, halfH);
		}
	}
	
	@Override
	public void setActive(boolean activate) {this.isActive = activate;}
	@Override
	public boolean isActive() {return this.isActive;}
	@Override
	public Ability get() {return this.getKeys() == null? new Interact8d() : new Interact8d(this.getKeys());}
	@Override
	public Abilities getType() {return Abilities.INTERACT_EIGHT_DIR;}

}
