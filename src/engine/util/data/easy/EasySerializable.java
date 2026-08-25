package engine.util.data.easy;

public interface EasySerializable<T> {
	
	public abstract byte[] serialize();
	
	public abstract T deserialize(EasyByteHelper bytes);

}
