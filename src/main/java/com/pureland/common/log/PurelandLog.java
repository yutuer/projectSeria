package com.pureland.common.log;

import org.apache.log4j.Logger;

/*
 * Created by qinpeirong on 14-10-31
 */
public class PurelandLog {
	
	private static final Logger infoLogger  = Logger.getLogger("IN");
	private static final Logger debugLogger = Logger.getLogger("DE");
	private static final Logger errorLogger = Logger.getLogger("ER");
	private static final Logger fatalLogger = Logger.getLogger("FA");
	private static final Logger warnLogger  = Logger.getLogger("WA");
	private static final Logger reqLogger   = Logger.getLogger("Req");

	public PurelandLog() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getClassTag(Class<?> clazz) {
		return clazz.getSimpleName();
	}

	public static void debug(String tag, String msg) {
		debugLogger.debug(tag + " " + msg);
	}

	public static void debug(String msg) {
		debug("", msg);
	}

	public static void info(String tag, String msg) {
		infoLogger.info(tag + " " + msg);
	}
	
	public static void info(String msg) {
		info("", msg);
	}

	public static void error(String tag, String msg) {
		errorLogger.error(tag + " " + msg);
	}
	public static void error(String msg) {
		error("", msg);
	}

	public static void fatal(String tag, String msg) {
		fatalLogger.error(tag + " " + msg);
	}

	public static void fatal(String msg) {
		fatal("", msg);
	}

	public static void warn(String tag, String msg) {
		warnLogger.warn(tag + " " + msg);
	}
	
	public static void warn(String msg) {
		warn("", msg);
	}
	

	
}
