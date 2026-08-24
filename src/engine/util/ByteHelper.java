package engine.util;

import java.util.*;
import java.lang.reflect.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import engine.util.data.Serializable;
import engine.util.data.SerializableRegistry;

public class ByteHelper {
	
	private static Serializable<?>[] PROTOTYPES;
	
	public static short getID(Class<?> c) {
		return SerializableRegistry.IDS.get(c);
	}
	
	public static Class<?> getClass(short id) {
		return SerializableRegistry.REGISTRY[id];
	}
	
	public static Serializable<?> getProto(short id) {
		return ByteHelper.PROTOTYPES[id];
	}
	
	static {
		ByteHelper.PROTOTYPES = new Serializable[SerializableRegistry.REGISTRY.length];
		for (int i = 0; i < ByteHelper.PROTOTYPES.length; i++) {
			ByteHelper.PROTOTYPES[i] = ByteHelper.classConstructor(SerializableRegistry.REGISTRY[i]);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T classConstructor(Class<?> type) {
		Constructor<?>[] con = type.getDeclaredConstructors(); 
		Arrays.sort(con, Comparator.comparingInt(Constructor::getParameterCount));
		for (int i = 0; i < con.length; i++) {
			if (con[i].getParameterCount() == 0) {
				try {con[i].setAccessible(true); return (T) con[i].newInstance();} 
				catch (Exception e) {e.printStackTrace();}
			} Parameter[] prm = con[i].getParameters();
			Object[] obj = new Object[prm.length];
			try {
				for (int j = 0; j < obj.length; j++) {
					obj[j] = ByteHelper.recursiveClass(prm[j].getType());
				} return (T) con[i].newInstance(obj);
			} catch (Exception e) {
				System.out.println("Could not instantiate "+type.getCanonicalName()+" to registry due to "+e.getCause()+".");
				e.printStackTrace(); 
			}
		} return null;
	}
	
	private static Object recursiveClass(Class<?> type) throws ReflectiveOperationException {
		if (type.isInterface()) {return null;}
		if (type.isArray()) {return Array.newInstance(type.getComponentType(), 0);}
		if (type == boolean.class) {return false;}
		if (type == byte.class) {return (byte) 0;}
		if (type == short.class) {return (short) 0;}
		if (type == char.class) {return '\u0000';}
		if (type == int.class) {return 0;}
		if (type == float.class) {return 0f;}
		if (type == double.class) {return 0d;}
		if (type == long.class) {return 0l;}
		if (type == String.class) {return "";}
		return ByteHelper.classConstructor(type);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable<?>> T load(byte[] bytes) {
		ByteHelper b = new ByteHelper(bytes);
		Serializable<?> proto = ByteHelper.getProto(b.readShort());
		return (T) proto.deserialize(b);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Serializable<?>> T load(byte[] bytes, T proto) {
		ByteHelper b = new ByteHelper(bytes); b.readShort();
		return (T) proto.deserialize(b);
	}
	
	/**
	 * Merges multiple byte[] arrays into a singular flat byte[] array
	 * @param params byte arrays to flatten
	 * @return Singular flattened array of bytes
	 */
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
	 * @param <T> Object that implements Serializable
	 * @param o Object array being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [ID][Array Length][[ID][Byte Length][Object], ...[ID][Byte Length][Object]]
	 */
	public static <T extends Serializable<T>> byte[] toBytes(T[] o) {
		if (o.length == 0) {throw new IllegalArgumentException("Given array was length 0");}
		byte[][] bytes = new byte[o.length+2][]; bytes[0] = ByteHelper.toBytes(ByteHelper.getID(o[0].getClass())); 
		for (int i = 0; i < o.length; i++) {bytes[i+2] = ByteHelper.toBytes(o[i]);}
		bytes[1] = ByteHelper.toBytes(o.length); return ByteHelper.mergeBytes(bytes);
	}
	
	/**
	 * Function that serializes an array of strings without an ID
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
	
	/**
	 * Function that serializes an array of floats
	 * @param f Float array being serialized
	 * @return The float array in byte form, the bytes are ordered as so:
	 * [Array Length][[Float], ...[Float]]
	 */
	public static byte[] toBytes(float[] f) {
		ByteBuffer b = ByteBuffer.allocate(f.length*4+4);
		b.putInt(f.length); for (float l : f) {b.putFloat(l);} return b.array();
	}
	
	/**
	 * Function that serializes an array of integers
	 * @param i Int array being serialized
	 * @return The int array in byte form, the bytes are ordered as so:
	 * [Array Length][[Int], ...[Int]]
	 */
	public static byte[] toBytes(int[] i) {
		ByteBuffer b = ByteBuffer.allocate(i.length*4+4);
		b.putInt(i.length); for (int n : i) {b.putInt(n);} return b.array();
	}
	
	/**
	 * Function that serializes an array of characters
	 * @param c Char array being serialized
	 * @return The char array in byte form, the bytes are ordered as so:
	 * [Array Length][[Char], ...[Char]]
	 */
	public static byte[] toBytes(char[] c) {
		ByteBuffer b = ByteBuffer.allocate(c.length*2+4);
		b.putInt(c.length); for (char h : c) {b.putChar(h);} return b.array();
	}
	
	/**
	 * Function that serializes an array of booleans
	 * @param b Bool array being serialized
	 * @return The bool array in byte form, the bytes are ordered as so:
	 * [Array Length][[Bool], ...[Bool]]
	 */
	public static byte[] toBytes(boolean[] b) {
		ByteBuffer y = ByteBuffer.allocate(b.length+4);  y.putInt(b.length); 
		for (boolean o : b) {y.put((byte) (o ? 1 : 0));} return y.array();
	}
	
	/**
	 * Function that serializes an object
	 * @param <T> Object that implements Serializable
	 * @param object  Object being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [ID][Byte Length][Object]
	 */
	public static <T extends Serializable<T>> byte[] toBytes(T object) {
		byte[] bytes = object.serialize();
		return ByteHelper.mergeBytes(
			ByteHelper.toBytes(ByteHelper.getID(object.getClass())),
			ByteBuffer.allocate(4).putInt(bytes.length).array(), 
			bytes
		);
	}
	
	/**
	 * Function that serializes a string
	 * @param s String to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [String Length][String]
	 */
	public static byte[] toBytes(String s) {
		byte[] b = s.getBytes(StandardCharsets.UTF_8);
		return ByteHelper.mergeBytes(ByteHelper.toBytes(b.length), b);
	}
	
	/**
	 * Function that serializes a float
	 * @param f Float to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [Float]
	 */
	public static byte[] toBytes(float f) {
		return ByteBuffer.allocate(4).putFloat(f).array();
	}
	
	/**
	 * Function that serializes an integer
	 * @param i Int to be serialized
	 * @return The int in byte form, the bytes are ordered as so:
	 * [Int]
	 */
	public static byte[] toBytes(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}
	
	/**
	 * Function that serializes a short
	 * @param s Short to be serialized
	 * @return The short in byte form, the bytes are ordered as so:
	 * [Short]
	 */
	public static byte[] toBytes(short s) {
		return ByteBuffer.allocate(2).putShort(s).array();
	}
	
	/**
	 * Function that serializes a character
	 * @param c Int to be serialized
	 * @return The char in byte form, the bytes are ordered as so:
	 * [Char]
	 */
	public static byte[] toBytes(char c) {
		return ByteBuffer.allocate(2).putChar(c).array();
	}
	
	/**
	 * Function that serializes a boolean as an array
	 * @param b Bool to be serialized
	 * @return The bool in byte form, the bytes are ordered as so:
	 * [Bool]
	 */
	public static byte[] toBytes(boolean b) {
		return new byte[] {(byte) (b ? 1 : 0)};
	}
	
	/**
	 * Function that serializes a boolean
	 * @param b Bool to be serialized
	 * @return The bool in byte form, the bytes are ordered as so:
	 * [Bool]
	 */
	public static byte toByte(boolean b) {
		return (byte) (b ? 1 : 0);
	}
	
	private final ByteBuffer byteStream;
	
	public ByteHelper(byte[] bytes) {this.byteStream = ByteBuffer.wrap(bytes);}
	
	public boolean hasRemaining () {return this.byteStream.hasRemaining();}
	
	public byte[] get() {return this.byteStream.array();}
	
	// [ID][Array Length][[ID][Byte Length][Object], ...[ID][Byte Length][Object]]
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr() {
		T[] proto = (T[]) Array.newInstance(ByteHelper.getClass(this.readShort()), this.readInt());
		for (int i = 0; i < proto.length; i++) {
			proto[i] = this.readObject();
		} return (T[]) proto;
	}
	
	// [ID][Array Length][[ID][Byte Length][Object], ...[ID][Byte Length][Object]]
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr(T proto) {
		T[] arr = (T[]) Array.newInstance(ByteHelper.getClass(this.readShort()), this.readInt());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = this.readObject(proto);
		} return (T[]) arr;
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
	
	public short[] readShortArr() {
		short[] rtrn = new short[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readShort();
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
	
	public <T extends Serializable<T>> T readObject(T proto) {
		return proto.deserialize(new ByteHelper(this.byteStream.get(new byte[this.readInt()]).array()));
	}
	
	//[ID][Byte Length][Object]
	@SuppressWarnings("unchecked")
	public <T extends Serializable<?>> T readObject() {
		short id = this.readShort();
		Serializable<T> proto = (Serializable<T>) ByteHelper.getProto(id);
		if (proto == null) {
		    throw new IllegalStateException(
		        "No automatic deserialization prototype exists for Class " + 
		        ByteHelper.getClass(id).getName() + ". Use readObject(prototype) instead."
		    );
		} return proto.deserialize(new ByteHelper(this.byteStream.get(new byte[this.readInt()]).array()));
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
	
	public short readShort() {
		return this.byteStream.getShort();
	}
	
	public char readChar() {
		return this.byteStream.getChar();
	}
	
	public boolean readBool() {
		return this.byteStream.get() == 1;
	}

}
