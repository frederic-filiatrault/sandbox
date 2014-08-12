package com.test.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;

public class CustomJPALogger extends AbstractSessionLog implements SessionLog {

	private static final Logger LOGGER = LogManager.getLogger(CustomJPALogger.class);

	@Override
	public void log(SessionLogEntry entry) {
		int level = entry.getLevel();
		String message = entry.getMessage();
		if (entry.getParameters() != null) {
			message += " [";
			int index = 0;
			for (Object object : entry.getParameters()) {
				message += (index++ > 0 ? "," : "") + object;
			}
			message += "]";
		}
		switch (level) {
		case SessionLog.SEVERE:
			LOGGER.error(message);
			break;
		case SessionLog.WARNING:
			LOGGER.warn(message);
			break;
		case SessionLog.INFO:
			LOGGER.info(message);
			break;
		case SessionLog.CONFIG:
			LOGGER.trace(message);
			break;
		default:
			LOGGER.debug(message);
			break;
		}
	}
}