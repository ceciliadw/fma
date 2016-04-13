package com.kayrin.fma.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kayrin.fma.model.FmaUser;
import com.kayrin.fma.model.Role;

@Repository
public class RoleDao extends AbstractHibernateDao{

	private static final Logger logger = LogManager.getLogger(RoleDao.class.getName());
	
	@Transactional(readOnly = true)
	public Role findByRoleName(String roleName) {
		logger.entry();
		Criteria crit = getSession().createCriteria(Role.class);
		crit.add(Restrictions.eq("roleName", roleName));
		Role role = (Role)crit.uniqueResult();
		logger.debug("role is = " + role);
		return role; 
	}	
	
	@Transactional(readOnly = false)
	public Role save(Role role, FmaUser auditUser){
		updateAudit(role, auditUser);
		getSession().save(role);		
		return role;
	}
}
