package engine.util.data.encryptions;

import engine.util.ByteHelper;
import engine.util.data.Encryption;

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
	public Encryption deserialize(ByteHelper bytes) throws ReflectiveOperationException {
		return new Caesar();
	}

}
