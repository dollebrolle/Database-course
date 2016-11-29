package se.plushogskolan.taskhandler.logger;

public interface Logger {
		
	void info(String message);
	void debug(String message);
	void warn(String message);
	void error(String message);

}