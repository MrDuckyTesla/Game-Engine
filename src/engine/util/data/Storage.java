package engine.util.data;

import java.io.IOException;

public interface Storage extends Serializable<Storage> {
	
	public abstract void save(byte[] bytes) throws IOException;
	
	public abstract byte[] load() throws IOException;
	
}
