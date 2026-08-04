package engine.data;

public interface Storage {
	
	public void save(byte[] bytes);
	
	public byte[] load(String name);
	
}
