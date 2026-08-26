package engine.data.encryptions;

import engine.data.Encryption;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

public class Caesar implements Encryption {

	public Caesar() {
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
	public Encryption deserialize(ByteHelper b, FastSerializable<?>... prototypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Encryption[] getProtoArray(int length) {
		// TODO Auto-generated method stub
		return null;
	}

}
