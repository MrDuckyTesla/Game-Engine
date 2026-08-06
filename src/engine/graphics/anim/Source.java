package engine.graphics.anim;

import java.awt.image.BufferedImage;

public interface Source {
	
	public abstract BufferedImage getFrame(int frame);
	
	public abstract int totalFrames();
	
}
