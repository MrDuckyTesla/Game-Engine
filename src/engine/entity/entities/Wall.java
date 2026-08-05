package engine.entity.entities;

import engine.Room;
import engine.entity.Ability;
import engine.entity.Move;
import engine.entity.Trigger;
import engine.entity.enums.Entities;
import engine.entity.movement.*;
import obsolete.ToolKit;
import processing.core.PImage;

public class Wall extends NPC{
	
	private float imgX, imgY;
	private int imgS;

	public Wall(Room room, PImage img, Move move, Ability[] abilities, int[][] colorLayers, int[] colorTints, boolean isTangible, boolean isBreakable) {
		super(room, img, move, abilities, colorLayers, colorTints, isTangible, isBreakable);
	} public Wall(PImage img, float x, float y, float w, float h, float imgX, float imgY, int imgS) {
		super(null, img, new ObjectAffected(x, y, w, h), new Ability[0], null, null, true, true); this.imgX = imgX; this.imgY = imgY; this.imgS = imgS;
	} public Wall(PImage img, float x, float y, float w, float h) {super(null, img, new ObjectAffected(x, y, w, h), new Ability[0], null, null, true, true);}
	public Wall(float x, float y, float w, float h) {super(null, null, new ObjectAffected(x, y, w, h), null, null, null, true, true);}
	
	public void show() {
		if (this.getImg() == null) {
			ToolKit.rectApp(this.getX(), this.getY(), this.getW(),this.getH());
		} else {ToolKit.getApp().image(getImg(), imgX+this.getX(), imgY+this.getY(), getImg().width*imgS, getImg().height*imgS);}
	}

	@Override
	public void interact(Trigger t) {
		
	}
	
	@Override
	public Entities getType() {return Entities.NON_PLAYER_CHARACTER;}

	@Override
	public boolean isDelete() {return false;}

}
