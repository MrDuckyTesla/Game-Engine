package engine.graphics.anim;

public interface Source {
	
	public abstract Frame getFrame(int frame);
	
	public abstract int totalFrames();
	
}
