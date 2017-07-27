package com.kuckian.dumb_log;

import com.kuckian.dumb_log.Logger.Level;

public interface LoggerInterface {

	public void write(Level level, String format, Object... values);

	public void fatal(String format, Object... values);

	public void error(String format, Object... values);

	public void warning(String format, Object... values);

	public void info(String format, Object... values);

	public void debug(String format, Object... values);

	public void verbose(String format, Object... values);

}
