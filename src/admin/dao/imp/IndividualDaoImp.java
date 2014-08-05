package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.bean.Individual;
import admin.bean.Item;
import admin.dao.IndividualDao;
import admin.utils.JdbcUtils;

public class IndividualDaoImp implements IndividualDao {
	
	List<Item> itemList=null;				//活动的集合
	List<Individual> indList=null;			//查询出个人用户的集合
	private Connection conn=null;			
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;				//结果集
	
	private int resultCount;//总记录数
	private int pageCount;//总页数
	private int size=3;//每页的条数
	
//	查询所有的用户
	public List<Individual> queryAllIndividual(int currentPage) {
		// TODO Auto-generated method stub
		int size=4;//每页显示活动的条数
		String sql="select individual_name,individual_gender,individual_roles,IndDepartment ,ApplySysDate from individual order by ItemSysDate desc"+" limit " + (currentPage-1)*size + "," +size;
		conn=JdbcUtils.getConnectin();
		indList=new ArrayList<Individual>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Individual ind=new Individual();
				ind.setIndividual_name(rs.getString("individual_name"));
				ind.setIndividual_gender(rs.getString("individual_gender"));
				ind.setIndividual_roles(rs.getString("individual_roles"));
				ind.setIndDepartment(rs.getString("IndDepartment"));
				indList.add(ind);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indList;
	}
//	获得按照size分页时的总页数
	public int getIndividualPageNum(){
		String sql= "select count(*)  from individual ";
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
//	根据输入的查询条件查询用户
	public List<Individual> queryIndByCondation() {
		// TODO Auto-generated method stub
		return null;
	}
//	用户报名成功或失败了的活动，applyItemResult值的不同，表示成功或失败
	public List<Item> queryItemById(int individualId, String applyItemResult,int currentPage) {
		// TODO Auto-generated method stub
		int size=4;
//		查询用户通过报名成功的活动，即individual_Id=? and ApplyItemResult=?
		String sql="select *  from declareitem ,applyitem where (declareitem.declareItem_ID=applyitem.declareItem_ID)and(individual_Id=?) and (ApplyItemResult=?)order by ItemSysDate desc"+" limit " + (currentPage-1)*size + "," +size;
		itemList=new ArrayList<Item>();
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,individualId);		//用户的id
			pstmt.setString(2,applyItemResult);	//报名活动的结果
			rs=pstmt.executeQuery();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemStopApplyTime(rs.getTimestamp("itemStopApplyTime"));
				item.setItemStartTime(rs.getTimestamp("declareItemStartTime"));
				item.setItemStopTime(rs.getTimestamp("declareItemStopTime"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
//	用户一共参加了多少次活动
	public int queryJoinItemNum(int individualId) {
		// TODO Auto-generated method stub
		String sql="select count(*)  from applyitem where individual_ID=? and ApplyItemResult=?";
		int visitNum=0;
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,individualId);		//用户的id
			pstmt.setString(2,"y");				//报名活动的结果
			rs=pstmt.executeQuery();
			rs.next();
			visitNum=rs.getInt(1);
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return visitNum;
	}

}
