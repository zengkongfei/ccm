package com.ccm.api.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ccm.api.Constants;
import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.base.LabelValue;
import com.ccm.api.model.channel.Channel;
import com.ccm.api.model.hotel.Company;
import com.ccm.api.model.hotel.vo.ChainVO;
import com.ccm.api.model.hotel.vo.HotelVO;

/**
 * B2B user
 * 
 * @author Jenny Liao
 * 
 */
public class B2BUser extends BaseObject implements Serializable, UserDetails {

	private static final long serialVersionUID = 3832626162173359411L;

	private String userId;

	private Company company;// 相关的公司

	// private String companyId; //公司ID

	private String username; // required

	private String password; // required

	private String confirmPassword;

	private Employee employee;// 每个用户都有一个Employee
	
	private List<EmployeeI18n> employeeI18nList;// 每个用户都有一个EmployeeI18n集合

	private Date lastLoginTime; // 最后登录时间

	// 如下字段为Spring Security要求的必须字段
	private Integer version;

	private Set<Role> roles = new HashSet<Role>();

	private boolean enabled;

	private boolean accountExpired;

	private boolean accountLocked;

	private boolean credentialsExpired;

	private String salt;

	private HotelVO hotelvo;// 酒店

	private List<HotelVO> hotelVOs;// 用户分配的酒店
	private List<ChainVO> chainVOs;// 用户分配的酒店所关联的集团列表

	private String hotelId;// 酒店ID

	private String hid;// 淘宝酒店ID

	private String hotelName;// 酒店名称

	private Integer initStatus;

	private String alipayAccount;// 支付宝账号
	private Boolean hasInvoice;// 有无发票标志
	private Boolean hotelInvoiceType;// 发票类型:true-酒店住宿发票，false-其它发票
	private String otherInvoiceType;// 其它发票类型

	private String shopurl;// 用户淘宝店铺地址

	private Integer channelStatus;// 渠道酒店发布状态

	private Boolean sendMess = Boolean.FALSE;// 是否发送短信标志
	private String chainCode;// 渠道编码
	
	private String employeeId;
	
	private List<Channel> channels;// 用户分配的渠道
	
	private Boolean isLive;//账号激活
	private Boolean passwordIsInit= Boolean.TRUE;//是否是初始密码  默认是0：需要修改吗  1：不需要修改密码
	private Boolean islocak= Boolean.FALSE;//是否锁定
	
	private Boolean isAdmin;//是否为Admin,数据库无此字段，仅作前台标识
	
	private Boolean ishotelBlackList;
	
	public Boolean getIshotelBlackList() {
		return ishotelBlackList;
	}

