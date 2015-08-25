package net.zmobile.util;

import java.util.Date;

public class Logger {
	public static final int LOG_INFO = 0;
	public static final int LOG_WARN = 1;
	public static final int LOG_ERROR = 2;
	
	static int logLevel = LOG_INFO;
	public static void log(int level, String logContent) {
		if (logLevel > level) {
			return;
		}
		switch (logLevel) {
		case LOG_INFO:
			System.out.print("INFO--");
			break;
		case LOG_WARN:
			System.out.print("WARN--");
			break;
		case LOG_ERROR:
			System.out.print("ERROR--");
			break;
		}
		System.out.print(new Date().toString()+"--");
		System.out.println(logContent);
	}
}
