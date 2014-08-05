package admin.dao.imp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.bean.Manager;
import admin.dao.ManagerDao;
import admin.utils.JdbcUtils;


public class ManagerDaoImp implements ManagerDao {
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	public boolean managerLogin(Manager m) {
		// TODO Auto-generated method stub
		boolean flag=false;//判断管理员身份是否合法
		String sql="select * from manager where manager_Pass=? and manager_name=?";
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,m.getManagerPass());
			pstmt.setString(2,m.getManagerName());
			rs=pstmt.executeQuery();
			if(rs.next()){
				flag=true;
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
