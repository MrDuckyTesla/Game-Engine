package engine.data.storages;

import java.io.IOException;
import java.nio.file.*;

import engine.data.Storage;

public class Local implements Storage {
	
	private final Path path;

	public Local(String fileLocation) {
		this.path =Paths.get(fileLocation);
	}

	@Override
	public void save(byte[] bytes) throws IOException {
		Files.write(this.path, bytes);
	}

	@Override
	public byte[] load() throws IOException {
		return Files.readAllBytes(this.path);
	}

}
