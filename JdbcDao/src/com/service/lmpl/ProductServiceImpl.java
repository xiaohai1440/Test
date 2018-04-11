package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.Product;
import com.dao.ProductDao;
import com.dao.lmpl.ProductDaolmpl;
import com.service.ProductService;

public class ProductServiceImpl implements ProductService {


	ProductDao productDao=new ProductDaolmpl();
	@Override
	public void add(Product t) {
		int add = productDao.add(t);

		if (add>0) {
			System.err.println("新增成功！");

		}else {
			System.err.println("新增失败！");
		}

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		int delete = productDao.delete(id);
		if (delete>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}

	}

	@Override
	public void updata(Product t) {
		// TODO Auto-generated method stub
		int update = productDao.update(t);
		if (update>0) {
			System.err.println("更新成功！");

		}else {
			System.err.println("更新失败！");
		}


	}

	@Override
	public List<Product> select() {
		// TODO Auto-generated method stub
		List<Product> selectAll = productDao.selectAll();

		return selectAll;
	}

	@Override
	public Product select_Id(Serializable id) {
		// TODO Auto-generated method stub
		Product select_Id = productDao.select_Id(id);

		return select_Id;
	}

}
