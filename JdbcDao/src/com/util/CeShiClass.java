package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CeShiClass extends Eq {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private int ss;
	
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
//		CeShiClass newInstance = CeShiClass.class.newInstance();
		
		
		 Field[] fields = CeShiClass.class.getFields();
		 Field[] declaredFields = CeShiClass.class.getDeclaredFields();
		 
		 Method[] declaredMethods = CeShiClass.class.getDeclaredMethods();
		 Method[] methods = CeShiClass.class.getMethods();
		 
//		 Constructor<?>
		 
	/*	 
		 for (Method method : methods) {
			 
			 System.err.println(method.getName());
			 
			 
			
		}
		 
		 System.err.println("++++++++++++++++++++++++++++++++");
		 for (Method method : declaredMethods) {
			 
			 System.err.println(method.getName());
			 
			 
			
		}
		 */
		 
		 
 for (Field method : fields) {
			 
			 System.err.println(method.getName());
			 
			 
			
		}
		 
		 System.err.println("++++++++++++++++++++++++++++++++");
		 
		 
		 
		 for (Field method : declaredFields) {
			 
			 System.err.println(method.getName());
			 
			 
			
		}
		 
		 
		 
		 
		

	}

}
