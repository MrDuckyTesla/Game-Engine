package engine.util;

import java.util.*;
import java.lang.reflect.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import engine.util.data.Serializable;

public class ByteHelper {
	
	private static ByteBuffer byteStream;
	private static Serializable<?>[] regisrty;
	
	private static void buildRegistry() {
		Class<?>[] implementations = Serializable.class.getInterfaces();
		Serializable.class.getClasses();
		ByteHelper.regisrty = new Serializable[implementations.length];
	}
	
	public static <T extends Serializable<T>> T load(byte[] byteStream) throws ClassNotFoundException {
		ByteHelper.byteStream = ByteBuffer.wrap(byteStream);
		while (ByteHelper.byteStream.hasRemaining()) {
			switch (ByteHelper.byteStream.getShort()) {
				case 0:  // Object array
//					ByteHelper.scanObject(Class.forName(ByteHelper.readStringNoID()));
					for (int i = 0; i < ByteHelper.byteStream.getInt(); i++) {
						ByteHelper.byteStream.position(ByteHelper.byteStream.position()+ByteHelper.byteStream.getInt());
					} break;
				case 6:  // Object
//					ByteHelper.scanObject(Class.forName(ByteHelper.readStringNoID()));
					ByteHelper.byteStream.position(ByteHelper.byteStream.position()+ByteHelper.byteStream.getInt());
					break;
				case 1:  // String array
					ByteHelper.readStringArrNoID(); break;
				case 2:  // Float array
					ByteHelper.readFloatArrNoID(); break;
				case 3:  // Int array
					ByteHelper.readIntArrNoID(); break;
				case 4:  // Char array
					ByteHelper.readCharArrNoID(); break;
				case 5:  // Bool array
					ByteHelper.readBoolArrNoID(); break;
				case 7:  // String
					ByteHelper.readStringNoID(); break;
				case 8:  // Float
					ByteHelper.readFloatNoID(); break;
				case 9:  // Int
					ByteHelper.readIntNoID(); break;
				case 10:  // Short
					ByteHelper.readShortNoID(); break;
				case 11:  // Char
					ByteHelper.readCharNoID(); break;
				case 12:  // Bool
					ByteHelper.readBoolNoID(); break;
				default:
					System.out.println(ByteHelper.byteStream.getShort(ByteHelper.byteStream.position()));
					throw new IllegalStateException("Unknown Internal ID Found");
			}
		} ByteHelper.byteStream.position(0);
		return null;
	}
	
//	private static void scanObject(Class<?> type) {
//		Class<?>[] types = type.getInterfaces();
//		for (int i = 0; i < types.length; i++) {ByteHelper.interfaces.put(types[i], type);}
//		Class<?> parent = type.getSuperclass();
//		while (parent != null && !Modifier.isAbstract(parent.getModifiers())) {
//			parent = parent.getSuperclass();
//		} if (parent != null) {ByteHelper.abstractClasses.put(parent, type);}
//	}
	
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
		return ByteHelper.toBytesNoID(object.getClass().getName());
	}

	/**
	 * Function that serializes a string
	 * @param s String to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [ID: 7][String Length][String]
	 */
	public static byte[] toBytes(String s) {
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID((short) 7), ByteHelper.toBytesNoID(s));
	}
	
	/**
	 * Function that serializes a string without an ID
	 * @param s String to be serialized
	 * @return The string in byte form, the bytes are ordered as so:
	 * [String Length][String]
	 */
	private static byte[] toBytesNoID(String s) {
		byte[] b = s.getBytes(StandardCharsets.UTF_8);
		return ByteHelper.mergeBytes(ByteHelper.toBytesNoID(b.length), b);
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
	 * Function that serializes a character without an ID
	 * @param b Bool to be serialized
	 * @return The bool in byte form, the bytes are ordered as so:
	 * [Bool]
	 */
	private static byte toByteNoID(boolean b) {
		return (byte) (b ? 1 : 0);
	}
	
//	// [ID][Object Type][Array Length][[Byte Length][Object], ...[Byte Length][Object]]
//	// [ID][Object Type][Byte Length][Object]
//	private <T extends Serializable<T>> T[] readObjArr() throws ReflectiveOperationException {
//		this.readShortNoID(); return this.readObjArrNoID();
//	}
	
//	@SuppressWarnings("unchecked")
//	private <T extends Serializable<T>> T[] readObjArrNoID() throws ReflectiveOperationException {
//		Class<T> type = ((Class<T>) Class.forName(this.readStringNoID())); try {
//			T prototype = type.getDeclaredConstructor().newInstance();
//			T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(prototype.getClass(), this.byteStream.getInt()); 
//			for (int i = 0; i < rtrn.length; i++) {
//				rtrn[i] = prototype.deserialize(new ByteHelper(this.objHelper()));
//			} return rtrn;
//		} catch (NoSuchMethodException e) {
//			throw new ReflectiveOperationException(
//				"To use readObj() without parameters, please create an empty constructor for " 
//				+ type.getName()
//			);
//		}
//	}
	
//	private <T extends Serializable<T>> T[] readObjArr(T prototype) throws ReflectiveOperationException {
//		this.readShortNoID(); return this.readObjArrNoID(prototype);
//	}
	
//	@SuppressWarnings("unchecked")
//	private <T extends Serializable<T>> T[] readObjArrNoID(T prototype) throws ReflectiveOperationException {
//		this.readStringNoID(); T[] rtrn = (T[]) java.lang.reflect.Array.newInstance(prototype.getClass(), this.byteStream.getInt()); 
//		for (int i = 0; i < rtrn.length; i++) {rtrn[i] = this.readObj(prototype);} return rtrn;
//	}
	
	private static String[] readStringArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readStringArrNoID();
	}
	
	private static String[] readStringArrNoID() {
		String[] rtrn = new String[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readStringNoID();
		} return rtrn;
	}
	
	private static float[] readFloatArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readFloatArrNoID();
	}
	
	private static float[] readFloatArrNoID() {
		float[] rtrn = new float[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readFloat();
		} return rtrn;
	}
	
	private static int[] readIntArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readIntArrNoID();
	}
	
	private static int[] readIntArrNoID() {
		int[] rtrn = new int[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readInt();
		} return rtrn;
	}
	
	private static short[] readShortArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readShortArrNoID();
	}
	
	private static short[] readShortArrNoID() {
		short[] rtrn = new short[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readShortNoID();
		} return rtrn;
	}
	
	private static char[] readCharArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readCharArrNoID();
	}
	
	private static char[] readCharArrNoID() {
		char[] rtrn = new char[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readChar();
		} return rtrn;
	}
	
	private static boolean[] readBoolArr() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.readBoolArrNoID();
	}
	
	private static boolean[] readBoolArrNoID() {
		boolean[] rtrn = new boolean[ByteHelper.byteStream.getInt()]; 
		for (int i = 0; i < rtrn.length; i++) {
			rtrn[i] = ByteHelper.readBool();
		} return rtrn;
	}
	
