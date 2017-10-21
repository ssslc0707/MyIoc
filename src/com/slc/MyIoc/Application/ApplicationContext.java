package com.slc.MyIoc.Application;

import java.io.File;
import java.net.URL;

import javax.xml.ws.soap.Addressing;

import com.slc.MyIoc.Application.BeanFactory.BeanFactory;
import com.slc.MyIoc.Utils.StringUtils;
public class ApplicationContext {

	
	public static String path;
	public static String pageName;
	public ApplicationContext() {
		
	}
	private Class clazz;
	
	private BeanFactory beanFactory = BeanFactory.getBeanFactory();

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
	public <T> T getBean(Class<T> class1){
		return beanFactory.getBean(class1);
	}
	public <T> T getBean(String className){
		return beanFactory.getBean(className);
	}
	public ApplicationContext run(Class clazz){
		
		this.clazz = clazz;
		URL url = clazz.getClassLoader().getResource("");
		path = StringUtils.getPath(url.toString());
		pageName = StringUtils.getPageName(clazz.getPackage().toString());
		beanFactory.registerBean(new File(path));
		
		return this;
	}
}
