package com.kuckian.dumb_log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileBackend extends AbstractStringifyBackend implements BackendInterface {

	private final File file;
	private final FileOutputStream stream;

	public FileBackend(StringifyConfiguration configuration, String filename, boolean append) throws IOException {
		super(configuration);
		file = new File(filename);
		stream = new FileOutputStream(file, append);
		if (!file.exists()) {
			file.createNewFile();
		}

	}

	public void close() {
		try {
			stream.close();
		} catch (IOException e) {
			System.err.println("Failed to close log file <" + file.getAbsolutePath() + ">: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void write(Message message) {
		try {
			stream.write(stringify(message).getBytes());
		} catch (IOException e) {
			System.err.println("Failed to write to log file <" + file.getAbsolutePath() + ">: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
