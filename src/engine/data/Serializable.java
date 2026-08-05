package engine.data;

public interface Serializable<T> {
	
	public byte[] serialize();
	
	public T deserialize(ByteHelper bytes);

}
