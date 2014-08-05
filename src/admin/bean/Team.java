package admin.bean;

public class Team {
	private int teamId;  			//团队账号
	private String teamPass;  		//密码	
	private String teamName; 		//团队名称  
	private String teamPrincipal; 	//团队负责人	
	private String teamTel;  		//联系电话
	private String teamAddress; 	//联系地址
	private String teamEmail;   		//电子邮件
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamPass() {
		return teamPass;
	}
	public void setTeamPass(String teamPass) {
		this.teamPass = teamPass;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamPrincipal() {
		return teamPrincipal;
	}
	public void setTeamPrincipal(String teamPrincipal) {
		this.teamPrincipal = teamPrincipal;
	}
	public String getTeamTel() {
		return teamTel;
	}
	public void setTeamTel(String teamTel) {
		this.teamTel = teamTel;
	}
	public String getTeamAddress() {
		return teamAddress;
	}
	public void setTeamAddress(String teamAddress) {
		this.teamAddress = teamAddress;
	}
	public String getTeamEmail() {
		return teamEmail;
	}
	public void setTeamEmail(String teamEmail) {
		this.teamEmail = teamEmail;
	}
}
