package com.kayrin.fma.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kayrin.fma.model.FmaUser;
import com.kayrin.fma.model.UserRole;


@Repository
public class FmaUserDao extends AbstractHibernateDao{
	
	private static final Logger logger = LogManager.getLogger(FmaUserDao.class.getName());

	@Transactional(readOnly = true)
	public FmaUser findByUserid(String userid) {
		logger.entry();
		Criteria crit = getSession().createCriteria(FmaUser.class);
		crit.add(Restrictions.eq("fbUserId", userid));
		FmaUser user = (FmaUser)crit.uniqueResult();
		logger.debug("user is = " + user);
		return user; 		

	}	
	
	@Transactional(readOnly = false)
	public FmaUser saveOrUpdate(FmaUser user, FmaUser auditUser){
		updateAudit(user, auditUser);
		getSession().saveOrUpdate(user);		
		return user;
	}
	
	@Transactional(readOnly = false)
	public UserRole saveOrUpdateAssociation(UserRole userrole, FmaUser auditUser){
		updateAudit(userrole, auditUser);
		getSession().saveOrUpdate(userrole);		
		return userrole;
	}
}
