package com.kayrin.fma.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kayrin.fma.model.FmaUser;


@Repository
public class FmaUserDao extends AbstractHibernateDao{
	
	private static final Logger logger = LogManager.getLogger(FmaUserDao.class.getName());
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public FmaUser findByUserid(String userid) {
		logger.entry();
		List<FmaUser> users = new ArrayList<FmaUser>();

		users = getSession()
			.createQuery("from FmaUser where userid=?")
			.setParameter("userid", userid)
			.list();

		if (users.size() > 0) {
			logger.trace("user found {0}", userid);
			return users.get(0);
		} else {
			logger.trace("cannot find userid  {0} ", userid);
			return null;
		}

	}	
}
