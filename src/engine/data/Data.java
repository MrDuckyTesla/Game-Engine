package engine.data;

import java.io.IOException;

import engine.data.serializations.FastSerializable;

public interface Data<T extends Serialization<T> > {
	
	public abstract void save() throws IOException;
	
	public T load(FastSerializable<?>... prototypes) throws IOException;
}
