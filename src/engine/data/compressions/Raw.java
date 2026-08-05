package engine.data.compressions;

import engine.data.Compression;

public class Raw implements Compression {
	
	@Override
	public byte[] compress(byte[] bytes) {return bytes;}
	@Override
	public byte[] decompress(byte[] bytes) {return bytes;}
	@Override
	public int getSavedSpace() {return 0;}

}
