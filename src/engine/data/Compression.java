package engine.data;

import engine.data.serializations.FastSerializable;

public interface Compression extends FastSerializable<Compression> {
	
	public abstract byte[] compress(byte[] bytes);
	
	public abstract byte[] decompress(byte[] bytes);
	
	public abstract int getSavedSpace();

}
