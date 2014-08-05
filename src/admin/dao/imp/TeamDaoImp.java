package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.bean.Team;
import admin.dao.TeamDao;
import admin.utils.JdbcUtils;

public class TeamDaoImp implements TeamDao {
	// 声明一个数据库连接对象
	private Connection conn = null ;
	// 声明一个数据库操作对象
	private PreparedStatement pstmt= null ;
	// 声明一个结果集对象
	private ResultSet rs = null ;
	//sql语句
	private String sql=null;
	//集合
	private List<Team> teamList=null;
	
	private int resultCount;//总记录数
	private int pageCount;//总页数
	private int size=3;//每页的条数
	
//	查询所有单位用户
	public List<Team> queryAllTeam(int currentPage ) {
		// TODO Auto-generated method stub
		sql="select * from team limit " + (currentPage-1)*size + "," +size;
		conn=JdbcUtils.getConnectin();
		teamList=new ArrayList<Team>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Team team=new Team();
				team.setTeamId(rs.getInt("team_ID"));
				team.setTeamPass(rs.getString("team_pass"));
				team.setTeamName(rs.getString("team_name"));
				team.setTeamPrincipal(rs.getString("team_principal"));
				team.setTeamTel(rs.getString("team_tel"));
				team.setTeamAddress(rs.getString("team_address"));
				team.setTeamEmail(rs.getString("team_email"));
				teamList.add(team);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
		JdbcUtils.free(conn, pstmt, rs);
		}
		return teamList;
	}
//	获得所有单位用户按size分页所得的总页数
	public int getPageNum() {
		// TODO Auto-generated method stub
		sql= "select count(*)  from team ";
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			resultCount=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{	
			JdbcUtils.free(conn, pstmt, rs);
		}
//		分的页数
		pageCount=(resultCount + size - 1)/size;
		return pageCount;
	}
	
//	根据单位用户的id查询单位用户
	public Team queryTeamById(int teamId) {
		// TODO Auto-generated method stub
		sql="select * from team where team_ID=?";
		conn=JdbcUtils.getConnectin();
		Team team=new Team();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, teamId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				team.setTeamId(rs.getInt("team_ID"));
				team.setTeamPass(rs.getString("team_pass"));
				team.setTeamName(rs.getString("team_name"));
				team.setTeamPrincipal(rs.getString("team_principal"));
				team.setTeamTel(rs.getString("team_tel"));
				team.setTeamAddress(rs.getString("team_address"));
				team.setTeamEmail(rs.getString("team_email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{	
			JdbcUtils.free(conn, pstmt, rs);
		}
		return team;
	}
//	某单位总共举办了多少次活动
	public int getTeamHoldAllItem(int teamId){
		sql= "select count(*)  from declareitem ";
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			resultCount=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{	
			JdbcUtils.free(conn, pstmt, rs);
		}
		return resultCount;
	}
}

