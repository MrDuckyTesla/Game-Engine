package engine.data;

import java.nio.ByteBuffer;

public class ByteHelper {
	
	public static byte[] mergeBytes(byte[]...params) {
		int total = 0;  // Total size across all arrays
		for (byte[] b : params) {total += b.length;}
		byte[] rtrn = new byte[total]; int offset = 0;
		for (byte[] b : params) {
			System.arraycopy(b, 0, rtrn, offset, b.length);
			offset += b.length;
		} return rtrn;
	}
	
	public static byte[] toBytes(String[] s) {
		byte[][] bytes = new byte[s.length][];
		for (int i = 0; i < s.length; i++) {
			bytes[i] = toBytes(s[i]);
		} return ByteHelper.mergeBytes(bytes);
	}
	
	public static byte[] toBytes(float[] f) {
		ByteBuffer b = ByteBuffer.allocate(f.length*4);
		for (float l : f) {b.putFloat(l);} return b.array();
	}
	
	public static byte[] toBytes(int[] i) {
		ByteBuffer b = ByteBuffer.allocate(i.length*4);
		for (int n : i) {b.putInt(n);} return b.array();
	}

	public static byte[] toBytes(char[] c) {
		ByteBuffer b = ByteBuffer.allocate(c.length*2);
		for (char h : c) {b.putChar(h);} return b.array();
	}
	
	public static byte[] toBytes(boolean[] b) {
		byte[] bytes = new byte[b.length];
		for (int i = 0; i < b.length; i++) {
			bytes[i] = (byte) (b[i] ? 1 : 0);
		} return bytes;
	}

	public static byte[] toBytes(String s) {
		byte[] b = s.getBytes(java.nio.charset.StandardCharsets.UTF_8);
		return ByteHelper.mergeBytes(ByteHelper.toBytes(b.length), b);
	}
	
	public static byte[] toBytes(float f) {
		return ByteBuffer.allocate(4).putFloat(f).array();
	}
	
	public static byte[] toBytes(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}

	public static byte[] toBytes(char c) {
		return ByteBuffer.allocate(2).putChar(c).array();
	}
	
	public static byte toBytes(boolean b) {
		return (byte) (b ? 1 : 0);
	}

}
