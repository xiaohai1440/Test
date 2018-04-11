package utilclass;

//import java.lang.reflect.Field;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.lang.model.element.Element;

import com.util.ConfigManager;
import com.util.JdcbUtil;

public class UtilClass extends JdcbUtil{

	/**
	 * 
	 * @throws SQLException
	 * 对外提供的接口,
	 * 可以操作数据库！生成实体类！
	 */
	public void util_java() throws SQLException {

		/*List存的是所有数据库的名字*/
		List<String> dataName = getDataName();

		/*这个Map存的是所有的数据库对应所有表名*/
		Map<String, List<String>> tableName = getTableName(dataName);

		Set<String> keySet = tableName.keySet();
		
		 Scanner input=new Scanner(System.in);


		//		List<String> li=new ArrayList();
		//		xianshijiazai(keySet, li);
		System.err.println("--------进入了数据库生成实体类逆向工程系统！----------");
		System.err.println("---------------1:显示所有数据库----------------------");
		System.err.println("---------------2:查看指定数据库的表------------------");
		System.err.println("---------------3:查看全部数据库的全部表---------------");
		System.err.println("---------------4:操作数据生成对应的实体类-------------");
		System.err.println("--------------- :其他键退出--------------------------");


		
		boolean fale=false;

		do {

			String next = input.next();

			switch (next) {
			case "1":

				show_databases_name(dataName);
				System.out.println("----------------请继续操作！----------------------");
				break;
			case "2":
				System.err.println("选择数据库的编号！");
				//其实传一个key(数据库)就能获得对应的数据库的全部表名字！
				show_tables(dataName, tableName, input.nextInt());
				System.out.println("----------------请继续操作！----------------------");

				break;

			case "3":
				xianquan(tableName,dataName);
				System.out.println("----------------请继续操作！----------------------");

				break;

			case "4":

				entity_class(tableName, dataName);

				System.out.println("----------------请继续操作！----------------------");

				break;

			default:
				System.out.println("退出系统！欢迎下次使用！");
				fale=true;
				break;
			}


		} while (!fale);

	}

