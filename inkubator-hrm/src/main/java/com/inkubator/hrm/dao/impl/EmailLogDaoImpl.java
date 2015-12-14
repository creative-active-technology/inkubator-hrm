package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EmailLogDao;
import com.inkubator.hrm.entity.EmailLog;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "emailLogDao")
@Lazy
public class EmailLogDaoImpl extends IDAOImpl<EmailLog> implements EmailLogDao {

	@Override
	public Class<EmailLog> getEntityClass() {
		return EmailLog.class;
	}

	@Override
	public List<EmailLog> getAllDataEmailNotSent() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("sentStatus", false));
		return criteria.list();
	}

}
