package com.kuckian.dumb_log;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class Logger implements LoggerInterface {

	private final BackendInterface[] backends;

	public enum Level {
		FATAL, ERROR, WARNING, INFORMATION, DEBUG, VERBOSE
	};

	public Logger(BackendInterface... backends) {
		this.backends = backends;
	}

	public void write(final Message message) {
		for (final BackendInterface backend : backends) {
			backend.write(message);
		}
	}

	public void write(final Date date, final Level level, final String format, Object... values) {
		write(new Message(date, level, format, values));
	}

	@Override
	public void write(final Level level, final String format, Object... values) {
		write(new Date(), level, format, values);
	}

	@Override
	public void fatal(Exception err, String format, Object... values) {
		format += ":\n\t%s";
		List<Object> args = new LinkedList<Object>(Arrays.asList(values));
		args.add(err);
		write(Level.FATAL, format, args.toArray());
	}

	@Override
	public void error(Exception err, String format, Object... values) {
		format += ":\n\t%s";
		List<Object> args = new LinkedList<Object>(Arrays.asList(values));
		args.add(err);
		write(Level.ERROR, format, args.toArray());
	}

	@Override
	public void fatal(final String format, Object... values) {
		write(Level.FATAL, format, values);
	}

	@Override
	public void error(final String format, Object... values) {
		write(Level.ERROR, format, values);
	}

	@Override
	public void warning(final String format, Object... values) {
		write(Level.WARNING, format, values);
	}

	@Override
	public void info(final String format, Object... values) {
		write(Level.INFORMATION, format, values);
	}

	@Override
	public void debug(final String format, Object... values) {
		write(Level.DEBUG, format, values);
	}

	@Override
	public void verbose(final String format, Object... values) {
		write(Level.VERBOSE, format, values);
	}

}
