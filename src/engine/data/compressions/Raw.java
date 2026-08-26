package engine.data.compressions;

import engine.data.Compression;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

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
	public Compression deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new Raw();
	}
	@Override
	public Compression[] getProtoArray(int length) {
		return new Raw[length];
	}
	
}