	public void setIshotelBlackList(Boolean ishotelBlackList) {
		this.ishotelBlackList = ishotelBlackList;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * Default constructor - creates a new instance with no values set.
	 */
	public B2BUser() {
	}

	/**
	 * Create a new instance and set the username.
	 * 
	 * @param username
	 *            login name for user.
	 */
	public B2BUser(final String username) {
		this.username = username;
	}

	// 返回CompanyType
	public String getCompanyType() {
		if (company != null) {
			return company.getCompanyType();
		}
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getCompanyId() {

		if (company == null) {
			return null;
		}

		return company.getCompanyId();
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * Convert user roles to LabelValue objects for convenience.
	 * 
	 * @return a list of LabelValue objects with role information
	 */

	public List<LabelValue> getRoleList() {
		List<LabelValue> userRoles = new ArrayList<LabelValue>();

		if (this.roles != null) {
			for (Role role : roles) {
				// convert the user's roles to LabelValue Objects
				userRoles.add(new LabelValue(role.getName(), role.getName()));
			}
		}

		return userRoles;
	}

	/**
	 * Adds a role for the user
	 * 
	 * @param role
	 *            the fully instantiated role
	 */
	public void addRole(Role role) {
		getRoles().add(role);
	}

	/**
	 * @return GrantedAuthority[] an array of roles.
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */

	public Set<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();

		authorities.addAll(roles);
		return authorities;
	}

	public Integer getVersion() {
		return version;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public boolean isAccountNonExpired() {
		return !isAccountExpired();
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public boolean isAccountNonLocked() {
		return !isAccountLocked();
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 * @return true if credentials haven't expired
	 */

	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCompanyId(String companyId) {

		if (company != null) {
			company.setCompanyId(companyId);
		} else {
			company = new Company();
			company.setCompanyId(companyId);
		}

	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof B2BUser)) {
			return false;
		}

		final B2BUser user = (B2BUser) o;

		return !(username != null ? !username.equals(user.getUsername()) : user.getUsername() != null);

	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return (username != null ? username.hashCode() : 0);
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		ToStringBuilder sb = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("username", this.username).append("enabled", this.enabled).append("accountExpired", this.accountExpired).append("credentialsExpired", this.credentialsExpired).append("accountLocked", this.accountLocked);

		if (roles != null) {
			sb.append("Granted Authorities: ");

			int i = 0;
			for (Role role : roles) {
				if (i > 0) {
					sb.append(", ");
				}
				sb.append(role.toString());
				i++;
			}
		} else {
			sb.append("No Granted Authorities");
		}
		return sb.toString();
	}

	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean checkIsAdmin() {

		if (roles != null) {

			for (Role role : roles) {

				if (Constants.ADMIN_ROLE.equals(role.getName()) || Constants.COADMIN_ROLE.equals(role.getName())) {
					return true;

				}

			}
		}

		return false;

	}

	public List<HotelVO> getHotelVOs() {
		return hotelVOs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setHotelVOs(List hotelVOs) {
		this.hotelVOs = hotelVOs;
	}
	
	
	public List<ChainVO> getChainVOs() {
		return chainVOs;
	}

	public void setChainVOs(List<ChainVO> chainVOs) {
		this.chainVOs = chainVOs;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public Integer getInitStatus() {
		return initStatus;
	}

	public void setInitStatus(Integer initStatus) {
		this.initStatus = initStatus;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public Boolean getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Boolean hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	public Boolean getHotelInvoiceType() {
		return hotelInvoiceType;
	}

	public void setHotelInvoiceType(Boolean hotelInvoiceType) {
		this.hotelInvoiceType = hotelInvoiceType;
	}

	public String getOtherInvoiceType() {
		return otherInvoiceType;
	}

	public void setOtherInvoiceType(String otherInvoiceType) {
		this.otherInvoiceType = otherInvoiceType;
	}

	public String getShopurl() {
		return shopurl;
	}

	public void setShopurl(String shopurl) {
		this.shopurl = shopurl;
	}

	public Integer getChannelStatus() {
		return channelStatus;
	}

	public void setChannelStatus(Integer channelStatus) {
		this.channelStatus = channelStatus;
	}

	public Boolean getSendMess() {
		return sendMess;
	}

	public void setSendMess(Boolean sendMess) {
		this.sendMess = sendMess;
	}

	public String getChainCode() {
        return chainCode;
    }

    public void setChainCode(String chainCode) {
        this.chainCode = chainCode;
    }

    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getEmployeeId() {

		if (employee != null) {
			return employee.getEmployeeId();
		}

		return employeeId;
	}

	public void setEmployeeId(String employeeId) {

		if (employee != null) {
			employee.setEmployeeId(employeeId);
		} else {
			employee = new Employee();
			employee.setEmployeeId(employeeId);
		}
		this.employeeId = employeeId;
	}

	public HotelVO getHotelvo() {
		return hotelvo;
	}

	public void setHotelvo(HotelVO hotelvo) {
		this.hotelvo = hotelvo;
	}

	public List<EmployeeI18n> getEmployeeI18nList() {
		return employeeI18nList;
	}

	public void setEmployeeI18nList(List<EmployeeI18n> employeeI18nList) {
		this.employeeI18nList = employeeI18nList;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public void setChannels(List<Channel> channels) {
		this.channels = channels;
	}

	public Boolean getIsLive() {
		if(isLive==null){
			return false;
		}
		return isLive;
	}

	public void setIsLive(Boolean isLive) {
		this.isLive = isLive;
	}

	public Boolean getPasswordIsInit() {
		return passwordIsInit;
	}

	public void setPasswordIsInit(Boolean passwordIsInit) {
		this.passwordIsInit = passwordIsInit;
	}

	public Boolean getIslocak() {
		return islocak;
	}

	public void setIslocak(Boolean islocak) {
		this.islocak = islocak;
	}
	
}
