package com.service;

import java.io.Serializable;
import java.util.List;

import com.bean.User;

public interface UserService extends BaseService<User> {
	
	
	User login(String userName,String passWord);

}
