package admin.bean;

import java.util.Date;

public class ApplyItem {
	private int applyItem_Id;			//报名事项的编号
	private int declareItem_Id;			//申报事项编号
	private int individual_Id;			//个人账号
	private String applyItemResult;		//报名结果
	private Date applySysDate;			//报名系统时间
	
	public int getApplyItem_Id() {
		return applyItem_Id;
	}
	public void setApplyItem_Id(int applyItemId) {
		applyItem_Id = applyItemId;
	}
	public int getDeclareItem_Id() {
		return declareItem_Id;
	}
	public void setDeclareItem_Id(int declareItemId) {
		declareItem_Id = declareItemId;
	}
	public int getIndividual_Id() {
		return individual_Id;
	}
	public void setIndividual_Id(int individualId) {
		individual_Id = individualId;
	}
	public String getApplyItemResult() {
		return applyItemResult;
	}
	public void setApplyItemResult(String applyItemResult) {
		this.applyItemResult = applyItemResult;
	}
	public Date getApplySysDate() {
		return applySysDate;
	}
	public void setApplySysDate(Date applySysDate) {
		this.applySysDate = applySysDate;
	}
}
