package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.OrderDetail;
import com.dao.OrderDatailDao;
import com.dao.lmpl.OrderDatailDaolmpl;
import com.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{

	OrderDatailDao OrderDetail=new OrderDatailDaolmpl();

	@Override
	public void add(OrderDetail t) {
		// TODO Auto-generated method stub
		int add = OrderDetail.add(t);

		if (add>0) {
			System.err.println("新增成功！");

		}else {
			System.err.println("新增失败！");
		}

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		int delete = OrderDetail.delete(id);
		if (delete>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}


	}

	@Override
	public void updata(OrderDetail t) {
		// TODO Auto-generated method stub
		int update = OrderDetail.update(t);
		if (update>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}


	}

	@Override
	public List<OrderDetail> select() {
		// TODO Auto-generated method stub
		List<OrderDetail> selectAll = OrderDetail.selectAll();



		return selectAll;
	}

	@Override
	public OrderDetail select_Id(Serializable id) {
		// TODO Auto-generated method stub
		OrderDetail select_Id = OrderDetail.select_Id(id);


		return select_Id;
	}









}
