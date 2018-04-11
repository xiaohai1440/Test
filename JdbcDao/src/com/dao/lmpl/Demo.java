package com.dao.lmpl;

import java.util.List;

import com.bean.News;
import com.bean.User;

public class Demo {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*UserDaoImpl u=new UserDaoImpl();
		
		
		List<User> selectAll = u.selectAll();		
		for (User user : selectAll) {			
			System.err.println(user);			
		}
		*/
		
		
		/*登陆的方法*/
		/*User login = u.login("111882@qq.com","1111");				
		System.err.println(login);*/
		
		/*删除方法*/
	/*	int delete = u.delete(100);
		if (delete>0) {
			System.err.println("删除成功");
		}*/
		
		
		
		/*增加用户*/

//		User user =new User(101, "xiaohai", "xiao", "1111",1, "440923188888", "111882@qq.com","135066532", 1);	
//		int add = u.add(user);
//		System.err.println(add);
		
		
		
		
		/*修改*/
//		User user =new User(100, "xiaohai", "xiao", "1111",1, "440923188888",
//				"111882@qq.com","135066532", 1);
//		
//		int update = u.update(user);
//		if (update>0) {
//			System.err.println("修改成功");
//		}
//		
	
		
		
		NewsDaolmpl news=new NewsDaolmpl();
		
		
		List<News> se = news.selectAll();		
		for (News user : se) {			
			System.err.println(user);			
		}
		
		
		
		
		
		
		
		
	}

}
