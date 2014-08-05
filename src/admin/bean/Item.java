package admin.bean;

import java.util.Date;

public class Item {
	private int itemID;					//申报事项id编号
	private int teamID;					//申报方id
	private String itemTitle;			//申报事项标题
	private String itemIntro;			//申报事项的简介
	private Date itemStopApplyTime;		//停止报名时间
	private Date itemStartTime;			//活动开始时间
	private Date itemStopTime;			//活动截止时间
	private String itemAddress;			//活动的地点
	private int itemApplyMax;			//最多报名人数
	private boolean student;			//报名对象在校学生是否可以参与
	private boolean staff;				//报名对象教职工是否可以参与
	private String attentions;			//注意事项
	private String itemOrganizer;		//项目承办方
	private String itemResult;			//申报结果，通过审核中或者淘汰
	private Date itemSysDate;			//申报的系统时间
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public String getItemIntro() {
		return itemIntro;
	}
	public void setItemIntro(String itemIntro) {
		this.itemIntro = itemIntro;
	}
	public Date getItemStopApplyTime() {
		return itemStopApplyTime;
	}
	public void setItemStopApplyTime(Date itemStopApplyTime) {
		this.itemStopApplyTime = itemStopApplyTime;
	}
	public Date getItemStartTime() {
		return itemStartTime;
	}
	public void setItemStartTime(Date itemStartTime) {
		this.itemStartTime = itemStartTime;
	}
	public Date getItemStopTime() {
		return itemStopTime;
	}
	public void setItemStopTime(Date itemStopTime) {
		this.itemStopTime = itemStopTime;
	}
	public String getItemAddress() {
		return itemAddress;
	}
	public void setItemAddress(String addressItem) {
		this.itemAddress = addressItem;
	}
	public int getItemApplyMax() {
		return itemApplyMax;
	}
	public void setItemApplyMax(int itemApplyMax) {
		this.itemApplyMax = itemApplyMax;
	}
	public boolean isStudent() {
		return student;
	}
	public void setStudent(boolean student) {
		this.student = student;
	}
	public boolean isStaff() {
		return staff;
	}
	public void setStaff(boolean staff) {
		this.staff = staff;
	}
	public String getAttentions() {
		return attentions;
	}
	public void setAttentions(String attentions) {
		this.attentions = attentions;
	}
	public String getItemOrganizer() {
		return itemOrganizer;
	}
	public void setItemOrganizer(String itemOrganizer) {
		this.itemOrganizer = itemOrganizer;
	}
	public String getItemResult() {
		return itemResult;
	}
	public void setItemResult(String itemResult) {
		this.itemResult = itemResult;
	}
	public Date getItemSysDate() {
		return itemSysDate;
	}
	public void setItemSysDate(Date itemSysDate) {
		this.itemSysDate = itemSysDate;
	}
	public Item() {
		// TODO Auto-generated constructor stub
	}

}














