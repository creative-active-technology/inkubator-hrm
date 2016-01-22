package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalPerformanceIndicatorJabatanDao;
import com.inkubator.hrm.entity.AppraisalPerformanceIndicatorJabatan;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalPerformanceIndicatorJabatanDao")
@Lazy
public class AppraisalPerformanceIndicatorJabatanDaoImpl extends IDAOImpl<AppraisalPerformanceIndicatorJabatan> implements AppraisalPerformanceIndicatorJabatanDao {

	@Override
	public Class<AppraisalPerformanceIndicatorJabatan> getEntityClass() {
		// TODO Auto-generated method stub
		return AppraisalPerformanceIndicatorJabatan.class;
	}

	@Override
	public List<AppraisalPerformanceIndicatorJabatan> getAllDataByJabatanIdFetchScoringIndex(Long jabatanId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("jabatan.id", jabatanId));
		criteria.setFetchMode("performanceIndicator", FetchMode.JOIN);
		criteria.setFetchMode("performanceIndicator.systemScoring", FetchMode.JOIN);
		criteria.setFetchMode("performanceIndicator.systemScoring.systemScoringIndexes", FetchMode.JOIN);
		return criteria.list();
	}

	@Override
	public void deleteByJabatanId(Long jabatanId) {
		Query query = getCurrentSession().createQuery("DELETE FROM AppraisalPerformanceIndicatorJabatan temp WHERE temp.jabatan.id = :jabatanId")
				.setLong("jabatanId", jabatanId);
        query.executeUpdate();
		
	}

}
