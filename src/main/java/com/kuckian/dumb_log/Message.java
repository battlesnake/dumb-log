package com.kuckian.dumb_log;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import com.kuckian.dumb_log.Logger.Level;

public class Message {

	public final Date date;
	public final Logger.Level level;
	public final String data;

	public static final List<MessageTranslator> translators = Arrays.asList(new ThrowableTranslator());

	public static void Translate(List<Object> values) {
		for (MessageTranslator translator : translators) {
			translator.translate(values);
		}
	}

	public Message(Date date, Level level, String format, Object... values) {
		List<Object> vals = Arrays.asList(values);
		Translate(vals);
		this.date = date;
		this.level = level;
		this.data = String.format(format, vals.toArray());
	}

}
