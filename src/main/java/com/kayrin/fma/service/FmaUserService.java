package com.kayrin.fma.service;

import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

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


	@Override
	public UserDetails loadUserByUsername(final String userid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private User buildUserForAuthentication(UserDetails user, List<GrantedAuthority> authorities) {
		return null;
	//	return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRoleAuthorizationInterceptor> userRoles) {

//		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//
//		// Build user's authorities
//		for (UserRole userRole : userRoles) {
//			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
//		}
//
//		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//
//		return Result;
		return null;
	}	
	
}
