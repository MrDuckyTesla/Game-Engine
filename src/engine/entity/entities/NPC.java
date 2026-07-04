package engine.entity.entities;

import engine.Room;
import engine.entity.Ability;
import engine.entity.Entity;
import engine.entity.Move;
import engine.entity.Trigger;
import engine.entity.abilities.*;
import engine.entity.enums.Abilities;
import engine.entity.enums.Entities;
import engine.entity.enums.Triggers;
import engine.entity.movement.*;
import engine.util.ToolKit;
import processing.core.PImage;

public class NPC extends Entity{
	
	private boolean isMarked = false;
	private int framesDie = 10;
	private long timeWander = 0;
	private Trigger trigger;

	public NPC(Room room, PImage img, Move move, Ability[] abilities, int[][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		super(room, img, move, abilities, colorLayers, colorTints, isTangible, isBreakable);
	}

	public void update() {
		super.update(); this.wander(); 
		if (this.isMarked) {this.framesDie--;}
	}
	
	private void wander() {
		if (this.timeWander < System.currentTimeMillis()) {
			this.timeWander = System.currentTimeMillis() + Math.round(Math.random() * 1000);
			this.getMoveSet().setDir((int)(Math.random() * 8));
			this.getMoveSet().setIdle(Math.random() > 0.5);
			for (Ability a : this.getAbilities()) {
				if (a.getType() == Abilities.SWORD_EIGHT_DIR) {
					a.setActive(Math.random() > 0.99);
				} else {
					a.setActive(Math.random() > 0.8);
				}
			}
		}
	}
	
	private void markDelete() {if (!this.isMarked) {this.isMarked = true; ToolKit.changeColor(ToolKit.getApp(), this.getImg(), this.getColorList(), Entity.hurtColor);}}
	
	@Override
	public void interact(Trigger t) {
		this.trigger = t;
		switch(t.getTriggerType()) {
			case Triggers.DELETE:
				if (!this.isMarked()) {this.markDelete();}
				break;
			case Triggers.INTERACT:
				if (!this.isMarked()) {
					ToolKit.getApp().text("Hello.", this.getX(), this.getY());
					this.setOverState(true);
				} break;
			default:
				break;
		}
	}
	
	@Override
	public Entities getType() {return Entities.NON_PLAYER_CHARACTER;}
	@Override
	public boolean isDelete() {return this.framesDie < 0;}
	@Override
	public boolean isMarked() {return this.isMarked;}
	@Override
	public Trigger getTrigger() {return this.trigger;}

}
