package admin.control;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.bean.Manager;
import admin.dao.imp.ManagerDaoImp;
import admin.utils.FactoryDemo;


//判断管理员身份是否合法，合法的话就跳转到管理员主页
public class ManagerLoginConf extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse resp )throws IOException,ServletException{
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{
		//接收参数用户名和密码
		String name=req.getParameter("managerName");
		String password=req.getParameter("managerPass");
		
		String path="admin_login.html";//设置跳转页面
		//保存错误信息
		List errors=new ArrayList();
		//给person赋值
		Manager manager=new Manager();
		manager.setManagerName(name);
		manager.setManagerPass(password);
		ManagerDaoImp mdi=FactoryDemo.getPersonDaoImpl();
		
		boolean flag=false;
		flag=mdi.managerLogin(manager);//调用方法判断管理员身份
		if(flag){
			path="admin_index.jsp?pageChildren=event";
		}
		else{
			errors.add("用户名或密码错误，请确认后在登录");
		}
		
		req.setAttribute("error",errors);
		req.setAttribute("manager",manager);
		req.getSession().setAttribute("managerName",manager.getManagerName());//保存管理员信息
		req.getRequestDispatcher(path).forward(req,resp);//跳转到path指定的页面
		
	}
}
