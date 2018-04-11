package com.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




/**
 * 
 * @author LSZ
 *
 * 非宁静无以致远！
 * 2018-4-11上午10:48:16    
 * 
 * 目的是通过jdbc连接，通过结果集ResultSet对象,获取到对应表的对应对象
 * 
 *
 */
public class resultSetUtil {

	/**
	 * 
	 * @param rs 结果集
	 * @param c	  运行时的类
	 * @return   返回一个查询结果,反射生成对象
	 */

	public static <T> T selectAlla(ResultSet rs,Class<T> c){

		T object=null;//存放运行时的对象
		try {
			//获得运行时的对象
			object=c.newInstance();
			if (rs.next()) {
				//获取到所有字段
				Field[] fields = c.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);//开启权限(可以为私有的变量直接赋值)
					//最终是通过setFF;
					//给字段赋值
					field.set(object, rs.getObject(field.getName()));						
				}	

			}



		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return object;

	}

	/**
	 * 
	 * @param rs 结果集
	 * @param c	  运行时的类
	 * @return   返回一个查询结果,反射生成对象
	 */
	public static <T> T selectAll(ResultSet rs,Class<T> c){

		T object=null;
		try {
			object=c.newInstance();
			if (rs.next()) {

				/*获取所有字段包括继承的，接口的*/
				Field[] fields = c.getDeclaredFields();
				/*获取public修饰的所有方法，c.getDeclaredMethods() 所有*/				
				Method[] methods = c.getMethods();

				for (Field field : fields) {//遍历匹配属性，赋值

					for (Method m:methods) {

						//获得declaredMethods数组里的，具体名字的方法
						String name=m.getName();

						//判断它是否是set方法，是返回true
						boolean startsWith = name.startsWith("set");
						if (startsWith) {

							//截取了set方法后面的属性名
							String num= name.substring(3);
							//大写改为小写的；  为了取得对应的属性
							num=num.substring(0, 1).toLowerCase()+num.substring(1);

							if (num.equals(field.getName())) {
								//PS:通过setter方法设置属性
								m.invoke(object, rs.getObject(field.getName().toString()) );

							}

						}

					}

				}	

			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;

	}


	/**
	 * 
	 * @param rs 结果集
	 * @param c	  运行时的类
	 * @return   返回一个查询结果,反射生成一个表的所有对象，元数据，一行数据
	 */
	public static <T> List<T> selectAlls(ResultSet rs,Class<T> c){

		T object=null;
		List<T> list=new ArrayList<T>();

		try {

			while(rs.next()) {
				object=c.newInstance();
				Field[] fields = c.getDeclaredFields();
				for (Field field : fields) {


					field.setAccessible(true);//开启权限
					//最终是通过set方法给字段赋值;
					//给字段赋值
					System.err.println(field.getName());
					field.set(object, rs.getObject(field.getName()));	
					System.err.println(field.getName());

				}	
				list.add(object);

			}



		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.err.println(list);
		return list;


	}

	/**	 
	 * @param rs 结果集
	 * @param c	  运行时的类
	 * @return   返回一个查询结果,反射生成一个表的所有对象，元数据，一行数据
	 */
	public static <T> List<T> selectAllsa(ResultSet rs,Class<T> c){

		T object=null;
		List<T> list=new ArrayList<T>();

		try {

			while(rs.next()) {


				/*每个实体类的对象*/
				object=c.newInstance();
				/*获取实体类的成员变量名*/
				Field[] fields = c.getDeclaredFields();
				/*获取目标类的所有public的方法*/
				Method[] declaredMethods = c.getMethods();
				/*获取目标类的所有包括继承的方法*/
				Method[] declaredMethod = c.getDeclaredMethods();


				for (Field field : fields) {


					for (Method m:declaredMethods) {

						//获得declaredMethods数组里的，具体名字的方法
						String name=m.getName();

						//判断它是否是set方法，是返回true
						boolean startsWith = name.startsWith("set");
						if (startsWith) {

							//截取了set方法后面的属性名
							String num= name.substring(3);
							//大写改为小写的；  为了取得对应的属性
							num=num.substring(0, 1).toLowerCase()+num.substring(1);
							//获得方法所对应的属性,成员属性,
							if (num.equals(field.getName())) {
								//通过set方法设置属性
								m.invoke(object, rs.getObject(field.getName().toString()) );

							}




						}


					}


				}	

				list.add(object);

			}



		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;


	}








}
