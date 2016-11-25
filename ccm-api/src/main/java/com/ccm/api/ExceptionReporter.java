package com.ccm.api;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class ExceptionReporter {

	public static void reportException(Throwable t, String key) {
		String className = t.getStackTrace()[1].getClassName();
		if (AppConfig.isEanble(className + ".report.exception") || AppConfig.isEanble(key)) {
			//TODO:send exception email to ccm OPS
		}
	}
	
	public static void reportException(Throwable t) {
        t.printStackTrace();
    }
	
	 public static String printStackTrace(Throwable t) { 
		 StringWriter sw = new StringWriter();
		 t.printStackTrace(new PrintWriter(sw));
		 return sw.toString();
	  }
}
