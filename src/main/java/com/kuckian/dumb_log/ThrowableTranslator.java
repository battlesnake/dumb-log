package com.kuckian.dumb_log;

import java.util.List;
import java.util.ListIterator;

import java.io.StringWriter;
import java.io.PrintWriter;

public class ThrowableTranslator implements MessageTranslator {

	public static String translate(Throwable t) {
		final StringWriter sw = new StringWriter();
		sw.write(t.getMessage());
		sw.write("\n");
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public void translate(List<Object> values) {
		for (ListIterator<Object> it = values.listIterator(); it.hasNext(); ) {
			Object o = it.next();
			if (o instanceof Throwable) {
				it.set(translate((Throwable) o));
			}
		}
	}

}
