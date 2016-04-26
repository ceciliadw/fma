package com.kayrin.fma.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.kayrin.fma.model.Role;

public class TestRoleDao extends AbstractTestDao {
	

	@Test
	@Ignore
	public void testCreate(){
		Role role = new Role("post_iso", "user can post ISO (In Search Of)"); 
		roleDao.save(role, getSuperAdminUser());
	}

	
	@Test
	public void testFindByRoleName(){
		Role role = roleDao.findByRoleName("post_iso");
		Assert.notNull(role);
	}
}
