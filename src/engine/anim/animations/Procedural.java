package engine.anim.animations;

import engine.anim.Animation;
import engine.anim.Motion;
import engine.anim.sources.Image;

public class Procedural implements Animation {

	public Procedural(MovingImage...images) {
		// TODO Auto-generated constructor stub
	}
	
	public class MovingImage {
		
		private final Motion mot;
		private final Image img;

		public MovingImage(Motion mot, Image img) {
			this.mot = mot; this.img = img;
		}
		
		public Image getImg() {return this.img;}
		public Motion getMotion() {return this.mot;}

	}

	@Override
	public void draw(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
