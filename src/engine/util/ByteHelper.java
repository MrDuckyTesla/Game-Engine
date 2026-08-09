package engine.util;

import java.util.*;
import java.lang.reflect.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import engine.util.data.Serializable;

public class ByteHelper {
	
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
	 * [ID: 0][Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
	 */
	public static <T extends Serializable<T>> byte[] toBytes(T[] o) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 0), ByteHelper.toBytesNoID(o));
	}
	
	/**
	 * Function that serializes an array of objects without an ID
	 * @param <T> Object that implements Serializable
	 * @param o Object array being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
	 */
	private static <T extends Serializable<T>> byte[] toBytesNoID(T[] o) {
		if (o.length == 0) {throw new IllegalArgumentException("Given array was length 0");}
		byte[][] bytes = new byte[o.length+2][]; bytes[0] = ByteHelper.objNameToBytes(o[0]); 
		for (int i = 0; i < o.length; i++) {bytes[i+2] = ByteHelper.objToBytes(o[i]);}
		bytes[1] = ByteHelper.toBytes(o.length); return ByteHelper.mergeBytes(bytes);
	}
	
	/**
	 * Helper function that writes an object into a byte array
	 * @param <T> Object that implements Serializable
	 * @param object Object being serialized
	 * @return Byte array holding serialized object
	 */
	private static <T extends Serializable<T>> byte[] objToBytes(T object) {
		byte[] bytes = object.serialize(); return ByteHelper.mergeBytes(
			ByteBuffer.allocate(4).putInt(bytes.length).array(), bytes
		);
	}
	
	/**
	 * Function that serializes an array of strings
	 * @param s String array being serialized
	 * @return The String in byte form, the bytes are ordered as so:
	 * [ID: 1][Array Length][[String Length][String], ...[String Length][String]]
	 */
	public static byte[] toBytes(String[] s) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 1), ByteHelper.toBytesNoID(s));
	}
	
	/**
	 * Function that serializes an array of strings without an ID
	 * @param s String array being serialized
	 * @return The String in byte form, the bytes are ordered as so:
	 * [Array Length][[String Length][String], ...[String Length][String]]
	 */
	private static byte[] toBytesNoID(String[] s) {
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
	 * [ID: 2][Array Length][[Float], ...[Float]]
	 */
	public static byte[] toBytes(float[] f) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 2), ByteHelper.toBytesNoID(f));
	}
	
	/**
	 * Function that serializes an array of floats without an ID
	 * @param f Float array being serialized
	 * @return The float array in byte form, the bytes are ordered as so:
	 * [Array Length][[Float], ...[Float]]
	 */
	private static byte[] toBytesNoID(float[] f) {
		ByteBuffer b = ByteBuffer.allocate(f.length*4+4);
		b.putInt(f.length); for (float l : f) {b.putFloat(l);} return b.array();
	}
	
	/**
	 * Function that serializes an array of integers
	 * @param i Int array being serialized
	 * @return The int array in byte form, the bytes are ordered as so:
	 * [ID: 3][Array Length][[Int], ...[Int]]
	 */
	public static byte[] toBytes(int[] i) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 3), ByteHelper.toBytesNoID(i));
	}
	
	/**
	 * Function that serializes an array of integers without an ID
	 * @param i Int array being serialized
	 * @return The int array in byte form, the bytes are ordered as so:
	 * [Array Length][[Int], ...[Int]]
	 */
	private static byte[] toBytesNoID(int[] i) {
		ByteBuffer b = ByteBuffer.allocate(i.length*4+4);
		b.putInt(i.length); for (int n : i) {b.putInt(n);} return b.array();
	}

	/**
	 * Function that serializes an array of characters
	 * @param c Char array being serialized
	 * @return The char array in byte form, the bytes are ordered as so:
	 * [ID: 4][Array Length][[Char], ...[Char]]
	 */
	public static byte[] toBytes(char[] c) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 4), ByteHelper.toBytesNoID(c));
	}
	
	/**
	 * Function that serializes an array of characters without an ID
	 * @param c Char array being serialized
	 * @return The char array in byte form, the bytes are ordered as so:
	 * [Array Length][[Char], ...[Char]]
	 */
	private static byte[] toBytesNoID(char[] c) {
		ByteBuffer b = ByteBuffer.allocate(c.length*2+4);
		b.putInt(c.length); for (char h : c) {b.putChar(h);} return b.array();
	}
	
	/**
	 * Function that serializes an array of booleans
	 * @param b Bool array being serialized
	 * @return The bool array in byte form, the bytes are ordered as so:
	 * [ID: 5][Array Length][[Bool], ...[Bool]]
	 */
	public static byte[] toBytes(boolean[] b) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 5), ByteHelper.toBytesNoID(b));
	}
	
	/**
	 * Function that serializes an array of booleans without an ID
	 * @param b Bool array being serialized
	 * @return The bool array in byte form, the bytes are ordered as so:
	 * [Array Length][[Bool], ...[Bool]]
	 */
	private static byte[] toBytesNoID(boolean[] b) {
		ByteBuffer y = ByteBuffer.allocate(b.length+4);  y.putInt(b.length); 
		for (boolean o : b) {y.put((byte) (o ? 1 : 0));} return y.array();
	}
	
	/**
	 * Function that serializes an object
	 * @param <T> Object that implements Serializable
	 * @param object  Object being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [ID: 6][Object Type][Byte Length][Object]
	 */
	public static <T extends Serializable<T>> byte[] toBytes(T object) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 6), ByteHelper.toBytesNoID(object));
	}
	
	/**
	 * Function that serializes an object without an ID
	 * @param <T> Object that implements Serializable
	 * @param object  Object being serialized
	 * @return The object in byte form, the bytes are ordered as so:
	 * [Object Type][Byte Length][Object]
	 */
	private static <T extends Serializable<T>> byte[] toBytesNoID(T object) {
		byte[] bytes = object.serialize();
		return ByteHelper.mergeBytes(
			ByteHelper.objNameToBytes(object),
			ByteBuffer.allocate(4).putInt(bytes.length).array(), 
			bytes
		);
	}
	
	/**
	 * Serializes most of an objects metadata to an array
	 * @param <T> Object that implements Serializable
	 * @param object Object that needs metadata
	 * @return Metadata of object
	 */
	private static <T extends Serializable<T>> byte[] objNameToBytes(T object) {
		return ByteHelper.toBytes(object.getClass().getName());
	}

	/**
	 * Function that serializes a string
	 * @param s String to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [ID: 7][String Length][String]
	 */
	public static byte[] toBytes(String s) {
		return ByteHelper.mergeBytes(ByteHelper.toBytes((short) 7), ByteHelper.toBytesNoID(s));
	}
	
	/**
	 * Function that serializes a string without an ID
	 * @param s String to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [String Length][String]
	 */
	private static byte[] toBytesNoID(String s) {
		byte[] b = s.getBytes(StandardCharsets.UTF_8);
		return ByteHelper.mergeBytes(ByteHelper.toBytes(b.length), b);
	}
	
	/**
	 * Function that serializes a float
	 * @param f Float to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [ID: 8][Float]
	 */
	public static byte[] toBytes(float f) {
		return ByteBuffer.allocate(6).putShort((short) 8).putFloat(f).array();
	}
	
	/**
	 * Function that serializes a float without an ID
	 * @param f Float to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [Float]
	 */
	private static byte[] toBytesNoID(float f) {
		return ByteBuffer.allocate(4).putFloat(f).array();
	}
	
	/**
	 * Function that serializes an integer
	 * @param i Int to be serialized
	 * @return The int in byte form, the bytes are ordered as so:
	 * [ID: 9][Int]
	 */
	public static byte[] toBytes(int i) {
		return ByteBuffer.allocate(6).putShort((short) 9).putInt(i).array();
	}
	
	/**
	 * Function that serializes an integer without an ID
	 * @param i Int to be serialized
	 * @return The int in byte form, the bytes are ordered as so:
	 * [Int]
	 */
	private static byte[] toBytesNoID(int i) {
		return ByteBuffer.allocate(4).putInt(i).array();
	}
	
	/**
	 * Function that serializes a short
	 * @param s Short to be serialized
	 * @return The short in byte form, the bytes are ordered as so:
	 * [ID: 10][Short]
	 */
	public static byte[] toBytes(short s) {
		return ByteBuffer.allocate(4).putShort((short) 10).putShort(s).array();
	}
	
	/**
	 * Function that serializes a short without an ID
	 * @param s Short to be serialized
	 * @return The short in byte form, the bytes are ordered as so:
	 * [Short]
	 */
	private static byte[] toBytesNoID(short s) {
		return ByteBuffer.allocate(2).putShort(s).array();
	}

	/**
	 * Function that serializes a character
	 * @param c Int to be serialized
	 * @return The char in byte form, the bytes are ordered as so:
	 * [ID: 11][Char]
	 */
	public static byte[] toBytes(char c) {
		return ByteBuffer.allocate(4).putShort((short) 11).putChar(c).array();
	}
	
	/**
	 * Function that serializes a character without an ID
	 * @param c Int to be serialized
	 * @return The char in byte form, the bytes are ordered as so:
	 * [Char]
	 */
	private static byte[] toBytesNoID(char c) {
		return ByteBuffer.allocate(2).putChar(c).array();
	}
	
	/**
	 * Function that serializes a boolean
	 * @param b Bool to be serialized
	 * @return The char in byte form, the bytes are ordered as so:
	 * [ID: 12][Bool]
	 */
	public static byte[] toBytes(boolean b) {
		return ByteBuffer.allocate(3).putShort((short) 12).put((byte) (b ? 1 : 0)).array();
	}
	
	/**
	 * Function that serializes a character
	 * @param c Int to be serialized
	 * @return The char in byte form, the bytes are ordered as so:
	 * [Bool]
	 */
	private static byte toByteNoID(boolean b) {
		return (byte) (b ? 1 : 0);
	}
	
	private final ByteBuffer byteStream;
	private final HashMap<Class<?>, Class<?>> interfaces = new HashMap<>();
	private final HashMap<Class<?>, Class<?>> abstractClasses = new HashMap<>();
	
	public ByteHelper(byte[] byteStream) throws ClassNotFoundException {
		this.byteStream = ByteBuffer.wrap(byteStream);
		
		while (this.byteStream.hasRemaining()) {
			switch (this.byteStream.getShort(this.byteStream.position())) {
				case 0:  // Object array
					this.scanObject(Class.forName(this.readStringNoID()));
					for (int i = 0; i < this.byteStream.getInt(); i++) {
						this.byteStream.position(this.byteStream.position()+this.byteStream.getInt());
					} break;
				case 6:  // Object
					this.scanObject(Class.forName(this.readStringNoID()));
					int length = this.byteStream.getInt();
					this.byteStream.position(this.byteStream.position() + length);
					break;
				case 1:  // String array
					this.readStringArr(); break;
				case 2:  // Float array
					this.readFloatArr(); break;
				case 3:  // Int array
					this.readIntArr(); break;
				case 4:  // Char array
					this.readCharArr(); break;
				case 5:  // Bool array
					this.readBoolArr(); break;
				case 7:  // String
					this.readString(); break;
				case 8:  // Float
					this.readFloat(); break;
				case 9:  // Int
					this.readInt(); break;
				case 10:  // Short
					this.readShort(); break;
				case 11:  // Char
					this.readChar(); break;
				case 12:  // Bool
					this.readBool(); break;
				default:
					System.out.println(this.byteStream.getShort(this.byteStream.position()));
					throw new IllegalStateException("Unknown Internal ID Found");
			}
		} this.byteStream.position(0);
	}
	
	public void scanObject(Class<?> type) {
		Class<?>[] types = type.getInterfaces();
		for (int i = 0; i < types.length; i++) {this.interfaces.put(types[i], type);}
		Class<?> parent = type.getSuperclass();
		while (parent != null && !Modifier.isAbstract(parent.getModifiers())) {
			parent = parent.getSuperclass();
		} if (parent != null) {this.abstractClasses.put(parent, type);}
	}
	
	// [ID][Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
	// [ID][Object Type][Byte Length][Object]
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr() throws ReflectiveOperationException {
		this.readShort(); // Ignore ID
		Class<T> type = ((Class<T>) Class.forName(this.readString())); try {
			T prototype = type.getDeclaredConstructor().newInstance();
			T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(prototype.getClass(), this.byteStream.getInt()); 
			for (int i = 0; i < rtrn.length; i++) {
				rtrn[i] = prototype.deserialize(new ByteHelper(this.objHelper()));
			} return rtrn;
		} catch (NoSuchMethodException e) {
			throw new ReflectiveOperationException(
				"To use readObj() without parameters, please create an empty constructor for " 
				+ type.getName()
			);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T[] readObjArr(T prototype) throws ReflectiveOperationException {
		this.readShort(); // Ignore ID
		this.readString(); T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(prototype.getClass(), this.byteStream.getInt()); 
		for (int i = 0; i < rtrn.length; i++) {rtrn[i] = this.readObj(prototype);} return rtrn;
	}
	
	public String[] readStringArr() {
		this.readShort(); // Ignore ID
		String[] rtrn = new String[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readString();
		} return rtrn;
	}
	
	public float[] readFloatArr() {
		this.readShort(); // Ignore ID
		float[] rtrn = new float[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readFloat();
		} return rtrn;
	}
	
	public int[] readIntArr() {
		this.readShort(); // Ignore ID
		int[] rtrn = new int[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readInt();
		} return rtrn;
	}
	
	public short[] readShortArr() {
		this.readShort(); // Ignore ID
		short[] rtrn = new short[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readShort();
		} return rtrn;
	}
	
	public char[] readCharArr() {
		this.readShort(); // Ignore ID
		char[] rtrn = new char[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readChar();
		} return rtrn;
	}
	
	public boolean[] readBoolArr() {
		this.readShort(); // Ignore ID
		boolean[] rtrn = new boolean[this.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = this.readBool();
		} return rtrn;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Serializable<T>> T readObj() throws ReflectiveOperationException {
		this.readShort(); // Ignore ID
		Class<T> type = (Class<T>) Class.forName(this.readString()); 
		try {
			T prototype = type.getDeclaredConstructor().newInstance();
			byte[] bytes = new byte[this.byteStream.getInt()]; this.byteStream.get(bytes);
			return prototype.deserialize(new ByteHelper(bytes));
		} catch (NoSuchMethodException e) {return ByteHelper.classConstructor(type);}
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T classConstructor(Class<?> type) throws ReflectiveOperationException {
		Constructor<?>[] con = type.getConstructors(); 
		Arrays.sort(con, Comparator.comparingInt(Constructor::getParameterCount));
		for (int i = 0; i < con.length; i++) {
			if (con[i].getParameterCount() == 0) {return (T) con[i].newInstance();}
			Parameter[] prm = con[i].getParameters();
			Object[] obj = new Object[prm.length];
			try {
				for (int j = 0; j < obj.length; j++) {
					obj[j] = ByteHelper.recursiveClass(prm[j].getType());
				} return (T) con[i].newInstance(obj);
			} catch (ReflectiveOperationException e) {}
		} throw new ReflectiveOperationException(
			"To use readObj() without parameters, please create a public constructor for " 
			+ type.getName()
		);
	}
	
	private static Object recursiveClass(Class<?> type) throws ReflectiveOperationException {
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
	
	public <T extends Serializable<T>> T readObj(
		Class<T> type, Class<?>[] constructorParams,  Object...params) throws ReflectiveOperationException {
		this.readShort(); // Ignore ID
		this.readString(); return type.getDeclaredConstructor(constructorParams)
			.newInstance(params).deserialize(new ByteHelper(this.objHelper()));
	}
		
	public <T extends Serializable<T>> T readObj(T prototype) throws ReflectiveOperationException {
		this.readShort(); // Ignore ID
		this.readString(); T rtrn = prototype.deserialize(new ByteHelper(this.objHelper())); return rtrn;
	}
		
	private byte[] objHelper() {
		byte[] bytes = new byte[this.byteStream.getInt()];
		this.byteStream.get(bytes); return bytes;
	}
	
	public String readString() {
		this.readShort(); // Ignore ID
		byte[] str = new byte[this.byteStream.getInt()]; 
		this.byteStream.get(str);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	private String readStringNoID() {
		byte[] str = new byte[this.byteStream.getInt()]; 
		this.byteStream.get(str);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	public float readFloat() {
		this.readShort(); // Ignore ID
		return this.byteStream.getFloat();
	}
	
	public int readInt() {
		this.readShort(); // Ignore ID
		return this.byteStream.getInt();
	}
	
	private int readIntNoID() {
		return this.byteStream.getInt();
	}
	
	public short readShort() {
		this.byteStream.getShort(); // Ignore ID
		return this.byteStream.getShort();
	}
	
	public char readChar() {
		this.readShort(); // Ignore ID
		return this.byteStream.getChar();
	}
	
	public boolean readBool() {
		this.readShort(); // Ignore ID
		return this.byteStream.get() == 1;
	}

}
