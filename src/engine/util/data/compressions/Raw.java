package engine.util.data.compressions;

import engine.util.ByteHelper;
import engine.util.data.Compression;
import engine.util.data.Serializable;

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
	public Compression deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new Raw();
	}
	@Override
	public Compression[] getProtoArray(int length) {
		return new Raw[length];
	}
	
}
