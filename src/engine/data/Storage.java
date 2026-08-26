package engine.data;

import java.io.IOException;

import engine.data.serializations.FastSerializable;

public interface Storage extends FastSerializable<Storage> {
	
	public abstract void save(byte[] bytes) throws IOException;
	
	public abstract byte[] load() throws IOException;
	
}
