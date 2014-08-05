package admin.utils;

import java.sql.*;
public class JdbcUtils {
	private static String url="jdbc:mysql://localhost:3306/twoapply";
	private static String user="root";
	private static String password="123456";
	private JdbcUtils(){		
	}
	//静态区域块执行一次  注册驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError();
		}
	}
	// 获得数据库连接
	public static Connection getConnectin() {
		Connection coon=null;
		try {
			coon= DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return coon;
	}
	// 关闭数据库连接
	public static void free(Connection conn, PreparedStatement pstate, ResultSet rs){
		try {
			if(rs !=null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(pstate!=null);
					pstate.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				try {
						if(conn!=null)
							conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}			
		}
	}
}
