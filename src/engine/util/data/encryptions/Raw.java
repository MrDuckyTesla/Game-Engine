package engine.util.data.encryptions;

import engine.util.ByteHelper;
import engine.util.data.Encryption;
import engine.util.data.Serializable;

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
	public Encryption deserialize(ByteHelper b, Serializable<?>... prototypes) {
		return new Raw();
	}
	@Override
	public Encryption[] getProtoArray(int length) {
		return new Raw[length];
	}

}
