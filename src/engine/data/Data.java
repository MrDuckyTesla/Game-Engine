package engine.data;

import java.io.IOException;

public class Data<T extends Serializable<?>> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	
	public Data(T object, Storage s, Compression c, Encryption e) {
		this.object = object;
	}

	public Data(T object) {
		this.object = object;
	}
	
	public Data() throws IOException {
		this.object = this.load();
	}
	
	public Data<T> setStorage(Storage s) {this.storage = s; return this;}
	public Data<T> setCompression(Compression s) {this.compression = s; return this;}
	public Data<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
	public void save() throws IOException {
		if (this.storage == null) {this.storage = new engine.data.storages.Local("data/test.txt");}
		if (this.compression == null) {this.compression = new engine.data.compressions.Raw();}
		if (this.encryption == null) {this.encryption = new engine.data.encryptions.Raw();}
		
		byte[] bytes = object.serialize();
		bytes = this.compression.compress(bytes);
		bytes = this.encryption.encrypt(bytes);
		this.storage.save(bytes);
	}
	
	public T load() throws IOException {
		return (T) this.object.deserialize(new ByteHelper(this.storage.load()));
	}

}
