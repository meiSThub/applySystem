package admin.dao;

import java.util.List;

import admin.bean.Team;

public interface TeamDao {
//	查询所有单位用户
	public List<Team> queryAllTeam(int currentPage );
//	获得所有单位用户按size分页所得的总页数
	public int getPageNum();
//	根据单位用户的id查询单位用户
	public Team queryTeamById(int teamId);
//	某单位总共举办了多少次活动
	public int getTeamHoldAllItem(int teamId);
}
