package engine.data.compressions;

import java.io.ByteArrayOutputStream;

import engine.data.Compression;

public class RLE implements Compression {
	
	private int before = 0, after = 0;

	@Override
	public byte[] compress(byte[] bytes) {
		if (bytes.length == 0) {return bytes;}
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		this.before = bytes.length; int count = 1;
		for (int i = 1; i < bytes.length; i++) {
			if (bytes[i-1] == bytes[i] && count < 255) {count++;} 
			else {b.write(count); b.write(bytes[i-1]); count = 1;}
		} b.write(count); b.write(bytes[bytes.length-1]);
		byte[] a = b.toByteArray(); this.after = a.length; return a;
	}

	@Override
	public byte[] decompress(byte[] bytes) {
		if (bytes.length == 0) {return bytes;}
		if (bytes.length % 2 != 0) {
			throw new IllegalArgumentException("data length should be even");
		} ByteArrayOutputStream y = new ByteArrayOutputStream();
		for (int i = 0; i < bytes.length-1; i+=2) {
			for (int j = 0; j < (bytes[i] & 0xFF); j++) {
				y.write(bytes[i+1]);
			}
		} return y.toByteArray();
	}

	@Override
	public int getSavedSpace() {return this.before - this.after;}

}
