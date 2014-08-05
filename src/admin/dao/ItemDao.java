package admin.dao;
import java.util.Date;
import java.util.List;

import admin.bean.*;

public interface ItemDao {

	public List<Item> findItemByOrganizer(String organizer,int currentPage);//按照承办方查询申报活动
	public List<Item> findDischeckItem(int currentPage,int pageSize);//查询新增加的没有被审核的活动，即审核中的活动
	public int getAllNotCheckItem();//查出所有未被审核活动的总页数
	public boolean verifyItem(int declareItem_Id,String itemResult);//审核活动，
	public List<Item> findPassItem();//查询通过审核的活动，即正在报名中的活动
	public List<Item> findEndingItem(Date stopTime,int maxNumber);//查询报名结束了的活动
	public List<Item> findNotPassItem();//查询没有通过审核的活动
//	根据活动的id查询该活动
	public Item queryItemById(int itemId);

	
}
