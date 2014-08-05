package admin.utils;

import admin.dao.imp.ManagerDaoImp;

public class FactoryDemo {
	public static ManagerDaoImp getPersonDaoImpl(){
		return new ManagerDaoImp();
	}
//	public  static NotesDaoImpl getNotesDaoImpl(){
//		return new NotesDaoImpl();
//	}
}
