package com.kuckian.dumb_log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.UUID;

public class FilesBackend implements BackendInterface {

	private final File directory;
	private final String filenameFormat;
	private final String dataFormat;

	private long serial = 0;

	public FilesBackend(String directory, String filenameFormat, String dataFormat) {
		this.directory = new File(directory);
		this.filenameFormat = filenameFormat;
		this.dataFormat = dataFormat;
		if (!this.directory.mkdirs() && !this.directory.isDirectory()) {
			throw new IOError(new Exception(String.format("Failed to create log directory \"%s\"", directory)));
		}
	}

	private String substitute(String format, long serial, UUID uuid, Message message) {
		final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String dateString = df.format(message.date);
		return format.replaceAll("\\$\\{serial}", String.format("%08x", serial)).replaceAll("\\$\\{uuid}", uuid.toString())
				.replaceAll("\\$\\{date}", dateString).replaceAll("\\$\\{level}", message.level.toString())
				.replaceAll("\\$\\{data}", message.data);
	}

	private void writeFile(File file, byte[] data) throws IOException {
		final FileOutputStream stream = new FileOutputStream(file);
		try {
			stream.write(data);
		} finally {
			stream.close();
		}
	}

	@Override
	public void write(Message message) {
		final long id = serial++;
		final UUID uuid = UUID.randomUUID();
		final String filename = substitute(filenameFormat, id, uuid, message);
		final String data = substitute(dataFormat, id, uuid, message);
		final File file = new File(directory.getAbsolutePath(), filename);
		try {
			writeFile(file, data.getBytes());
		} catch (IOException e) {
			System.err.println("Failed to write to log entry file <" + file.getAbsolutePath() + ">: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
