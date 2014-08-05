package admin.bean;

public class Individual {
	private int individual_Id;			//个人的账号
	private String individual_pass;		//密码
	private String individual_name;		//姓名
	private String individual_gender;	//性别
	private String individual_roles;	//角色，s代表学生，t代表老师
	private String IndDepartment;		//个人所属的院系
	private String Indclass;			//个人所属的专业班级
	private int individual_Te;			//个人的联系电话
	private String individual_Email;	//个人的电子邮箱
	
	public int getIndividual_Id() {
		return individual_Id;
	}
	public void setIndividual_Id(int individualId) {
		individual_Id = individualId;
	}
	public String getIndividual_pass() {
		return individual_pass;
	}
	public void setIndividual_pass(String individualPass) {
		individual_pass = individualPass;
	}
	public String getIndividual_name() {
		return individual_name;
	}
	public void setIndividual_name(String individualName) {
		individual_name = individualName;
	}
	public String getIndividual_gender() {
		return individual_gender;
	}
	public void setIndividual_gender(String individualGender) {
		individual_gender = individualGender;
	}
	public String getIndividual_roles() {
		return individual_roles;
	}
	public void setIndividual_roles(String individualRoles) {
		individual_roles = individualRoles;
	}
	public String getIndDepartment() {
		return IndDepartment;
	}
	public void setIndDepartment(String indDepartment) {
		IndDepartment = indDepartment;
	}
	public String getIndclass() {
		return Indclass;
	}
	public void setIndclass(String indclass) {
		Indclass = indclass;
	}
	public int getIndividual_Te() {
		return individual_Te;
	}
	public void setIndividual_Te(int individualTe) {
		individual_Te = individualTe;
	}
	public String getIndividual_Email() {
		return individual_Email;
	}
	public void setIndividual_Email(String individualEmail) {
		individual_Email = individualEmail;
	}
	
}
