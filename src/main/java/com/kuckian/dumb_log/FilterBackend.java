package com.kuckian.dumb_log;

import java.util.function.Predicate;

public class FilterBackend implements BackendInterface {

	private final BackendInterface backend;
	private final Predicate<? super Message> predicate;

	public FilterBackend(BackendInterface backend, Predicate<? super Message> predicate) {
		this.backend = backend;
		this.predicate = predicate;
	}

	@Override
	public void write(Message message) {
		if (predicate.test(message)) {
			backend.write(message);
		}
	}

}
