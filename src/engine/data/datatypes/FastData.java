package engine.data.datatypes;

import java.io.IOException;

import engine.data.Compression;
import engine.data.Data;
import engine.data.Encryption;
import engine.data.Storage;
import engine.data.serializations.FastSerializable;
import engine.data.util.ByteHelper;

public class FastData<T extends FastSerializable<T>> implements Data<T> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	
	public FastData(T object, Storage s, Compression c, Encryption e) {
		this.object = object; this.storage = s; 
		this.compression = c; this.encryption = e;
	}

	public FastData(T object) {
		this.object = object;
	}
	
	public FastData<T> setStorage(Storage s) {this.storage = s; return this;}
	public FastData<T> setCompression(Compression s) {this.compression = s; return this;}
	public FastData<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
	public void save() throws IOException {
		this.checkNullExists();
		byte[] bytes = this.object.serialize();
		bytes = this.compression.compress(bytes);
		bytes = this.encryption.encrypt(bytes);
		this.storage.save(bytes);
	}
	
	public T load(FastSerializable<?>... prototypes) throws IOException {
		this.checkNullExists();
		byte[] bytes = this.storage.load();
		bytes = this.encryption.decrypt(bytes);
		bytes = this.compression.decompress(bytes);
		return this.object.deserialize(new ByteHelper(bytes), prototypes);
	}
	
	public T get() {return this.object;}
	
	private void checkNullExists() {
		if (this.storage == null) {this.storage = new engine.data.storages.Local("data/unnamed.mdt");}
		if (this.compression == null) {this.compression = new engine.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.data.encryptions.Raw();}
	}


}
