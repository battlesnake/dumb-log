package com.kuckian.dumb_log;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.TimeZone;

public abstract class AbstractStringifyBackend implements BackendInterface {

	private final boolean showDate;
	private final String fieldDelimiter;
	private final String messageTerminator;

	protected AbstractStringifyBackend(StringifyConfiguration configuration) {
		showDate = configuration.getShowDate();
		fieldDelimiter = configuration.getFieldDelimiter();
		messageTerminator = configuration.getMessageTerminator();
	}

	protected String stringify(Message message) {
		final LinkedList<String> fields = new LinkedList<String>();
		if (showDate) {
			final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
			fields.add(df.format(message.date));
		}
		fields.add(message.level.toString().toUpperCase());
		fields.add(message.data);
		return String.join(fieldDelimiter, fields) + messageTerminator;
	}

	@Override
	public abstract void write(Message message);

}
