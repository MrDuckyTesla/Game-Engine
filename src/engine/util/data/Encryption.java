package engine.util.data;

public interface Encryption extends Serializable<Encryption> {
	
	public abstract byte[] encrypt(byte[] bytes);
	
	public abstract byte[] decrypt(byte[] bytes);
	
}
