package engine.util.data;

public interface Encryption {
	
	public abstract byte[] encrypt(byte[] bytes);
	
	public abstract byte[] decrypt(byte[] bytes);
	
}
