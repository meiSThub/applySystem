package admin.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.bean.Item;
import admin.bean.Manager;
import admin.bean.Team;
import admin.dao.imp.ItemDaoImp;
import admin.dao.imp.ManagerDaoImp;
import admin.dao.imp.TeamDaoImp;
import admin.utils.FactoryDemo;

public class AdminEventDetail extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp )throws IOException,ServletException{
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
		String pageChildren=req.getParameter("pageChildren");
		String path="admin_index.jsp";//设置跳转页面
		//保存错误信息
		List errors=new ArrayList();
		System.out.println("跳转到那个选项卡"+pageChildren);
		if(pageChildren!=null){
//		审核活动子页面
		if("event".equals(pageChildren)){
			/*如果id不为空，则跳转到admin_index.jsp页面，否则跳转到admin_event_detail.jsp页面*/
			System.out.println("在活动页面");
			int itemId=0;
			path="admin_index.jsp?pageChildren=event";
			//接收从地址栏传过来的活动id
			String id=req.getParameter("itemId");
			if(id!=null&&"".equals(id))
				itemId=Integer.parseInt(id);
			//给person赋值
			Item item=new Item();
			if(itemId!=0){
				ItemDaoImp itemDaoImp=new ItemDaoImp();
				boolean flag=false;
				item=itemDaoImp.queryItemById(itemId);//通过id查询活动
				System.out.println("根据id查到的活动：item"+item);
				if(item!=null){
					path="admin_event_detail.jsp?pageChildren=event";
				}
				else{
					errors.add("用户名或密码错误，请确认后在登录");
				}
			}
			
			req.setAttribute("Item",item);
		}
		
//		查看单位用户子页面
		if("groupUser".equals(pageChildren)){
			System.out.println("点击了单位用户");
			int teamId=0;		//团队的id
			Team team =new Team();
			TeamDaoImp teamDaoImp=new TeamDaoImp();
//			设置跳转路径
			path="admin_index_group.jsp?pageChildren=groupUser";
			String id=req.getParameter("itemId");
			if(id!=null&&!"".equals(id))
				teamId=Integer.parseInt(id);
			if(teamId!=0){
				//给person赋值
//				boolean flag=false;
				team=teamDaoImp.queryTeamById(teamId);//根据id查询团队
				if(team!=null){
					path="admin_group.jsp?pageChildren=groupUser";
				}
				else{
					errors.add("用户名或密码错误，请确认后在登录");
				}
			}
			req.setAttribute("error",errors);
			req.setAttribute("Item",team);
		}
		
//		查看个人子页面
	/*	if("personUser".equals(pageChildren)){
			//给person赋值
			Item item=new Item();
			ItemDaoImp itemDaoImp=new ItemDaoImp();
			
			boolean flag=false;
			item=itemDaoImp.queryItemById(itemId);//调用方法判断管理员身份
			if(item!=null){
				path="admin_event_detail.jsp";
			}
			else{
				errors.add("用户名或密码错误，请确认后在登录");
			}
			req.setAttribute("error",errors);
			req.setAttribute("Item",item);
		}
		*/
		}
		req.getRequestDispatcher(path).forward(req,resp);//跳转到path指定的页面
	}
}
