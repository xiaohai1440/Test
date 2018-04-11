package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.Product;
import com.bean.UserAddress;
import com.dao.User_addressDao;
import com.dao.lmpl.User_addressDaoImpl;
import com.service.UserAddressService;

public class UserAddressServiceImpl implements UserAddressService {
	
	
	User_addressDao user=new User_addressDaoImpl();

	@Override
	public void add(UserAddress t) {
		// TODO Auto-generated method stub
		
		int add = user.add(t);

		if (add>0) {
			System.err.println("新增成功！");

		}else {
			System.err.println("新增失败！");
		}
		
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		
		int delete = user.delete(id);
		if (delete>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}
		
	}

	@Override
	public void updata(UserAddress t) {
		// TODO Auto-generated method stub
		
		int update = user.update(t);
		if (update>0) {
			System.err.println("更新成功！");

		}else {
			System.err.println("更新失败！");
		}
		
	}

	@Override
	public List<UserAddress> select() {
		// TODO Auto-generated method stub
		List<UserAddress> selectAll = user.selectAll();

		return selectAll;
	}

	@Override
	public UserAddress select_Id(Serializable id) {
		// TODO Auto-generated method stub
		UserAddress select_Id = user.select_Id(id);

		return select_Id;
	}

}
