package bean;

import anno.Column;
import anno.Entity;

/**
 * 寄件人信息
 * @author zhuqingwei
 *
 */
@Entity(tableName = "send")
public class SenderInfo extends BaseBean{
  
    /**电话号码**/
    @Column(columnName="phone",update = true)
	private String phone;
    /**手机号码**/
    @Column(columnName="mobile")
	private String mobile;
    @Column(columnName="company")
	private String company;
    @Column(columnName="name")
	private String name;
    @Column(columnName="address")
	private String address;

	

	public SenderInfo(String phone) {
		this.phone = phone;
	}
	public SenderInfo(String name,String mobile,String phone,String address,String company) {
		this.name = name;
		this.mobile = mobile;
		this.phone = phone;
		this.address = address;
		this.company = company;
	}

	public SenderInfo() {

	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SenderInfo other = (SenderInfo) obj;
		if (this.getAddress() == null) {
			if (other.getAddress() != null) {
				return false;
			}
		} else {
			if (!this.getAddress().equals(other.getAddress())) {
				return false;
			}
		}
		if (this.getCompany() == null) {
			if (other.getCompany() != null) {
				return false;
			}
		} else {
			if (!this.getCompany().equals(other.getCompany())) {
				return false;
			}
		}
		if (this.getCompany() == null) {
			if (other.getCompany() != null) {
				return false;
			}
		} else {
			if (!this.getCompany().equals(other.getCompany())) {
				return false;
			}
		}
		if (this.getMobile() == null) {
			if (other.getMobile() != null) {
				return false;
			}
		} else {
			if (!this.getMobile().equals(other.getMobile())) {
				return false;
			}
		}
		if (this.getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		} else {
			if (!this.getName().equals(other.getName())) {
				return false;
			}
		}
		if (this.getPhone() == null) {
			if (other.getPhone() != null) {
				return false;
			}
		} else {
			if (!this.getPhone().equals(other.getPhone())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		return "id:" + getId() + "\t mobile:" + getMobile() + "\t phone:" + getPhone()+ " \t company:" + getCompany() + "\t name:" + getName()+ "\t adress:" + getAddress();
	}

}
