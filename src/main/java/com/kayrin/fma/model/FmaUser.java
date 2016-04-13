package com.kayrin.fma.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fma_user")
public class FmaUser extends AbstractAuditableModel{
	
	private static final long serialVersionUID = 1L;
	
	private String fbUserId; 
	private String displayName; 
	private String email; 
	private Date createdDate;
	
	private Set<UserRole> userRoles = new HashSet<>(); 
	
	public FmaUser(){}
	
	public FmaUser(String userid, String displayName){
		this.fbUserId = userid;
		this.displayName = displayName; 
		this.createdDate = new Date(); 
	}
	
	
	@Column(name = "fb_user_id")
	public String getFbUserId() {
		return fbUserId;
	}
	public void setFbUserId(String userid) {
		this.fbUserId = userid;
	}
	
	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade=CascadeType.ALL)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public void addRole(Role role){	
		this.userRoles.add(new UserRole(this, role));
	}


}
