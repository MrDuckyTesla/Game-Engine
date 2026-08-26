package engine.data.encryptions;

import engine.data.Encryption;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

public class Raw implements Encryption {

	@Override
	public byte[] encrypt(byte[] bytes) {return bytes;}
	@Override
	public byte[] decrypt(byte[] bytes) {return bytes;}
	@Override
	public byte[] serialize() {
		return new byte[] {};
	}
	@Override
	public Encryption deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		return new Raw();
	}
	@Override
	public Encryption[] getProtoArray(int length) {
		return new Raw[length];
	}

}
