package engine.util.data;

import engine.util.ByteHelper;

public interface Serializable<T> {
	
	public abstract byte[] serialize();
	
	public abstract T deserialize(ByteHelper bytes) throws ReflectiveOperationException;

}