//	@SuppressWarnings("unchecked")
//	private <T extends Serializable<T>> T readObj() throws ReflectiveOperationException {
//		this.readShortNoID(); // Ignore ID
//		Class<T> type = (Class<T>) Class.forName(this.readStringNoID()); 
//		try {
//			T prototype = type.getDeclaredConstructor().newInstance();
//			byte[] bytes = new byte[this.byteStream.getInt()]; this.byteStream.get(bytes);
//			return prototype.deserialize(new ByteHelper(bytes));
//		} catch (NoSuchMethodException e) {return ByteHelper.classConstructor(type);}
//	}
	
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
	
//	private <T extends Serializable<T>> T readObj(
//		Class<T> type, Class<?>[] constructorParams,  Object...params) throws ReflectiveOperationException {
//		this.readShortNoID(); // Ignore ID
//		this.readStringNoID(); return type.getDeclaredConstructor(constructorParams)
//			.newInstance(params).deserialize(new ByteHelper(this.objHelper()));
//	}
		
//	private <T extends Serializable<T>> T readObj(T prototype) throws ReflectiveOperationException {
//		this.readShortNoID(); // Ignore ID
//		this.readStringNoID(); T rtrn = prototype.deserialize(new ByteHelper(this.objHelper())); return rtrn;
//	}
		
	private static byte[] objHelper() {
		byte[] bytes = new byte[ByteHelper.byteStream.getInt()];
		ByteHelper.byteStream.get(bytes); return bytes;
	}
	
	private static String readString() {
		ByteHelper.readShortNoID(); // Ignore ID
		byte[] str = new byte[ByteHelper.byteStream.getInt()]; 
		ByteHelper.byteStream.get(str);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	private static String readStringNoID() {
		byte[] str = new byte[ByteHelper.byteStream.getInt()]; 
		ByteHelper.byteStream.get(str);
		return new String(str, StandardCharsets.UTF_8);
	}
	
	private static float readFloat() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.byteStream.getFloat();
	}
	
	private static float readFloatNoID() {

		return ByteHelper.byteStream.getFloat();
	}
	
	private static int readInt() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.byteStream.getInt();
	}
	
	private static int readIntNoID() {
		return ByteHelper.byteStream.getInt();
	}
	
	private static short readShort() {
		ByteHelper.byteStream.getShort(); // Ignore ID
		return ByteHelper.byteStream.getShort();
	}
	
	private static short readShortNoID() {
		return ByteHelper.byteStream.getShort();
	}
	
	private static char readChar() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.byteStream.getChar();
	}
	
	private static char readCharNoID() {
		return ByteHelper.byteStream.getChar();
	}
	
	private static boolean readBool() {
		ByteHelper.readShortNoID(); // Ignore ID
		return ByteHelper.byteStream.get() == 1;
	}
	
	private static boolean readBoolNoID() {
		return ByteHelper.byteStream.get() == 1;
	}

}
