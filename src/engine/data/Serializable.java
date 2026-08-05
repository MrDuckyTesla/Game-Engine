package engine.data;

public interface Serializable<T> {
	
	public abstract byte[] serialize();
	
	public abstract T deserialize(ByteHelper bytes);

}
