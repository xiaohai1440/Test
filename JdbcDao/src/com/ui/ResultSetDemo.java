package com.ui;

import java.util.List;
import java.util.Scanner;
import com.bean.News;
import com.bean.Order;
import com.bean.OrderDetail;
import com.bean.Product;
import com.bean.ProductCategory;
import com.bean.User;
import com.bean.UserAddress;
import com.service.NewsService;
import com.service.OrderDetailService;
import com.service.OrderService;
import com.service.ProductCategoryService;
import com.service.ProductService;
import com.service.UserAddressService;
import com.service.UserService;
import com.service.lmpl.NewsServiceImpl;
import com.service.lmpl.OrderDetailServiceImpl;
import com.service.lmpl.OrderServiceImpl;
import com.service.lmpl.ProductCategoryServiceImpl;
import com.service.lmpl.ProductServiceImpl;
import com.service.lmpl.UserAddressServiceImpl;
import com.service.lmpl.UserServiceImpl;

public class ResultSetDemo {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		showMenu();
	}

	/**
	 * 一级菜单
	 */
	public static void showMenu() {
		System.out.println("------------------欢迎进入管理系统----------------------");

		System.out.println("1.用户管理    2.新闻管理           3.订单操作管理   4.订单详情管理 " +
				"\n5.商品管理    6.商品分类管理    7.用户地址管理   8.退出");

		System.out.println("-------------------------------------------------------");
		System.out.println("请输入选择：");
		int num = input.nextInt();
		switch (num) {
		case 1: // 用户管理
			showUser();
			break;
		case 2: // 新闻管理
			showNews();
			break;
		case 3:
			//订单管理
			order();
			break;
		case 4:			
			//订单详情管理
			order_detail();
			break;
		case 5:
			//商品管理
			product();
			break;
		case 6:
			//商品分类管理
			product_category();
			break;
		case 7:
			//用户地址管理
			user_address();
			break;
		case 8:
			System.out.println("退出系统！欢迎下次使用！");
			return;
		}
	}
	/**
	 * 用户地址管理菜单
	 */
	private static void user_address() {

		UserAddressService userAddress= new UserAddressServiceImpl();
		List<UserAddress> list=null;
		UserAddress ud=null;
		System.out.println("1. 查询所有用户地址   \n2.用户地址更新                \n3.添加用户地址" +
				"		  \n4. 删除用户地址          \n5.查看指定id用户的地址  \n6.回到主菜单");
		switch (input.nextInt()) {
		case 1:
			list=userAddress.select();
			for (UserAddress user : list) {
				System.out.println(user);
			}
			user_address();
			break;
		case 2:
			ud=new UserAddress();
			System.out.println("输入更新用户编号：");
			ud.setUserId(input.nextInt());
			System.out.println("输入更新地址：");
			ud.setAddress(input.next());
			System.out.println("输入更新地址是否是默认地址（1:是 0否）：");
			ud.setIsDefault(input.nextInt());
			System.out.println("输入备注：");
			ud.setRemark(input.next());
			userAddress.updata(ud);
			user_address();
			break;
		case 3:
			ud=new UserAddress();
			System.out.println("输入用户编号：");
			ud.setUserId(input.nextInt());
			System.out.println("输入地址：");
			ud.setAddress(input.next());
			System.out.println("输入地址是否是默认地址（1:是 0否）：");
			ud.setIsDefault(input.nextInt());
			System.out.println("输入备注：");
			ud.setRemark(input.next());
			userAddress.add(ud);
			user_address();
			break;
		case 4:
			System.out.println("输入用户编号：");
			userAddress.delete(input.nextInt());
			user_address();
			break;
		case 5:
			System.out.println("输入用户编号：");
			UserAddress select_Id = userAddress.select_Id(input.nextInt());			
			System.err.println(select_Id+"ee");
			user_address();
			break;

		case 6:
			showMenu();
			break;
		}


	}
	
	/**
	 * 商品分类管理
	 */
	private static void product_category() {

		ProductCategoryService pc=new ProductCategoryServiceImpl();
		ProductCategory pcg=null;
		List<ProductCategory> list=null;
		System.out.println("0.查询指定id商品 \n1.查询所有商品分类\n2.商品分类更新\n3.添加商品分类\n4.删除商品分类\n5.回到主菜单");
		switch (input.nextInt()) {
		case 0:
			System.out.println("输入商品id：");
			ProductCategory select_Id = pc.select_Id(input.nextInt());
			System.err.println(select_Id);
			product_category();
			break;
		case 1:
			list=pc.select();
			for (ProductCategory productCategory : list) {
				System.out.println(productCategory);
			}
			product_category();
			break;
		case 2:
			pcg=new ProductCategory();
			System.out.println("输入更新商品编号：");
			pcg.setId(input.nextInt());
			System.out.println("输入更新商品名称：");
			pcg.setName(input.next());
			System.out.println("输入更新父级目录id：");
			pcg.setParentId(input.nextInt());
			System.out.println("输入更新级别(1:一级 2：二级 3：三级)");
			pcg.setType(input.nextInt());
			pc.updata(pcg);
			product_category();
			break;
		case 3:
			pcg=new ProductCategory();
			System.out.println("输入商品编号：");
			pcg.setId(input.nextInt());
			System.out.println("输入商品名称：");
			pcg.setName(input.next());
			System.out.println("输入父级目录id：");
			pcg.setParentId(input.nextInt());
			System.out.println("输入级别(1:一级 2：二级 3：三级)");
			pcg.setType(input.nextInt());
			pc.add(pcg);
			product_category();
			break;
		case 4:
			pcg=new ProductCategory();
			System.out.println("输入商品编号：");
			pc.delete(pcg);
			product_category();
			break;
		case 5:
			showMenu();
			break;
		}
	}
	private static void product() {


		ProductService ps = new ProductServiceImpl();
		List<Product> list=null;
		Product product1=null;
		System.out.println("1.查询所有商品\n2.商品更新\n3.添加商品\n4.删除商品\n5.回到主菜单");
		switch (input.nextInt()) {
		case 1:
			list=ps.select();
			for (Product product : list) {
				System.out.println(product);
			}
			product();
			break;

		case 2:
			product1=new Product();
			System.out.println("输入更新商品编号：");
			product1.setId(input.nextInt());
			System.out.println("输入更新商品名称：");
			product1.setName(input.next());
			System.out.println("输入更新商品描述");
			product1.setDescription(input.next());
			System.out.println("输入更新商品价格");
			product1.setPrice(input.nextFloat());
			System.out.println("输入更新商品库存");
			product1.setStock(input.nextInt());


			ps.updata(product1);
			product();
			break;
		case 3:
			product1=new Product();
			System.out.println("输入商品编号：");
			product1.setId(input.nextInt());
			System.out.println("输入商品名称：");
			product1.setName(input.next());
			System.out.println("输入商品描述");
			product1.setDescription(input.next());
			System.out.println("输入商品价格");
			product1.setPrice(input.nextFloat());
			System.out.println("输入商品库存");
			product1.setStock(input.nextInt());
			System.out.println("输入商品分类1：");
			product1.setCategoryLevel1id(input.nextInt());
			System.out.println("输入商品分类2：");
			product1.setCategoryLevel2id(input.nextInt());
			System.out.println("输入商品分类3：");
			product1.setCategoryLevel3id(input.nextInt());
			ps.add(product1);
			product();
			break;
		case 4:
			System.out.println("输入商品编号 ：");
			ps.delete(input.nextInt());
			product();
			break;
		case 5:
			showMenu();
			break;
		}
	}

	/**
	 * 订单详情管理
	 */
	private static void order_detail() {



		OrderDetailService od=new OrderDetailServiceImpl();
		OrderDetail oDetail=null;
		List<OrderDetail> list=null;
		System.out.println("1.查询所有订单详情\n2.订单详情更新\n3.添加订单详情\n4.删除订单详情\n5.回到主菜单");
		switch (input.nextInt()) {
		case 1:
			list=od.select();
			for (OrderDetail orderDetail : list) {
				System.out.println(orderDetail);
			}
			order_detail();
			break;

		case 2:
			oDetail=new OrderDetail();
			System.out.println("输入更新订单编号：");
			oDetail.setOrderId(input.nextInt());
			System.out.println("输入更新商品编号：");
			oDetail.setProductId(input.nextInt());
			System.out.println("输入更新订单数量：");
			oDetail.setQuantity(input.nextInt());
			System.out.println("输入更新消费金额：");
			oDetail.setCost(input.nextFloat());

			od.updata(oDetail);

			order_detail();
			break;
		case 3:
			oDetail=new OrderDetail();
			System.out.println("输入订单编号：");
			oDetail.setOrderId(input.nextInt());
			System.out.println("输入商品编号：");
			oDetail.setProductId(input.nextInt());
			System.out.println("输入订单数量：");
			oDetail.setQuantity(input.nextInt());
			System.out.println("输入消费金额：");
			oDetail.setCost(input.nextFloat());
			od.add(oDetail);
			order_detail();
			break;
		case 4:
			System.out.println("输入你要删除的订单编号：");
			od.delete(input.nextInt());
			order_detail();
			break;
		case 5:
			showMenu();
			break;
		}


	}


	/**
	 * 3.订单操作菜单
	 */
	private static void order() {
		// TODO Auto-generated method stub
		OrderService os = new OrderServiceImpl();
		List<Order> list=null;
		Order order=null;
		System.out.println("1.查询所有订单\n2.订单更新\n3.添加订单\n4.查询指定订单\n5.删除订单\n6.回到主菜单");
		switch (input.nextInt()) {
		case 1:
			list=os.select();
			for (Order order1 : list) {
				System.out.println(order1);
			}
			order();
			break;

		case 2:
			order=new Order();
			System.out.println("输入更新用户编号");
			order.setUserId(input.nextInt());
			System.out.println("输入更新登录名：");
			order.setLoginName(input.next());
			System.out.println("输入更新用户地址：");
			order.setUserAddress(input.next());
			System.out.println("输入更新总消费：");
			order.setCost(input.nextFloat());
			System.out.println("输入更新订单号：");
			order.setSerialNumber(input.next());
			os.updata(order);
			order();
			break;
		case 3:
			order=new Order();
			System.out.println("输入用户编号");
			order.setUserId(input.nextInt());
			System.out.println("输入登录名：");
			order.setLoginName(input.next());
			System.out.println("输入用户地址：");
			order.setUserAddress(input.next());
			System.out.println("输入总消费：");
			order.setCost(input.nextFloat());
			System.out.println("输入订单号：");
			order.setSerialNumber(input.next());
			os.add(order);
			order();
			break;
		case 4:
			System.out.println("输入Id编号");
			Order od = os.select_Id(input.nextInt());
			System.out.println(od);
			order();
			break;
		case 5:

			System.out.println("输入删除的用户编号");
			os.delete(input.nextInt());
			order();

			break;
		case 6:
			showMenu();
			break;
		}

	}

	/**
	 * 2:新闻管理菜单
	 */
	private static void showNews() {


		NewsService ns = new NewsServiceImpl();

		News news=null;
		String title=null;
		String content=null;
		System.out.println("1.查看所有新闻2.更新新闻3.删除新闻4.添加新闻5.回到菜单");
		switch (input.nextInt()) {
		case 1:
			List<News> list =ns.select();
			for (News news1: list) {
				System.out.println(news1.toString());
			}
			showNews();
			break;
		case 2:
			System.out.println("输入要更新的新闻编号：");
			news.setId(input.nextInt());
			System.out.println("请输入更新标题：");
			title=input.next();
			System.out.println("请输入更新内容：");
			content=input.next();
			news=new News();
			news.setContent(content);
			news.setTitle(title);
			ns.updata(news);

			showNews();
			break;
		case 3:
			System.out.println("输入要删除新闻的编号:");

			ns.delete(input.nextInt());


			showNews();
			break;
		case 4:
			System.out.println("请输入标题：");
			title=input.next();
			System.out.println("请输入内容：");
			content=input.next();
			news=new News(null, title, content, null);
			ns.add(news);
			showNews();
			break;
		case 5:
			showMenu();
			break;
		}

	}

	/**
	 * 用户管理菜单
	 */
	private static void showUser() {
		// 实例化UserService层的对象
		UserService service = new UserServiceImpl();
		System.out.println("=================================");
		System.out.println("欢迎进入用户管理系统");
		System.out.println("1:登录");
		System.out.println("2:注册");
		System.out.println("3:删除");
		System.out.println("4:修改");
		System.out.println("5:查询所有");
		System.out.println("6:查询指定用户信息");
		System.out.println("7:返回上级目录");
		System.out.println("请您选择：");
		int choose = input.nextInt();
		switch (choose) {
		case 1: // 登录

			userLogin(service);
			 showUser();
			break;
		case 2: // 注册

			userAdd(service);
			 showUser();

			break;
		case 3: // 删除
			userDelete(service);
			 showUser();
			break;
		case 4: // 修改
			update_s(service);
			 showUser();

			break;
		case 5: // 查询所有

			select_All(service);
			 showUser();
			break;
		case 6: // 查询指定用户信息

			System.out.println("输入要查询订单的ID");
			service.select_Id(input.nextInt());
			 showUser();
			break;
		case 7: // 返回上级目录
			showMenu();
			break;
		}

	}



	private static void update_s(UserService service) {
		System.out.println("输入要改的信息的Id编号的用户!");
		User user=new User();
		user.setId(input.nextInt());

		System.err.println("输入下面的编号 :\n0：改登录名——loginName  1：改用户名  userName \n2：改 密码——password  " +
				"\n3：改 sex(0 | 1) \n4：身份 identityCode" +
				" 5：改email   6：改电话号码——mobilePhone  \n7：改type    其他提交"     );

		boolean fale=true;

		do {

			int s=input.nextInt();

			switch (s) {

			case 0:
				System.out.println("请输入要修改的值：");
				user.setLoginName(input.next());
				break;
			case 1:
				System.out.println("请输入要修改的值：");
				user.setUserName(input.next());
				break;
			case 2:
				System.out.println("请输入要修改的值：");
				user.setPassword(input.next());
				break;
			case 3:
				System.out.println("请输入要修改的值：");
				user.setSex(input.nextInt());
				break;
			case 4:
				System.out.println("请输入要修改的值：");
				user.setIdentityCode(input.next());
				break;
			case 5:
				System.out.println("请输入要修改的值：");
				user.setEmail(input.next());
				break;
			case 6:
				System.out.println("请输入要修改的值：");
				user.setMobile(input.next());
				break;
			case 7:
				System.out.println("请输入要修改的值：");
				user.setType(input.nextInt());
				break;
			default:
				fale=false;
				break;
			}
			if (fale) {
				System.out.println("继续选择！");
			}

		} while (fale); 


		service.updata(user);



	}

	private static void select_All(UserService service) {


		List<User> selectAll = service.select();

		for (User user : selectAll) {
			System.err.println(user);
		}


		// TODO Auto-generated method stub

	}

	private static void userAdd(UserService service) {


		User user=new User();

		/*String [] nums={"id","loginName","userName","password","sex","identityCode","email","mobile","type"};

		for (String string : nums) {

		}*/

		System.out.println("请输入id");	
		user.setId(input.nextInt());
		System.out.println("请输入loginName");
		user.setLoginName(input.next());
		System.out.println("请输入userName");
		user.setUserName(input.next());
		System.out.println("请输入password");
		user.setPassword(input.next());
		System.out.println("请输入sex");
		user.setSex(input.nextInt());
		System.out.println("请输入identityCode");
		user.setIdentityCode(input.next());
		System.out.println("请输入email");
		user.setEmail(input.next());
		System.out.println("请输入mobilePhone");
		user.setMobile(input.next());		
		System.out.println("请输入type");
		user.setType(input.nextInt());

		service.add(user);



	}

	public static void userDelete(UserService service) {
		System.out.println("请您输入需要删除的ID：");
		int id = input.nextInt();
		service.delete(id);
	}
	
	public static void userLogin(UserService service) {


		System.out.println("请您输入用户名：");
		String loginName = input.next();
		System.out.println("请您输入密码：");
		String password = input.next();
		User user = service.login(loginName, password);
		System.out.println("您的个人信息如下：");
		System.err.println(user);



	}
}