package com.dao.lmpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.ProductCategory;
import com.dao.Product_CategoryDao;
import com.util.JdbcUtil;
import com.util.ResultSet_Util;

public class Product_CategoryDaolmpl extends JdbcUtil implements Product_CategoryDao  {

	@Override
	public int add(ProductCategory t) {
		String sql = "INSERT INTO easybuy_product_category (name,parentId,type,iconClass) VALUES(?,?,?,?)";
		Object[] params = { t.getName(), t.getParentId(), t.getType(), t.getIconClass() };

		int rowNum = 0;

		rowNum=exceuteUpdate(sql, params);
		return rowNum;
	}

	@Override
	public int delete(Serializable id) {
		String sql = "delect from easybuy_product_category where id=?";
		Object[] params = { id };
		int rowNum = 0;
		rowNum=exceuteUpdate(sql, params);
		return rowNum;
	}

	@Override
	public int update(ProductCategory t) {
		String sql = "update easybuy_product_category set name=?,parentId=?,type=?,iconClass=? where id =?";
		Object[] params = { t.getName(), t.getParentId(), t.getType(), t.getIconClass(), t.getId() };
		int rowNum = 0;
		rowNum=exceuteUpdate(sql, params);
		return rowNum;
	}

	@Override
	public List<ProductCategory> selectAll() {
		String sql = "select * from easybuy_product_category";

		// 创建集合保存所有信息
		List<ProductCategory> easybuy_list = new ArrayList<>();
		try {
			result=exceuteQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

			close_();
		}
		easybuy_list=ResultSet_Util.selectAllsa(result, ProductCategory.class);

		return easybuy_list;
	}

	@Override
	public ProductCategory select_Id(Serializable id) {




		String sql = "select * from easybuy_product_category where id=?";
		Object[] params = { id };
		ProductCategory productCategory=null;
		
		try {
			result=exceuteQuery(sql, params);
			productCategory=ResultSet_Util.selectAlla(result, ProductCategory.class);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			close_();
		}
		
		
		return productCategory;
	}

}
