package engine.anim;

import java.awt.image.BufferedImage;

public interface Source {
	
	public abstract BufferedImage getFrame();
	
	public abstract BufferedImage getNext();
	
	public abstract int totalFrames();
	
}
