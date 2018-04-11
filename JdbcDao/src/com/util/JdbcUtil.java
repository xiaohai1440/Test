package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author LSZ
 *  数据库连接与关闭工具类
 *
 */
public class JdbcUtil {

	//protected 受保护的继承的可以用
	protected static Connection conn=null;
	protected static PreparedStatement pre=null;
	protected static ResultSet result=null;

	/**
	 * 
	 * @return
	 * 获取连接数据库对象 Connection 对象
	 */
	public static  boolean getConnection(){

		try {

			//加载驱动
			Class.forName(ConfigManager.getInstance().getValue("jdbc.driver"));
			//获取连接数据库对象
			conn = DriverManager.getConnection(ConfigManager.getInstance().getValue("jdbc.url"), 
					ConfigManager.getInstance().getValue("jdbc.userName"),
					ConfigManager.getInstance().getValue("jdbc.passWord"));


		} 	catch (ClassNotFoundException e) {		
			e.printStackTrace();	
			return false;

		}  catch (SQLException e){			
			e.printStackTrace();


		}
		return true;	

	}

	/**
	 * 统一关闭资源
	 */
	public static void close_(){
		
		//若对象不为null
				try {
					if (pre!=null) {
						pre.close();
					}
					if (result!=null) {
						result.close();
					}
					if (conn!=null) {
						conn.close();

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		
	}
	
	

	/**
	 * 关闭数据库连接
	 */

 	public static void closeAss(Connection conn,Statement state,ResultSet rs){

		//若对象不为null
		try {
			if (rs!=null) {
				rs.close();
			}
			if (state!=null) {
				state.close();
			}
			if (conn!=null) {
				conn.close();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}


	/**
	 * 增,删,改的操作
	 */
	public static int exceuteUpdate(String preparedsql,Object...param){

		int num=0;

		System.err.println(preparedsql);
		if (getConnection()) {//保证有连接
			try {
				//获取发送SQL语句到数据库的
				pre=conn.prepareStatement(preparedsql);


				for (int i = 0; i < param.length; i++) {//对应的参数
					pre.setObject(i+1, param[i]);//为SQL语句，补充？号的参数					
					//为预编译SQL设置参数
					//执行上面传过去参数的SQL语句，并返回操作行数
				}
				num=pre.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{

				closeAss(conn, pre, null);
			}

		}
		return num;	
	}



	/**
	 * 查询方法，返回结果集
	 * @throws SQLException 
	 */
	public static ResultSet exceuteQuery(String preparedsql,Object...param) throws SQLException{

		if (getConnection()) {

			//try {
				//获取发送SQL语句到数据库的
				pre=conn.prepareStatement(preparedsql);

				for (int i = 0; i < param.length; i++) {//对应的参数
					pre.setObject(i+1, param[i]);//为SQL语句，补充？号的参数

				}
				//为预编译SQL设置参数
				//执行上面传过去参数的SQL语句，并返回操作行数					
				result=pre.executeQuery();


//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		//这里不能关闭资源，因为还要连接
		return result;

	}

}
