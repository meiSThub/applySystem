package admin.control;

import admin.bean.Item;
import admin.bean.Team;
import admin.dao.imp.ItemDaoImp;
import admin.dao.imp.TeamDaoImp;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ItemDaoImp idi=new ItemDaoImp();
		Item item=idi.queryItemById(1);
		System.out.println(item.getItemIntro());
		TeamDaoImp t=new TeamDaoImp();
		for(Team team:t.queryAllTeam(1)){
			System.out.println("team"+team.getTeamName());
		}
	}

}
