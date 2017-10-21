package com.slc.MyIoc.Application.BeanFactory;

import java.lang.annotation.Annotation;

public class AnnotationUtils {

	public static boolean isIocBean(Annotation annotation) {
		return annotation.annotationType().getName().contentEquals("com.slc.MyIoc.Application.anno.Ioc");
	}

	public static boolean isAutoWried(Annotation[] annotations) {
		for (Annotation annotation : annotations) {
			annotation.annotationType().getName().contentEquals("com.slc.MyIoc.Application.anno.MyAuto");
			return true; 
		}
		return false;
	}

}
