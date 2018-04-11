package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.News;
import com.bean.Order;
import com.dao.OrderDao;
import com.dao.lmpl.OrderDaolmpl;
import com.service.OrderDetailService;
import com.service.OrderService;

public class OrderServiceImpl implements OrderService {


	OrderDao dao=new OrderDaolmpl();

	@Override
	public void add(Order t) {
		// TODO Auto-generated method stub

		int add = dao.add(t);

		if (add>0) {
			System.err.println("新增成功！");

		}else {
			System.err.println("新增失败！");
		}

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

		int delete = dao.delete(id);
		if (delete>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}

	}

	@Override
	public void updata(Order t) {
		// TODO Auto-generated method stub
		int update = dao.update(t);
		if (update>0) {
			System.err.println("更新成功！");

		}else {
			System.err.println("更新失败！");
		}

	}

	@Override
	public List<Order> select() {
		// TODO Auto-generated method stub
		List<Order> selectAll = dao.selectAll();



		return selectAll;
	}

	@Override
	public Order select_Id(Serializable id) {
		// TODO Auto-generated method stub
		Order select_Id = dao.select_Id(id);


		return select_Id;
	}

}
