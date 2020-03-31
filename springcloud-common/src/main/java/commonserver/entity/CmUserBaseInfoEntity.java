package commonserver.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import commonserver.utils.UserSex;

@Alias(value = "cm_user_base_info")
public class CmUserBaseInfoEntity implements Serializable {
	private static final long serialVersionUID = -2854789499814767249L;
	private Long id;
	private String userEnName;
	private String userChName;
	private String userAccount;
	private String userPwd;
	private String enablePwd;
	private UserSex userSex;
	private Long userOrganId;
	private String userOrganName;
	private Long createdBy;
	private Long updatedBy;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserEnName() {
		return userEnName;
	}
	public void setUserEnName(String userEnName) {
		this.userEnName = userEnName;
	}
	public String getUserChName() {
		return userChName;
	}
	public void setUserChName(String userChName) {
		this.userChName = userChName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEnablePwd() {
		return enablePwd;
	}
	public void setEnablePwd(String enablePwd) {
		this.enablePwd = enablePwd;
	}
	public UserSex getUserSex() {
		return userSex;
	}
	public void setUserSex(UserSex userSex) {
		this.userSex = userSex;
	}
	public Long getUserOrganId() {
		return userOrganId;
	}
	public void setUserOrganId(Long userOrganId) {
		this.userOrganId = userOrganId;
	}
	public String getUserOrganName() {
		return userOrganName;
	}
	public void setUserOrganName(String userOrganName) {
		this.userOrganName = userOrganName;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
