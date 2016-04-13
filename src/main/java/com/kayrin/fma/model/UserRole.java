package com.kayrin.fma.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole extends AbstractAuditableModel{

	private static final long serialVersionUID = 1L;
	
	private FmaUser user;
	private Role role;
	
	public UserRole(){}
	
	public UserRole(FmaUser user, Role role){
		this.role = role; 
		this.user = user; 
	}
	

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")  
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	} 

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fma_user_id")  	
	public FmaUser getUser() {
		return user;
	}

	public void setUser(FmaUser user) {
		this.user = user;
	}

}
