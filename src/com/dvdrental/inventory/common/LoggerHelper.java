package com.dvdrental.inventory.common;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	public static Logger log=null;
	public static ResourceBundle bundle=null;
	public static String loggerPropertiesFile=null;
	
	public static Logger intialize() {
		try {
	       bundle=ResourceBundle.getBundle("config");
	       loggerPropertiesFile=bundle.getString("Account.LogPath");
	       if(log==null && loggerPropertiesFile!=null) {
	    	   PropertyConfigurator.configure(loggerPropertiesFile);
	    	   log=Logger.getLogger("dvdrental_log");
	       }
			
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return log;
	}
	public static boolean isDebugEnabled() {
		if(log !=null) {
			return true;
		}else {
			return false;
		}
		
	}
	public static boolean isInfoEnabled() {
		if(log !=null) {
			return true;
		}else {
			return false;
		}
		
	}
	public static void logDebug(String className,String methodName,String message) {
		if(className==null) {
			className="";
		}
		if(methodName==null) {
			methodName="";
		}
		if(message==null) {
			message="";
		}
		if(log!=null) {
			log.debug(className+" [ "+methodName+" ] "+message);
		}else {
			intialize();
			log.debug(className+" [ "+methodName+" ] "+message);
		}
	}
	public static void logInfo(String className,String methodName,String message) {
		if(className==null) {
			className="";
		}
		if(methodName==null) {
			methodName="";
		}
		if(message==null) {
			message="";
		}
		if(log!=null) {
			log.info(className+" [ "+methodName+" ] "+message);
		}else {
			intialize();
			log.info(className+" [ "+methodName+" ] "+message);
		}
	}
	public static void logError(String className,String methodName,String message) {
		if(className==null) {
			className="";
		}
		if(methodName==null) {
			methodName="";
		}
		if(message==null) {
			message="";
		}
		if(log!=null) {
			log.error(className+" [ "+methodName+" ] "+message);
		}else {
			intialize();
			log.error(className+" [ "+methodName+" ] "+message);
		}
	}
	public static void logException(String className,String methodName,String message) {
		if(className==null) {
			className="";
		}
		if(methodName==null) {
			methodName="";
		}
		if(message==null) {
			message="";
		}
		if(log!=null) {
			log.error(className+" [ "+methodName+" ] "+message);
		}else {
			intialize();
			log.error(className+" [ "+methodName+" ] "+message);
		}
	}

}
