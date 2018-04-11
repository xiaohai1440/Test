package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import com.bean.Order;
import com.bean.ProductCategory;
import com.dao.Product_CategoryDao;
import com.dao.lmpl.Product_CategoryDaolmpl;
import com.service.ProductCategoryService;

public class ProductCategoryServiceImpl implements ProductCategoryService {

	Product_CategoryDao pro=new Product_CategoryDaolmpl();

	@Override
	public void add(ProductCategory t) {
		// TODO Auto-generated method stub

		int add = pro.add(t);

		if (add>0) {
			System.err.println("新增成功！");

		}else {
			System.err.println("新增失败！");
		}

	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub

		int delete = pro.delete(id);
		if (delete>0) {
			System.err.println("删除成功！");

		}else {
			System.err.println("删除失败！");
		}

	}

	@Override
	public void updata(ProductCategory t) {
		// TODO Auto-generated method stub

		int update = pro.update(t);
		if (update>0) {
			System.err.println("更新成功！");

		}else {
			System.err.println("更新失败！");
		}

	}

	@Override
	public List<ProductCategory> select() {
		// TODO Auto-generated method stub
		List<ProductCategory> selectAll = pro.selectAll();

		return selectAll;

	}

	@Override
	public ProductCategory select_Id(Serializable id) {
		// TODO Auto-generated method stub



		ProductCategory select_Id = pro.select_Id(id);
		
		return select_Id;
	}

}
