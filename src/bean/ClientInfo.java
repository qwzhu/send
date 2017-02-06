package bean;

import java.util.Date;

/**
 * 
 * @author zhuqingwei 记录客户端信息 暂时和用户信息放在一起。 一个用户只能在一个客户端登录
 */
public class ClientInfo {
	private String id;
	private String username;
	private String qq; // 暂时使用qq验证
	// private String email;邮箱验证
	private String mobile; // 手机号验证，暂时仅记录下来
	// private String shenfenzheng;//身份证照片； 暂时不使用,//TODO 管理员审核是否可用
	private String macAddress;
	private String ipAddress; //注册当时的网络地址
	private Date firstDate; //第一次登陆时间
	private Date lastValidDate; //最后截止有效时间
	private int status; // 0,免费使用   1续费使用 -1 强制停止 -2 已过期，建议续费
    public ClientInfo(MacInfo macInfo){
    	this.ipAddress = macInfo.getIp();
    	this.macAddress = macInfo.getMac();
    }
    public ClientInfo(){
    	
    }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	public Date getLastValidDate() {
		return lastValidDate;
	}
	public void setLastValidDate(Date lastValidDate) {
		this.lastValidDate = lastValidDate;
	}
	

}