	/**
	 * 创建包,和类方法
	 * 
	 * @param bao      数据库的名字
	 * @param string   表的名字
	 * @throws SQLException
	 */
	public void create_bata_package(String bao,String string) throws SQLException{

		Writer out = null;
		BufferedWriter bw = null;		
		File file=null;

		//截取表名变为类的名字
		String	lei=string.substring(0, 1).toUpperCase()+string.substring(1);


		//创建包名和类的方法
		file=file(bao,lei);

		if (file==null) {
			System.err.println("保留原来的!");
			//就往回调！
			//continue;
			return;
		}
		ResultSet result =null;
		try {
			//输出流写入的位置，true拼接不覆盖原有的东西
			out = new FileWriter(file,true);
			//out.write(str);
			//通过缓冲流
			bw = new BufferedWriter(out);



			//sql语句
			String sql ="select * from "+string;			
			//获取结果集ResultSet  因为前面已经切换了数据库
			boolean falg = getConnection(bao);
			result = exceuteQuery(sql);
			//getMetaData()方法获得   ResultSetMetaData对象就可以操作元数据了
			ResultSetMetaData metaData =result.getMetaData();
			//表的列数，也就是字段的个数
			int columnCount = metaData.getColumnCount();

			StringBuffer canshu=new StringBuffer();//拼接参数
			StringBuffer This=new StringBuffer();  //拼接this.属性取值
			StringBuffer ziduan=new StringBuffer();//拼接私有字段的
			StringBuffer get_set=new StringBuffer();//拼接get_set方法
			StringBuffer toString=new StringBuffer();//拼接toString的内容

			//根据表的列数
			for (int i = 1; i <=columnCount; i++) {

				pin_jie(metaData, columnCount, canshu, This, ziduan, get_set,
						toString, i);

			}

			writr_java(bao, bw, lei, canshu, This, ziduan, get_set, toString);

		} catch (IOException e) {
			e.printStackTrace();
		}finally{

			//关闭流
			if (bw!=null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out!=null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			closeAss(conn, pre, result);
		}
		//		}


	}
	
	/**
	 * 
	 * @param list
	 * @return    获得所有数据的所有表名
	 * @throws SQLException
	 */
	public Map<String, List<String>> getTableName(List<String> list) throws SQLException{

		Map<String, List<String>> map=new HashMap();
		List<String> list1=new ArrayList();

		for (String string : list) {

			getConnection(string);				
			String sql2="show tables";								
			ResultSet result= exceuteQuery(sql2);

			while (result.next()) {
				//存表的名字
				list1.add(result.getString(1));


			}

			map.put(string,list1);
			list1=new ArrayList();
			closeAss(conn, pre, result);
		}


		return map;	

	}


	/**
	 * 
	 * @return  获得数据库全部名字的方法
	 * @throws SQLException 
	 */
	public List<String> getDataName() throws SQLException{

		List<String> list = new ArrayList();// 储存database 名称
		String sql="show databases;";
		getConnection();
		result = exceuteQuery(sql);

		while (result.next()) {

			//去除slq自带的数据库
			boolean falg="information_schema".equals(result.getString("database"))
					||"performance_schema".equals(result.getString("database"))
					||"mysql".equals(result.getString("database"))
					||"sys".equals(result.getString("database"));
			if (falg) {
				continue;
			}		

			list.add(result.getString("database"));

		}
		closeAss(conn, pre, result);			
		return list;

	}


	/**
	 * 
	 * @param tableName
	 * 显示所有数据库的所有的表名的方法
	 */
	private void xianquan(Map<String, List<String>> tableName , List<String> keySet) {

//		Set<String> keySet = tableName.keySet();
		int ii=0;
		for (String string : keySet) {

			List<String> list = tableName.get(string);

			System.out.println(++ii+" :"+string+"  数据库  有以下的表！");
			int i=0;
			for (String str : list) {

				System.out.print(++i+" 、 "+str+"  ");

				if (i%4==0) {
					System.out.println("\n--------------------------------------------------");
				}

			}
			System.out.println("\n");


		}


	}

	/**
	 * 
	 * @param dataName  显示所有数据库的名字的方法
	 */
	private void show_databases_name(List<String> dataName) {
		System.err.println("Myslq数据库里有如下的数据库：");
		for (int i=0 ; i<dataName.size();i++) {

			System.out.print(i+1+" : "+dataName.get(i)+"   ");

			if ((i+1)%4==0) {
				System.out.println("\n--------------------------------------------------");
			}

		}
		System.out.println("");
	}

	/**
	 * 
	 * @param dataName
	 * @param tableName
	 * @param input  显示单个数据库所有表的名字的方法
	 */
	private void show_tables(List<String> dataName,
			Map<String, List<String>> tableName, int input) {


		System.out.println("选择"+input+":"+dataName.get(input-1)+"_数据库有下列的表:");

		//取得单个数据库的所有表名
		List<String> list = tableName.get(dataName.get(input-1));
		show_databases_name(list);
	}


	/**
	 * 
	 * @param bao
	 * @param bw
	 * @param lei
	 * @param canshu
	 * @param This
	 * @param ziduan
	 * @param get_set
	 * @param toString  生成一个类所有组件
	 */
	private void writr_java(String bao, BufferedWriter bw, String lei,
			StringBuffer canshu, StringBuffer This, StringBuffer ziduan,
			StringBuffer get_set, StringBuffer toString) {
		//创建类的头部
		writeJava(bw ,bao,lei);
		//写入私有变量
		write_variable(bw, ziduan.toString());
		//写入构造
		write_constructor(bw, canshu.toString(), This.toString(),lei);
		//写入getter,setter方法
		write_get_set(bw,get_set.toString());
		//写入toString()方法；
		write_toString(bw,lei,toString.toString());
	}

	/**
	 * 
	 * @param metaData
	 * @param columnCount
	 * @param canshu
	 * @param This
	 * @param ziduan
	 * @param get_set
	 * @param toString
	 * @param i
	 * @throws SQLException   
	 * 拼接要生成类的每个模块
	 */
	private void pin_jie(ResultSetMetaData metaData, int columnCount,
			StringBuffer canshu, StringBuffer This, StringBuffer ziduan,
			StringBuffer get_set, StringBuffer toString, int i)
					throws SQLException {
		//字段名字
		String columnName = metaData.getColumnName(i);
		//获取的是JDBC的数据类型
		//String nameType = metaData.getColumnTypeName(i);
		//这个是获取了Java的类型							
		String nameType=metaData.getColumnClassName(i);	
		//拿到最原始的类型lang包的，进行截取
		int of = nameType.lastIndexOf(".");				
		nameType=nameType.substring(of+1);

		//拼接私有的变量
		ziduan.append("\t private "+" "+nameType+" "+columnName+";\n");

		if (i!=columnCount) {
			//拼接toString()方法的内容,用到了转义符  \
			toString.append(columnName+"=\"+" + columnName + "+\",");
			//拼接参数
			canshu.append(nameType +" "+columnName+", ");
			if (i%2==0) {
				//因为在回车的时候String前后要加一个分号
				toString.append("\"\n\t\t+\"");
			}
		}else {
			toString.append(columnName+"=\"+" + columnName);
			canshu.append(nameType +" "+columnName);
		}				
		//拼接构造赋值的变量
		This.append(" \t\t this."+columnName+"="+columnName+";\n");
		//拼接get_set方法
		get_set.append(get_set(columnName,nameType));
	}


	/**
	 * 
	 * @param lei
	 * @param url
	 * @return    创建文件夹，文件的方法
	 */
	private File file(String bao,String url) {

		/**
		 * 每个方法都是进来压栈，对应的栈帧，执行完就出栈，典型的递归，容易发生栈溢出OutOfMemoryError
		 */

		/*创建包的名字*/
		File file=new File("src/"+bao);

		if (!file.isDirectory()) {//判断是否是一个文件夹(相当于包的名字)
			file.mkdir();
			//file.mkdirs();  可以父子目录一起创建
			System.err.println("\n\n创建 "+bao+"_package 成功！");
		}

		file=new File("src/"+bao+"/"+url+".java");

		if (!file.isFile()) {//判断是否是文件
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.err.println("创建 "+url+".java文件 成功！");
		}else {
			System.err.println("已经有这个 "+url+".java文件了");
			System.err.println("1:删除！2:保留");
			Scanner delete=new Scanner(System.in);

			if (delete.next().equals("1")) {//判断选择
				file.delete();//删除文件
				file=file(bao, url);

			}else {
				return null;
			}

		}
		return file;
	}


	/**
	 * 
	 * @param bw
	 * @param lei
	 * @param toString 写入toString()方法
	 */
	private void  write_toString(BufferedWriter bw,String className,String toString) {


		/*public String toString() {
			return "User [id=" + id + ", loginName=" + loginName + ", userName="
					+ userName + ", password=" + password + ", sex=" + sex
					+ ", identityCode=" + identityCode + ", email=" + email
					+ ", mobile=" + mobile + ", type=" + type + "]";
		}*/

		String user="\t @Override\n\t public String toString() {\n\n"				
				+"\t return \""+className+" ["+toString+"+\"]\";\n\t}\n}";

		try {
			bw.write(user);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}




	}



	/**
	 * 
	 * @param bw
	 * @param get_set 写入get_set方法
	 */
	private void  write_get_set(BufferedWriter bw,String get_set) {

		try {
			bw.write(get_set);
			bw.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param columnName
	 * @param nameType
	 * @return  取得get_set方法
	 */
	private String get_set(String columnName,String nameType) {


		String columnName1=columnName.substring(0, 1).toUpperCase()+columnName.substring(1);

		String string2="\t public "+nameType+" get"+columnName1+"() {\n"	
				+"\t\t return this."+columnName+";\n"	

		+"}\n\n"

		+"\t public void set"+columnName1+"("+nameType+"  "+columnName+") {\n"
		+"\t\t this."+columnName+" = "+columnName+";\n"
		+"}\n\n";

		return string2;

	}



	/**
	 * 
	 * @param bw
	 * @param nameType
	 * @param columnName
	 * @param className 写入构造函数
	 */
	private void write_constructor(BufferedWriter bw, String nameType,String columnName,String className) {

		String  constructor="\n\t public "+className+"() {  " +
				"\n}\n\n"
				+"\t public "+className+"("+nameType+") {\n\n"
				+columnName
				+"	}\n\n";

		try {
			bw.write(constructor);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	/**
	 * 
	 * @param bw
	 * @param variable
	 */
	public void write_variable(BufferedWriter bw,String variable){


		try {
			bw.write(variable+"\n");
			bw.flush();// 清除缓存
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

	/**
	 * 
	 * @param bw
	 * @param name4 创建类的头部
	 */
	public void writeJava(BufferedWriter bw,String bao,String strClass) {

		String className="package "+bao+";\n"+"import java.io.Serializable;\n"
				+"public class " + strClass + " implements Serializable{\n\n";

		try {

			bw.write(className);
			bw.flush();// 清除缓存

		} catch (IOException e) {

			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param tableName
	 * @param keySet
	 * @param li
	 * @throws SQLException  选择生成实体类的逻辑方法
	 */
	private void entity_class(Map<String, List<String>> tableName,
			List<String> dataName) throws SQLException {


		System.err.println("-----选择要生成实体类对应数据库的编号！注意：输入0全部生成！输入N返回！-----");
		boolean falg = false;//标记
		Scanner input=new Scanner(System.in);

		String next = input.next();
		falg = next.matches("^[-+]?(([0-9]+)(.)?|(.)?)$");
		
		if (next.equalsIgnoreCase("N")) {
			util_java();
		}
		if (!falg) {
			System.err.println("输入的不是数字，重新选择！");

			entity_class(tableName, dataName);
		}

		int int1 = Integer.parseInt(next);

		if (0<int1&&int1<=dataName.size()) {//判断选择是否再正确数据库编号

			/*获取到了数据的全部表*/
			List<String> list = tableName.get(dataName.get(int1-1));
			
			/*显示单个数据库的所有表*/		
			//show_databases_name(dataName);
			show_table_names(list);
			//show_tables(dataName, tableName, int1);

			do {


				System.err.println("-----选择要导出成实体类表的编号，注意：输入0全部表生成对应的实体类！-----");

				int int2 = -1;
				next = input.next();
				//正则表达式
				falg = next.matches("^[-+]?(([0-9]+)(.)?|(.)?)$");
				if (!falg) {
					System.err.println("输入的不是数字，重新:");
					falg=true;
				}else {
					int2 = Integer.parseInt(next);
				}
				
				if (0<int2&&int2 <= list.size()) {

					create_bata_package(dataName.get(int1 - 1), list.get(int2 - 1));

					falg = fale(input);

				}else if(int2==0){
					for (String string : list) {
						//单个数据库的全部表生成实体类
						create_bata_package(dataName.get(int1-1),string );
					}
					falg = fale(input);

				}else{
					System.err.print("你的选择没有这个编号的，请重新：！");
					falg=true;

				}
			} while (falg);

		}else if(int1==0){


			for (String string2 : dataName) {

				List<String> lis = tableName.get(string2);

				for (String string3 : lis) {

					create_bata_package(string2, string3);

				}

			}

		}else {
			System.err.println("输入没有这个选项！重新来过");
			entity_class(tableName, dataName);
		}
	}


	private void show_table_names(List<String> list_table) {
		
		for (int i=0 ; i<list_table.size();i++) {

			System.out.print(i+1+" : "+list_table.get(i)+"   ");

			if (i%4==0) {
				System.out.println("\n--------------------------------------------------");
			}
		}	
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 * @throws SQLException   判断是否继续操作的方法
	 */
	private boolean fale(Scanner input) throws SQLException {
		String next;
		System.out.println("继续选择，按y键，其他键返回上首页！");
		next = input.next();
		if (next.equalsIgnoreCase("y")) {
			System.err.print("请继续：");
			return true;
		}else{
			util_java();
			return false;
		}
	}

	
	
	public static void main(String[] args) throws SQLException {

		new UtilClass().util_java();
	}


}




