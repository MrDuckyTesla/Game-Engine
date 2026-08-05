package engine.util.data;

import java.io.IOException;

import engine.util.ByteHelper;

public class Data<T extends Serializable<T>> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	
	public Data(T object, Storage s, Compression c, Encryption e) {
		this.object = object; this.storage = s; 
		this.compression = c; this.encryption = e;
	}

	public Data(T object) {
		this.object = object;
	}
	
	public Data<T> setStorage(Storage s) {this.storage = s; return this;}
	public Data<T> setCompression(Compression s) {this.compression = s; return this;}
	public Data<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
	public void save() throws IOException {
		if (this.storage == null) {this.storage = new engine.util.data.storages.Local("data/unnamed.mdt");}
		if (this.compression == null) {this.compression = new engine.util.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.util.data.encryptions.Raw();}
		
		byte[] bytes = object.serialize();
		bytes = this.compression.compress(bytes);
		bytes = this.encryption.encrypt(bytes);
		this.storage.save(bytes);
	}
	
	public T load() throws IOException {
		if (this.storage == null) {this.storage = new engine.util.data.storages.Local("data/unnamed.mdt");}
		if (this.compression == null) {this.compression = new engine.util.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.util.data.encryptions.Raw();}
		
		byte[] bytes = this.storage.load();
		bytes = this.encryption.decrypt(bytes);
		bytes = this.compression.decompress(bytes);
		
		return this.object.deserialize(new ByteHelper(bytes));
	}
	
	public T get() {return this.object;}

}
