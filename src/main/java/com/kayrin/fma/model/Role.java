package com.kayrin.fma.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role  extends AbstractAuditableModel{

	private static final long serialVersionUID = 1L;
	
	private String roleName; 
	private String roleDescription;
	
	private Set<UserRole> userRoles; 
	
	public Role(){}
	
	public Role(String roleName, String roleDescription){
		this.roleDescription = roleDescription; 
		this.roleName = roleName; 
	}
	
	@Column(name = "role_name")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Column(name = "role_description")
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role", cascade=CascadeType.ALL)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
