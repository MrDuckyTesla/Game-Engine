package engine.util.data.encryptions;

import engine.util.ByteHelper;
import engine.util.data.Encryption;
import engine.util.data.Serializable;

public class Symmetric implements Encryption {

	public Symmetric() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] encrypt(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] decrypt(byte[] bytes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] serialize() {
		return new byte[] {};
	}

	@Override
	public Encryption deserialize(ByteHelper b, Serializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Encryption[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
