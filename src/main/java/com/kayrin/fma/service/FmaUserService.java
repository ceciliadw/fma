package com.kayrin.fma.service;

import javax.security.auth.login.AccountLockedException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kayrin.fma.dao.AbstractHibernateDao;
import com.kayrin.fma.dao.FmaUserDao;
import com.kayrin.fma.model.FmaUser;



@Service("userDetailsService")
public class FmaUserService implements UserDetailsService{
	
	private static final Logger logger = LogManager.getLogger(FmaUserService.class.getName());
	
	@Autowired
	private FmaUserDao fmaUserDao; 
	

	public FmaUser findByUserid(final String userid){
		logger.entry();
		return fmaUserDao.findByUserid(userid);
	}
	
	public FmaUser create(final String userid, String displayName){
		logger.entry();
		FmaUser user = new FmaUser(userid, displayName); 
		FmaUser auditUser = new FmaUser(""+AbstractHibernateDao.SYSTEM_USER_ID, "system"); 
		user = fmaUserDao.saveOrUpdate(user, auditUser);
		logger.debug("new user.id = " + user.getId());
		return user;
	}
	
	public FmaUser loginUser(final String userid) throws AccountLockedException, UsernameNotFoundException{
		logger.entry();
		FmaUser user = findByUserid(userid); 
		if(user == null){
			throw new UsernameNotFoundException("No such user");
		}
		if(!user.isEnabled()){
			throw new AccountLockedException();
		}
		return user; 
	}


	@Override
	public UserDetails loadUserByUsername(final String userid) throws UsernameNotFoundException {		
		return fmaUserDao.findByUserid(userid);
	}
	
	

	
}
