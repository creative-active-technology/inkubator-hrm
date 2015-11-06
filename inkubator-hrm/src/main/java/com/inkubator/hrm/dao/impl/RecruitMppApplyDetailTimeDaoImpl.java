package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitMppApplyDetailTimeDao;
import com.inkubator.hrm.entity.RecruitMppApplyDetailTime;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value = "recruitMppApplyDetailTimeDao")
@Lazy
public class RecruitMppApplyDetailTimeDaoImpl extends IDAOImpl<RecruitMppApplyDetailTime> implements RecruitMppApplyDetailTimeDao {

	@Override
	public Class<RecruitMppApplyDetailTime> getEntityClass() {
		return RecruitMppApplyDetailTime.class;
	}

	@Override
	public Boolean isMppDetailTimeAlreadyCreatedForMppDetailId(Long recruitMppApplyDetailId) {
		 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
         criteria.setFetchMode("recruitMppApplyDetail", FetchMode.JOIN);
         criteria.add(Restrictions.eq("recruitMppApplyDetail.id", recruitMppApplyDetailId));
         return !criteria.list().isEmpty();
	}

	@Override
	public List<RecruitMppApplyDetailTime> getListByMppApplyDetailId(Long mppApplyDetailId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppApplyDetail", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApplyDetail.recruitMppApply", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApplyDetai.jabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("recruitMppApplyDetail.id", mppApplyDetailId));
        criteria.addOrder(Order.asc("mppMonthApply"));
		return criteria.list();
	}

	@Override
	public void saveAndFlush(RecruitMppApplyDetailTime recruitMppApplyDetailTime) {
		Session session = getCurrentSession();
		session.save(recruitMppApplyDetailTime);
		session.flush();
	}

	@Override
	public List<RecruitMppApplyDetailTime> getListWithMppMonthApplyLaterThanParam(Long recruitMppApplyDetailId, Date mppMonthApply) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppApplyDetail", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApplyDetail.recruitMppApply", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApplyDetai.jabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("recruitMppApplyDetail.id", recruitMppApplyDetailId));
        criteria.add(Restrictions.gt("mppMonthApply", mppMonthApply));
        criteria.addOrder(Order.asc("mppMonthApply"));
		return criteria.list();
	}

}
