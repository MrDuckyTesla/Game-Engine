package engine.data.serializations;

import engine.data.Serialization;

public interface EasySerializable<T> extends Serialization<T> {
	
	public abstract byte[] serialize();
	
	public abstract T deserialize(engine.data.util.ByteHelper bytes);

}
