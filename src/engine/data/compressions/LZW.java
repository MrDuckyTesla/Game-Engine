package engine.data.compressions;

import engine.data.Compression;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

public class LZW implements Compression {

	public LZW() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] compress(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] decompress(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSavedSpace() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Compression deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Compression[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
