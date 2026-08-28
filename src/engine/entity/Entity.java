package engine.entity;

public interface Entity extends Comparable<Entity> {

	public abstract void update();
	
	public abstract void show();

}
