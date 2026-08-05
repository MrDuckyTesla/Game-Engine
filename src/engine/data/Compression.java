package engine.data;

public interface Compression {
	
	public abstract byte[] compress(byte[] bytes);
	
	public abstract byte[] decompress(byte[] bytes);
	
	public abstract int getSavedSpace();

}
