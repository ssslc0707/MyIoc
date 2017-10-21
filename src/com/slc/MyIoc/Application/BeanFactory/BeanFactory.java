package com.slc.MyIoc.Application.BeanFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.login.FailedLoginException;
import javax.xml.ws.soap.Addressing;

import com.slc.MyIoc.Application.ApplicationContext;
import com.slc.MyIoc.Application.exception.DiException;
import com.slc.MyIoc.Utils.StringUtils;

public class BeanFactory {

	//单列
	private static BeanFactory beanFactory = new BeanFactory();
	
	private BeanFactory(){
		
	}
	
	//用于存放className和Bean的对应关系map
	private static Map<String,Object> map = new ConcurrentHashMap<>();
	//用于存放类型和className集合的对应关系map
	private static Map<Class<?>,ArrayList<String>> typeOfBeanName = new ConcurrentHashMap<>();
	/**
	 * 通过基本包文件注册Bean
	 * @param file 基本包文件
	 */
	public static void registerBean(File file){
		
		File[] file_list = file.listFiles();
		if(file_list.length == 0 )return;
		for (File f : file_list) {
			if(f.isDirectory()){
				if(f.listFiles().length == 0){
					return;
				}
				registerBean(f);
			}
			if(f.getName().endsWith(".class")){
				String classname = f.getPath().substring(ApplicationContext.path.length()).replace("\\", ".").replace(".class","");
				try {
					Class<?> clazz = Class.forName(classname);
					Annotation[] annotations = clazz.getAnnotations();
					for (Annotation annotation : annotations) {
						if(AnnotationUtils.isIocBean(annotation)){
							try {
								Object object = clazz.newInstance();
								map.put(classname, object);
								addToTypeOfBeanName(clazz,classname);
							} catch (InstantiationException | IllegalAccessException e) {
								e.printStackTrace();
							}
						}
					}
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		initBean();
	}
	/**
	 * 类型类名关系map添加元素
	 * @param clazz 类型
	 * @param className 类名
 	 */
	private static void addToTypeOfBeanName(Class<?> clazz,String className) {
		ArrayList<String> list = new ArrayList<>();
		list.add(className);
		typeOfBeanName.put(clazz,list);
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> class1 : interfaces) {
			ArrayList<String> strings = typeOfBeanName.get(class1);
			if(strings != null){
				strings.add(className);
			}else{
				typeOfBeanName.put(class1,list);
			}
		}
	}
	/**
	 * 初始化bean
	 */
	private static void initBean() {
		for(Entry entry:map.entrySet()){
			Class<? extends Object> class1 = entry.getValue().getClass();
			Field[] fields = class1.getDeclaredFields();
			
			for (int i = 0; i < fields.length; i++) {
				if(AnnotationUtils.isAutoWried(fields[i].getAnnotations()))
				di(fields[i],entry.getValue());
			}
		}
	}
	/**
	 * di
	 * @param field 需要注入的变量
	 * @param object 操作的bean
	 * @return
	 */
	private static boolean di(Field field,Object object) {
		Class<?> type = field.getType();
		
		ArrayList<String> list = typeOfBeanName.get(type);
		
		if(list == null || list.size() == 0){
			throw new RuntimeException("缺少此Bean: "+field.getType()+" 注入失败");
		}
		if(list.size() > 1){
			String str ="";
			for (String string : list) {
				str += "["+string+"]";
			}
			throw new DiException(str);
		}
		String className = list.get(0);
		
		Object object2 = map.get(className);
		
		field.setAccessible(true);
		try {
			field.set(object, object2);
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public <T> T getBean(Class<T> class1){
		
		ArrayList<String> arrayList = typeOfBeanName.get(class1);
		
		if(arrayList.size() == 0)
		throw new RuntimeException("无此类");
		if(arrayList.size() > 1)
		throw new RuntimeException("有多个此类");
		String className = arrayList.get(0);
		return (T) map.get(className);
	}
	public static BeanFactory getBeanFactory(){
		return beanFactory;
	}

	public <T> T getBean(String className) {
		return (T) map.get(className);
		
	}
}
