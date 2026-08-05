package engine.data;

import java.io.IOException;

public interface Storage {
	
	public void save(byte[] bytes) throws IOException;
	
	public byte[] load() throws IOException;
	
}
