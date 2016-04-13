package com.kayrin.fma.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.kayrin.fma.config.DatabaseTestConfig;
import com.kayrin.fma.model.FmaUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DatabaseTestConfig.class, loader=AnnotationConfigContextLoader.class)
public class AbstractTestDao {
	
	private static FmaUser superAdmin; 

	@Autowired
	protected FmaUserDao fmaUserDao; 
	
	@Autowired
	protected RoleDao roleDao; 
	

	public FmaUser getSuperAdminUser(){
		if(superAdmin == null){
			superAdmin = fmaUserDao.findByUserid("super-admin");
		}
		return superAdmin; 
	}
	
}
