package com.dao;

import com.bean.User;

public interface UserDao extends BaseDao<User> {
	
	User login(String userName,String passWord);
	
}
