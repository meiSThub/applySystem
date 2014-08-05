package admin.dao;

import java.util.List;

import admin.bean.Individual;
import admin.bean.Item;

public interface IndividualDao {
//	查询所有的用户
	public List<Individual> queryAllIndividual(int currentPage);
//	获得按照size分页时的总页数
	public int getIndividualPageNum();
//	根据输入的查询条件查询用户
	public List<Individual> queryIndByCondation();
//	用户报名成功或失败了的活动，applyItemResult值的不同，表示成功或失败
	public List<Item> queryItemById(int individualId,String applyItemResult,int currentPage);
//	用户一共参加了多少次活动
	public int queryJoinItemNum(int individualId);
}
