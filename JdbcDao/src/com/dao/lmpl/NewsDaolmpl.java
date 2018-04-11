package com.dao.lmpl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.News;
import com.bean.User;
import com.dao.NewsDao;
import com.mysql.jdbc.util.ResultSetUtil;
import com.util.JdbcUtil;
import com.util.ResultSet_Util;

public class NewsDaolmpl extends JdbcUtil implements NewsDao {

	@Override
	public int add(News t) {
		String sql = "INSERT INTO `easybuy_news`(title,content,createTime,img)VALUES(?,?,?,?);";
		Object[] params = { t.getTitle(), t.getContent(), t.getCreateTime(), t.getId() };

		int rowNum = 0;

		rowNum = exceuteUpdate(sql, params);

		return rowNum;
	}


	@Override
	public int delete(Serializable id) {
		String sql = "DELETE FROM easybuy_news WHERE id=? ";
		Object[] parmas = { id };

		int rowNum = 0;

		rowNum = exceuteUpdate(sql, parmas);

		return rowNum;

	}

	@Override
	public int update(News t) {

		String sql = "UPDATE easybuy_news SET title=?,content=? WHERE id=?";
		Object[] params = { t.getTitle(), t.getContent(), t.getId() };

		int rowNum = 0;

		rowNum = exceuteUpdate(sql, params);

		return rowNum;
	}

	@Override
	public List<News> selectAll() {
		String sql = "select * from easybuy_news";
		//创建一个集合保存所有新闻信息
		List<News> newss = new ArrayList<>();

		try {
			result = exceuteQuery(sql);

			newss=ResultSet_Util.selectAllsa(result, News.class);

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAss(conn, pre, result);
		}

		return newss;
	}


	@Override
	public News select_Id(Serializable id) {

		String sql = "select * from easybuy_news where id=?";
		// 给参数赋值
		Object[] params = { id };
		News user = null;
		try {
			result = exceuteQuery(sql, params);
			user = ResultSet_Util.selectAlla(result, News.class);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close_();


		}
		
		return user;
	}


	

}

