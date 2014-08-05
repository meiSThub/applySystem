package admin.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.bean.Manager;
import admin.dao.imp.ManagerDaoImp;
import admin.utils.FactoryDemo;

public class ItemCheckConf {
	public void doGet(HttpServletRequest req,HttpServletResponse resp )throws IOException,ServletException{
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp)throws IOException,ServletException{


//		req.getSession().setAttribute("managerName",manager.getManagerName());
//		req.getRequestDispatcher(path).forward(req,resp);
		
	}
}
