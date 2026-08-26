package engine.data;

import engine.data.serializations.FastSerializable;

public interface Encryption extends FastSerializable<Encryption> {
	
	public abstract byte[] encrypt(byte[] bytes);
	
	public abstract byte[] decrypt(byte[] bytes);
	
}
