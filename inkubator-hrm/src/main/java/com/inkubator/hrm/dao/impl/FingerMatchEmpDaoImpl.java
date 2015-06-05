package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.FingerMatchEmpDao;
import com.inkubator.hrm.entity.FingerMatchEmp;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "fingerMatchEmpDao")
@Lazy
public class FingerMatchEmpDaoImpl extends IDAOImpl<FingerMatchEmp> implements FingerMatchEmpDao {

	@Override
	public Class<FingerMatchEmp> getEntityClass() {
		return FingerMatchEmp.class;
		
	}

	@Override
	public List<FingerMatchEmp> getAllDataByNik(String nik) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("nik", nik));
		return criteria.list();
	}

	@Override
	public FingerMatchEmp getEntityByNikAndMachineId(String nik, Long machineId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("empData.nik", nik));
		criteria.add(Restrictions.eq("mecineFinger.id", machineId));
		return (FingerMatchEmp) criteria.uniqueResult();
	}

	
}
