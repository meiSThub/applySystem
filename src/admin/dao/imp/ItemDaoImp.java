
package admin.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import admin.bean.Item;
import admin.dao.ItemDao;
import admin.utils.FactoryDemo;
import admin.utils.JdbcUtils;


public class ItemDaoImp implements ItemDao {
	
	List<Item> itemList=null;
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	private int resultCount;	//总记录数
	private int pageCount;		//总页数
	private int size=3;			//每页的条数
	
//	获取所有未被审核活动的总的页数，即未被审核活动按每页size=3分页，一共有多少页
	public int getAllNotCheckItem(){
		String sql= "select count(*)  from declareItem where ItemResult is null ";
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			//setDate 的参数只能是 sql.date  不能精确到时分秒，获取当前时间
			pstmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); 
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
		pageCount=(resultCount + size - 1)/size;//安装size分页后的页数
		return pageCount;
	}
//	按照承办方查询未被审核活动
	public List<Item> findItemByOrganizer(String organizer,int currentPage) {
		// TODO Auto-generated method stub
		//未被审核和查询条件
		String sql="select declareItem_Id,declareItem_title,declareItemStartTime,student,staff,ItemOrganizer，ItemSysDate from declareItem where ItemOrganizer=? and ItemResult is null order by ItemSysDate desc "+" limit "+(currentPage-1)*size + "," +size; 
		conn=JdbcUtils.getConnectin();
		itemList=new ArrayList<Item>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,organizer);
			rs= pstmt.executeQuery();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
//	查询新增加的没有被审核的活动，即审核中的活动
	public List<Item> findDischeckItem(int currentPage,int pageSize) {
		// TODO Auto-generated method stub
		String sql=null;
		sql="select declareItem_Id,declareItem_title,declareItemStartTime,student,staff,ItemOrganizer,ItemSysDate from declareItem where ItemResult is null order by ItemSysDate desc "+" limit " +(currentPage-1)*pageSize + "," +pageSize; 
		conn=JdbcUtils.getConnectin();
		itemList=new ArrayList<Item>();
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
//	审核活动,确定该活动是否通过审核
	public boolean verifyItem(int declareItem_Id,String itemResult) {
		// TODO Auto-generated method stub
//		itemResult有两个值，"y"表示通过审核，"n"表示没通过审核
		boolean isPass=false;
		String sql="update twoapply set ItemResult=? where declareItem_Id=? ";
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,itemResult);
			pstmt.setInt(2,declareItem_Id);
			int n=pstmt.executeUpdate();
			if(n!=0)
				isPass=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isPass;
	}
	
//	根据活动的id查询该活动
	public Item queryItemById(int itemId){
		String sql="select * from declareitem where declareItem_ID=?";
		conn=JdbcUtils.getConnectin();
		Item item=new Item();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setTeamID(rs.getInt("team_Id"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemIntro(rs.getString("declareItem_intro"));
				item.setItemStopApplyTime(rs.getDate("itemStopApplyTime"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setItemStopTime(rs.getDate("declareItemStopTime"));
				item.setItemAddress(rs.getString("itemAddress"));
				item.setItemApplyMax(rs.getInt("declareItemApplyMax"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setAttentions(rs.getString("Attentions"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemResult(rs.getString("ItemResult"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return item;
	}
//	查询报名结束了的活动
	public List<Item> findEndingItem(Date stopTime,int maxNumber) {
		// TODO Auto-generated method stub 
		String sql="select * from declareitem where declareItemStopTime=? or declareItemApplyMax=?";
		conn=JdbcUtils.getConnectin();
		itemList=new ArrayList<Item>();
		try {
			pstmt=conn.prepareStatement(sql);
//			pstmt.setDate(1, stopTime);
			pstmt.setInt(2,maxNumber);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setTeamID(rs.getInt("team_Id"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemIntro(rs.getString("declareItem_intro"));
				item.setItemStopApplyTime(rs.getDate("itemStopApplyTime"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setItemStopTime(rs.getDate("declareItemStopTime"));
				item.setItemAddress(rs.getString("itemAddress"));
				item.setItemApplyMax(rs.getInt("declareItemApplyMax"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setAttentions(rs.getString("Attentions"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemResult(rs.getString("ItemResult"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
//  查询没有通过审核的活动
	public List<Item> findNotPassItem() {
		
		String sql="select * from twoapply where ItemResult=?";
		
		conn=JdbcUtils.getConnectin();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"n");//没有通过审核的时候，ItemResult=n
			rs=pstmt.executeQuery();
			itemList=new ArrayList<Item>();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setTeamID(rs.getInt("team_Id"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemIntro(rs.getString("declareItem_intro"));
				item.setItemStopApplyTime(rs.getDate("itemStopApplyTime"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setItemStopTime(rs.getDate("declareItemStopTime"));
				item.setItemAddress(rs.getString("itemAddress"));
				item.setItemApplyMax(rs.getInt("declareItemApplyMax"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setAttentions(rs.getString("Attentions"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemResult(rs.getString("ItemResult"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
//	查询通过审核的活动，即正在报名中的活动，通过了审核，且在报名时间内，人数也没有超过限定的人数
	public List<Item> findPassItem() {
		// TODO Auto-generated method stub
		String sql="select * from twoapply where ItemResult=?";
		conn=JdbcUtils.getConnectin();
		itemList=new ArrayList<Item>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"y");//没有通过审核的时候，ItemResult=n
			rs=pstmt.executeQuery();
			while(rs.next()){
				Item item=new Item();
				item.setItemID(rs.getInt("declareItem_ID"));
				item.setTeamID(rs.getInt("team_Id"));
				item.setItemTitle(rs.getString("declareItem_title"));
				item.setItemIntro(rs.getString("declareItem_intro"));
				item.setItemStopApplyTime(rs.getDate("itemStopApplyTime"));
				item.setItemStartTime(rs.getDate("declareItemStartTime"));
				item.setItemStopTime(rs.getDate("declareItemStopTime"));
				item.setItemAddress(rs.getString("itemAddress"));
				item.setItemApplyMax(rs.getInt("declareItemApplyMax"));
				item.setStudent(rs.getBoolean("student"));
				item.setStaff(rs.getBoolean("staff"));
				item.setAttentions(rs.getString("Attentions"));
				item.setItemOrganizer(rs.getString("ItemOrganizer"));
				item.setItemResult(rs.getString("ItemResult"));
				item.setItemSysDate(rs.getDate("ItemSysDate"));
				itemList.add(item);
			}
			JdbcUtils.free(conn, pstmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itemList;
	}
}