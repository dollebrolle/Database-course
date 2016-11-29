package se.plushogskolan.taskhandler.logger;

public class LoggerImpl implements Logger {

	@Override
	public void info(String message) {
		System.err.println(message);		
	}

	@Override
	public void debug(String message) {
		System.err.println(message);
	}

	@Override
	public void warn(String message) {
		System.err.println(message);
	}

	@Override
	public void error(String message) {
		System.err.println(message);
	}
	
}