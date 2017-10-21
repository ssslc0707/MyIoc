package com.slc.MyIoc.Utils;


public final class StringUtils {

	
	
	public static String getPath(String string) {
		
		int i = string.indexOf("/");
		
		String path = string.substring(i+1);
		
		return path;
	}

	public static String getPageName(String string) {
		String pageName = string.substring(string.indexOf(" ")+1).replace(".", "/");
		
		return pageName;
	}

	public static String getClassName(Class<?> clazz) {
		return clazz.getName();
		
	}

	
	public static void main(String[] args) {
		System.out.println(StringUtils.class.getName());
		System.out.println(getClassName(StringUtils.class));
	}
}
