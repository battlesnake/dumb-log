package com.kuckian.dumb_log;

import java.util.Date;

import com.kuckian.dumb_log.Logger.Level;

public class Message {

	public final Date date;
	public final Logger.Level level;
	public final String data;

	public Message(Date date, Level level, String format, Object... values) {
		this.date = date;
		this.level = level;
		this.data = String.format(format, values);
	}

}