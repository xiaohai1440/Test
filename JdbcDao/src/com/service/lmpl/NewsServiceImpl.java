package com.service.lmpl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.bean.News;
import com.dao.NewsDao;
import com.dao.lmpl.NewsDaolmpl;
import com.service.NewsService;

public class NewsServiceImpl implements NewsService {
	
	
	private static Logger logger=Logger.getLogger(NewsService.class);
	//也可以传NewsService.class.getName
	
	NewsDao news=new NewsDaolmpl();

	@Override
	public void add(News t) {
		// TODO Auto-generated method stub
		int add = news.add(t);
		
		if (add>0) {
			System.err.println("新增成功！");
			
		}else {
			System.err.println("新增失败！");
		}
		
		
		
		
	}

	@Override
	public void delete(Serializable t) {
		// TODO Auto-generated method stub
		int delete = news.delete(t);
		if (delete>0) {
			System.err.println("删除成功！");
			
		}else {
			System.err.println("删除失败！");
		}
		
	}

	@Override
	public void updata(News t) {
		// TODO Auto-generated method stub
		
		int update = news.update(t);
		if (update>0) {
			System.err.println("删除成功！");
			
		}else {
			System.err.println("删除失败！");
		}
		
		
	}

	@Override
	public List<News> select() {
		// TODO Auto-generated method stub
		List<News> selectAll = news.selectAll();
		
		
		
		return selectAll;
	}

	@Override
	public News select_Id(Serializable id) {
		// TODO Auto-generated method stub
		News select_Id = news.select_Id(id);
		
		
		return select_Id;
	}

	

}
