package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RmbsApplicationDisbursementDao;
import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.web.search.RmbsDisbursementSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "rmbsApplicationDisbursementDao")
@Lazy
public class RmbsApplicationDisbursementDaoImpl extends IDAOImpl<RmbsApplicationDisbursement> implements RmbsApplicationDisbursementDao {

	@Override
	public Class<RmbsApplicationDisbursement> getEntityClass() {
		// TODO Auto-generated method stub
		return RmbsApplicationDisbursement.class;
	}

	@Override
	public List<RmbsApplicationDisbursement> getByParam(RmbsDisbursementSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}

	@Override
	public Long getTotalByParam(RmbsDisbursementSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	private void doSearchByParam(RmbsDisbursementSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("rmbsDisbursement", "rmbsDisbursement");
		criteria.createAlias("rmbsApplication", "rmbsApplication");
		criteria.createAlias("rmbsApplication.empData", "empData");
		criteria.createAlias("empData.bioData", "bioData");
		criteria.createAlias("rmbsApplication.rmbsType", "rmbsType");
		
		if (parameter.getEmpId() != null) {
			criteria.add(Restrictions.eq("empData.id", parameter.getEmpId()));
		}
		if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
			criteria.add(Restrictions.like("empData.nik", parameter.getEmpNik(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(parameter.getEmpName())) {
			criteria.add(Restrictions.like("bioData.combineName", parameter.getEmpName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(parameter.getRmbsTypeName())) {
			criteria.add(Restrictions.like("rmbsType.name", parameter.getRmbsTypeName(), MatchMode.ANYWHERE));
		}
		if (StringUtils.isNotEmpty(parameter.getRmbsApplicationCode())) {
			criteria.add(Restrictions.like("rmbsApplication.code", parameter.getRmbsApplicationCode(), MatchMode.ANYWHERE));
		}
		if (parameter.getDisbursementDate() != null){
			criteria.add(Restrictions.eq("rmbsDisbursement.disbursementDate", parameter.getDisbursementDate()));
		}
		
		criteria.add(Restrictions.isNotNull("id"));
	}

	

}
