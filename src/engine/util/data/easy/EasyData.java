package engine.util.data.easy;

import java.io.IOException;

import engine.util.data.Compression;
import engine.util.data.Encryption;
import engine.util.data.Storage;

public class EasyData<T extends EasySerializable<T>> {
	
	private final T object;
	
	private Storage storage = null;
	private Compression compression = null;
	private Encryption encryption = null;
	
	public EasyData(T object, Storage s, Compression c, Encryption e) {
		this.object = object; this.storage = s; 
		this.compression = c; this.encryption = e;
	}

	public EasyData(T object) {
		this.object = object;
	}
	
	public EasyData<T> setStorage(Storage s) {this.storage = s; return this;}
	public EasyData<T> setCompression(Compression s) {this.compression = s; return this;}
	public EasyData<T> setEncryption(Encryption s) {this.encryption = s; return this;}
	
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
		
		return this.object.deserialize(new EasyByteHelper(bytes));
	}
	
	public T get() {return this.object;}

}
