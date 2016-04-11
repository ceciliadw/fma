package com.kayrin.fma.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.kayrin.fma.model.AbstractAuditableModel;
import com.kayrin.fma.model.FmaUser;

public abstract class AbstractHibernateDao {
	public static final long SYSTEM_USER_ID = 0;
	
	@Resource
	private SessionFactory sessionFactory;
	

    protected Session getSession() {
    	return sessionFactory.getCurrentSession();
    }
	

    protected void updateAudit(AbstractAuditableModel auditable, FmaUser user) {
    	if (auditable != null) {
	    	auditable.setLastUpdatedBy(user.getId());
	    	auditable.setLastUpdatedDate(new Date());

	    	for(AbstractAuditableModel child : auditable.listChildren()) {
	    		updateAudit(child, user);
	    	}
    	}
    }

    protected void updateAuditParentOnly(AbstractAuditableModel auditable, FmaUser user) {
    	if (auditable != null) {
	    	auditable.setLastUpdatedBy(user.getId());
	    	auditable.setLastUpdatedDate(new Date());
    	}
    }	
}
