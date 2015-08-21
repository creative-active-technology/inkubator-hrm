package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDetailDao;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisementDetail;
import com.inkubator.hrm.web.search.VacancySearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitVacancyAdvertisementDetailDao")
@Lazy
public class RecruitVacancyAdvertisementDetailDaoImpl extends IDAOImpl<RecruitVacancyAdvertisementDetail> implements
		RecruitVacancyAdvertisementDetailDao {

	@Override
	public Class<RecruitVacancyAdvertisementDetail> getEntityClass() {
		return RecruitVacancyAdvertisementDetail.class;
	}

	@Override
	public void deleteByVacancyAdvertisementId(Long vacancyAdvertisementId){
		Query query = getCurrentSession().createQuery("DELETE FROM RecruitVacancyAdvertisementDetail temp WHERE temp.vacancyAdvertisement.id = :vacancyAdvertisementId")
				.setLong("vacancyAdvertisementId", vacancyAdvertisementId);
        query.executeUpdate();
	}
	
	@Override
	public List<RecruitVacancyAdvertisementDetail> getAllDataVacancyByParam(VacancySearchParameter parameter, int firstResult, int maxResults, Order orderable){
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
		doSearchVacancyByParam(parameter, criteria);
		criteria.createAlias("vacancyAdvertisement.advertisementMedia", "advertisementMedia", JoinType.LEFT_OUTER_JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
	}
	
	@Override
	public Long getTotalVacancyByParam(VacancySearchParameter parameter){
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchVacancyByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private Criteria doSearchVacancyByParam(VacancySearchParameter param, Criteria criteria) {
		criteria.createAlias("hireApply", "hireApply", JoinType.INNER_JOIN);
		criteria.createAlias("hireApply.jabatan", "jabatan", JoinType.INNER_JOIN);
		criteria.createAlias("vacancyAdvertisement", "vacancyAdvertisement", JoinType.INNER_JOIN);
		
        if (StringUtils.isNotEmpty(param.getJabatanName())) {
            criteria.add(Restrictions.like("jabatan.name", param.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (param.getEffectiveDate() != null) {
            criteria.add(Restrictions.eq("vacancyAdvertisement.effectiveDate", param.getEffectiveDate()));
        }

        return criteria;
    }

	@Override
	public RecruitVacancyAdvertisementDetail getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("vacancyAdvertisement", FetchMode.JOIN);		
		criteria.setFetchMode("vacancyAdvertisement.advertisementMedia", FetchMode.JOIN);
		criteria.setFetchMode("hireApply", FetchMode.JOIN);
		criteria.setFetchMode("hireApply.recruitMppPeriod", FetchMode.JOIN);
		criteria.setFetchMode("hireApply.jabatan", FetchMode.JOIN);
		criteria.setFetchMode("hireApply.employeeType", FetchMode.JOIN);
		return (RecruitVacancyAdvertisementDetail) criteria.uniqueResult();
	}
	
}
