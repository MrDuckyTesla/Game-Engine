package engine.anim;

import java.awt.image.BufferedImage;

// Transparent, mirror, fade, etc.
public interface Effect {
	
	public abstract BufferedImage apply(BufferedImage img);

}
