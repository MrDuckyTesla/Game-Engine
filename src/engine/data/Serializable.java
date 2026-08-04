package engine.data;

public interface Serializable <T> {
	
	public byte[] serialize(T o);
	
	public T deserialize(byte[] bytes, Class<?> type);

}
