package engine.data;

public interface Encryption {
	
	public byte[] encrypt(byte[] bytes);
	
	public byte[] decrypt(byte[] bytes);
	
}
