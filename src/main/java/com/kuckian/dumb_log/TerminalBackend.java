package com.kuckian.dumb_log;

public class TerminalBackend extends AbstractStringifyBackend implements BackendInterface {

	public TerminalBackend(StringifyConfiguration configuration) {
		super(configuration);
	}

	@Override
	public void write(Message message) {
		System.err.print(stringify(message));
	}

}
