package engine.util;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import engine.util.data.Serializable;

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
	
	/**
	 * Function that serializes an array of objects
	 * @param <T> Class being serialized
	 * @param o Object array being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
	 */
	public static <T extends Serializable<T>> byte[] toBytes(T[] o) {
		if (o.length == 0) {throw new IllegalArgumentException("Array length of 0");}
		byte[][] bytes = new byte[o.length+2][];
		bytes[0] = ByteHelper.objNameToBytes(o[0]);
		bytes[1] = ByteHelper.toBytes(o.length);
		for (int i = 0; i < o.length; i++) {
			bytes[i+2] = ByteHelper.objToBytes(o[i]);
		} return ByteHelper.mergeBytes(bytes);
	}
	
	private static <T extends Serializable<T>> byte[] objToBytes(T object) {
		byte[] bytes = object.serialize(); return ByteHelper.mergeBytes(
			ByteBuffer.allocate(4).putInt(bytes.length).array(), bytes
		);
	}
	
	/**
	 * Function that serializes an array of strings
	 * @param s String array being serialized
	 * @return The String in byte form, the bytes are ordered as so:
	 * [Array Length][[String Length][String], ...[String Length][String]]
	 */
	public static byte[] toBytes(String[] s) {
		byte[][] bytes = new byte[s.length+1][];
		bytes[0] = ByteHelper.toBytes(s.length);
		for (int i = 0; i < s.length; i++) {
			bytes[i+1] = ByteHelper.toBytes(s[i]);
		} return ByteHelper.mergeBytes(bytes);
	}
	
	public static byte[] toBytes(float[] f) {
		ByteBuffer b = ByteBuffer.allocate(f.length*4+4);
		b.putInt(f.length);
		for (float l : f) {b.putFloat(l);} return b.array();
	}
	
	public static byte[] toBytes(int[] i) {
		ByteBuffer b = ByteBuffer.allocate(i.length*4+4);
		b.putInt(i.length);
		for (int n : i) {b.putInt(n);} return b.array();
	}

	public static byte[] toBytes(char[] c) {
		ByteBuffer b = ByteBuffer.allocate(c.length*2+4);
		b.putInt(c.length);
		for (char h : c) {b.putChar(h);} return b.array();
	}
	
	public static byte[] toBytes(boolean[] b) {
		ByteBuffer y = ByteBuffer.allocate(b.length+4); 
		y.putInt(b.length);
		for (boolean o : b) {y.put((byte) (o ? 1 : 0));} 
		return y.array();
	}
	
	// [Object Type][Byte Length][Object]
	public static <T extends Serializable<T>> byte[] toBytes(T object) {
		byte[] bytes = object.serialize();
		return ByteHelper.mergeBytes(
				ByteHelper.objNameToBytes(object),
				ByteBuffer.allocate(4).putInt(bytes.length).array(), 
				bytes,
		);
	}
	
	private static <T extends Serializable<T>> byte[] objNameToBytes(T object) {
		return ByteHelper.toBytes(object.getClass().getName());
	}

	public static byte[] toBytes(String s) {
		byte[] b = s.getBytes(StandardCharsets.UTF_8);
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
	
	private final ByteBuffer byteStream;
	
	public ByteHelper(byte[] byteStream) {
		this.byteStream = ByteBuffer.wrap(byteStream);
	}
	
	// [Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
	// [Object Type][Byte Length][Object]
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr() throws ReflectiveOperationException {
		T prototype = ((Class<T>) Class.forName(this.readString())).getDeclaredConstructor().newInstance();
		T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(
				prototype.getClass(), 
				this.byteStream.getInt()
		); for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readObjNoStr(prototype);
		} return rtrn;
	}

	private <T extends Serializable<T>> T readObjNoStr(T prototype) throws ReflectiveOperationException {
		return prototype.deserialize(new ByteHelper(this.objHelper()));
	}
	
	public String[] readStringArr() {
		String[] rtrn = new String[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readString();
		} return rtrn;
	}
	
	public float[] readFloatArr() {
		float[] rtrn = new float[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readFloat();
		} return rtrn;
	}
	
	public int[] readIntArr() {
		int[] rtrn = new int[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readInt();
		} return rtrn;
	}
	
	public char[] readCharArr() {
		char[] rtrn = new char[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readChar();
		} return rtrn;
	}
	
	public boolean[] readBoolArr() {
		boolean[] rtrn = new boolean[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readBool();
		} return rtrn;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T readObj() throws ReflectiveOperationException {
		byte[] bytes = new byte[this.byteStream.getInt()];
		this.byteStream.get(bytes); String type = this.readString();
		T prototype = ((Class<T>) Class.forName(type)).getDeclaredConstructor().newInstance();
		return prototype.deserialize(new ByteHelper(bytes));
	}
	
	public String readString() {
		byte[] str = new byte[this.byteStream.getInt()]; 
		this.byteStream.get(str);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	public float readFloat() {
		return this.byteStream.getFloat();
	}
	
	public int readInt() {
		return this.byteStream.getInt();
	}
	
	public char readChar() {
		return this.byteStream.getChar();
	}
	
	public boolean readBool() {
		return this.byteStream.get() == 1;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr(T prototype) throws ReflectiveOperationException {
		this.readString(); T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(
			prototype.getClass(), this.byteStream.getInt()
		); for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readObj(prototype);
		} return rtrn;
	}
	
	public <T extends Serializable<T>> T readObj(
		Class<T> type, Class<?>[] constructorParams,  Object...params) throws ReflectiveOperationException {
		this.readString();
		return type.getDeclaredConstructor(constructorParams)
			.newInstance(params).deserialize(new ByteHelper(this.objHelper()));
	}
		
	public <T extends Serializable<T>> T readObj(T prototype) throws ReflectiveOperationException {
		this.readString(); T rtrn = prototype.deserialize(new ByteHelper(this.objHelper())); return rtrn;
	}
		
	private byte[] objHelper() {
		byte[] bytes = new byte[this.byteStream.getInt()];
		this.byteStream.get(bytes); return bytes;
	}

}
