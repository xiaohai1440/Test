package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.News;
import com.bean.User;
import com.dao.UserDao;
import com.service.UserService;

public class UserServiceImpl implements UserService{
UserDao userDao=new com.dao.lmpl.UserDaoImpl();

@Override
public void add(User t) {
	// TODO Auto-generated method stub
	int add = userDao.add(t);
	
	if (add>0) {
		System.err.println("新增成功！");
		
	}else {
		System.err.println("新增失败！");
	}
	
	
	
	
}

@Override
public void delete(Serializable t) {
	// TODO Auto-generated method stub
	int delete = userDao.delete(t);
	if (delete>0) {
		System.err.println("更新成功！");
		
	}else {
		System.err.println("更新失败！");
	}
	
}

@Override
public void updata(User t) {
	// TODO Auto-generated method stub
	
	int update = userDao.update(t);
	if (update>0) {
		System.err.println("删除成功！");
		
	}else {
		System.err.println("删除失败！");
	}
	
	
}

@Override
public List<User> select() {
	// TODO Auto-generated method stub
	List<User> selectAll = userDao.selectAll();
	
	
	
	return selectAll;
}

@Override
public User select_Id(Serializable id) {
	// TODO Auto-generated method stub
	User select_Id = userDao.select_Id(id);
	
	return select_Id;
}

@Override
public User login(String userName, String passWord) {
	// TODO Auto-generated method stub
	User login = userDao.login(userName, passWord);
	return login;
}


	
}
