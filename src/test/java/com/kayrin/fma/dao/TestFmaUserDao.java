package com.kayrin.fma.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.util.Assert;

import com.kayrin.fma.model.FmaUser;
import com.kayrin.fma.model.Role;
import com.kayrin.fma.model.UserRole;


public class TestFmaUserDao extends AbstractTestDao{


	
	@Test
	public void testFindByUserId(){
		FmaUser user = fmaUserDao.findByUserid("super-admin");
		Assert.notNull(user);
	}
	
	
	@Test
	@Ignore
	public void testCreate(){
		FmaUser user = new FmaUser("super-admin", "Super Administrator");	
		fmaUserDao.saveOrUpdate(user, getSuperAdminUser());
		Assert.notNull(user);
	}
	
	@Test
	@Ignore
	public void testUpdateRole(){
		FmaUser user =  fmaUserDao.findByUserid("my-admin-2"); 	
		Role role = roleDao.findByRoleName("post_iso");
		Role role2 = roleDao.findByRoleName("post_offering");
		Role role3 = roleDao.findByRoleName("nominated_offering");
		

		user.setUserRoles(new HashSet<UserRole>());
		user.addRole(role3);
		fmaUserDao.saveOrUpdate(user, getSuperAdminUser());
		Assert.notNull(user);
	}
}
