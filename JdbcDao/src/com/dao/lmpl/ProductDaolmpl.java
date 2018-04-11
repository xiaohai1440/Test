package com.dao.lmpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import com.bean.Product;
import com.dao.ProductDao;
import com.util.JdbcUtil;
import com.util.ResultSet_Util;


public class ProductDaolmpl extends JdbcUtil implements ProductDao {

	@Override
	public int add(Product t) {
		String sql ="insert into easybuy_product(name,description,price,stock,categoryLevel1Id,categoryLevel2Id,categoryLevel3Id) values(?,?,?,?，?,?,?,?，?,?)";
		Object [] object={t.getName(),t.getDescription(),t.getPrice(),t.getStock(),t.getCategoryLevel1id(),t.getCategoryLevel2id(),t.getCategoryLevel3id()};
		int result=0;
		result=exceuteUpdate(sql, object);
		return result;
	}

	@Override
	public int delete(Serializable id) {
		String sql="delete from easybuy_product where id=?";
		int result=0;
		Object[] object={id};
		result=exceuteUpdate(sql, object);
		return result;
	}

	@Override
	public int update(Product t) {
		// TODO Auto-generated method stub
		int result=0;
		String sql="update easybuy_product set name=?,description=?,price=?,stock=? where id=?";
		Object[] objects={t.getName(),t.getDescription(),t.getPrice(),t.getStock(),t.getId()};
		result=exceuteUpdate(sql,objects);
		return result;
	}

	@Override
	public List<Product> selectAll() {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_product";
		List<Product > list =null;
		try {
			result=exceuteQuery(sql);
			list=ResultSet_Util.selectAllsa(result,Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Product select_Id(Serializable id) {
	
		// TODO Auto-generated method stub
		
		
		String sql="select * from easybuy_product where id=?";
		
		Product selectAlla=null;
		try {
			result=exceuteQuery(sql, id);
					
			selectAlla = ResultSet_Util.selectAlla(result, Product.class);		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return selectAlla;
		
		
	}

	
}
