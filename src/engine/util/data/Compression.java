package engine.util.data;

public interface Compression extends Serializable<Compression> {
	
	public abstract byte[] compress(byte[] bytes);
	
	public abstract byte[] decompress(byte[] bytes);
	
	public abstract int getSavedSpace();

}
