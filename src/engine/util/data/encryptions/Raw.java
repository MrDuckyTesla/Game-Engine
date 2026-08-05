package engine.util.data.encryptions;

import engine.util.data.Encryption;

public class Raw implements Encryption {

	@Override
	public byte[] encrypt(byte[] bytes) {return bytes;}
	@Override
	public byte[] decrypt(byte[] bytes) {return bytes;}

}
