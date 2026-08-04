package engine.data.encryptions;

import engine.data.Encryption;

public class Raw implements Encryption {

	@Override
	public byte[] encrypt(byte[] bytes) {return bytes;}
	@Override
	public byte[] decrypt(byte[] bytes) {return bytes;}

}
