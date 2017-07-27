package com.kuckian.dumb_log;

public class StringifyConfiguration {

	private boolean showDate = true;
	private String fieldDelimiter = "\t";
	private String messageTerminator = "\n\n";

	public StringifyConfiguration() {

	}

	public boolean getShowDate() {
		return showDate;
	}

	public StringifyConfiguration setShowDate(boolean showDate) {
		this.showDate = showDate;
		return this;
	}

	public String getFieldDelimiter() {
		return fieldDelimiter;
	}

	public StringifyConfiguration setFieldDelimiter(String fieldDelimiter) {
		this.fieldDelimiter = fieldDelimiter;
		return this;
	}

	public String getMessageTerminator() {
		return messageTerminator;
	}

	public StringifyConfiguration setMessageTerminator(String messageTerminator) {
		this.messageTerminator = messageTerminator;
		return this;
	}

}
