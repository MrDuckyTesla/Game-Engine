package engine.util.data;

public interface Serializable<T> {
	
	/**
	 * Serializes all fields of an object into bytes
	 * @return Byte representation of implementing class
	 */
	public abstract byte[] serialize();
	
	public abstract T deserialize(engine.util.ByteHelper b);
	
	public abstract T[] getProtoArray(int length);

}
