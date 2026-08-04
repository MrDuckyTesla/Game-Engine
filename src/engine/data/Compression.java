package engine.data;

public interface Compression {
	
	public byte[] compress(byte[] bytes);
	
	public byte[] decompress(byte[] bytes);

}
