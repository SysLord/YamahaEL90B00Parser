package parser.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {

	private static final Logger logger = LogManager.getLogger("Log");

	public static void log(String format, Object... arg) {
		logger.log(Level.INFO, String.format(format, arg));
	}

}
