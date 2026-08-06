package engine.util.data.compressions;

import engine.util.ByteHelper;
import engine.util.data.Compression;

public class Raw implements Compression {
	
	@Override
	public byte[] compress(byte[] bytes) {return bytes;}
	@Override
	public byte[] decompress(byte[] bytes) {return bytes;}
	@Override
	public int getSavedSpace() {return 0;}
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}
	@Override
	public Compression deserialize(ByteHelper bytes) throws ReflectiveOperationException {
		return new Raw();
	}

}
