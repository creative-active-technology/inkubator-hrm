package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitVacancyAdvertisementDao;
import com.inkubator.hrm.entity.RecruitVacancyAdvertisement;
import com.inkubator.hrm.web.search.VacancyAdvertisementSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository (value = "recruitVacancyAdvertisementDao")
@Lazy
public class RecruitVacancyAdvertisementDaoImpl extends IDAOImpl<RecruitVacancyAdvertisement> implements RecruitVacancyAdvertisementDao {

	@Override
	public Class<RecruitVacancyAdvertisement> getEntityClass() {
		return RecruitVacancyAdvertisement.class;
	}

	@Override
	public List<RecruitVacancyAdvertisement> getByParam(VacancyAdvertisementSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("recruitVacancyAdvertisementDetails", FetchMode.JOIN);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		this.doSearchByParam(parameter, criteria);
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(VacancyAdvertisementSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(VacancyAdvertisementSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("advertisementMedia", "advertisementMedia", JoinType.INNER_JOIN);
        criteria.createAlias("advertisementMedia.recruitAdvertisementCategory", "recruitAdvertisementCategory", JoinType.INNER_JOIN);    

        if (StringUtils.isNotEmpty(parameter.getVacancyAdvertisementCode())) {  
        	criteria.add(Restrictions.like("vacancyAdvCode", parameter.getVacancyAdvertisementCode(),MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getAdvertisementMediaName())) {
        	criteria.add(Restrictions.like("advertisementMedia.name", parameter.getAdvertisementMediaName(),MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getAdvertisementCategoryName())) {
        	criteria.add(Restrictions.like("recruitAdvertisementCategory.name", parameter.getAdvertisementCategoryName(),MatchMode.ANYWHERE));
        }
    }

	@Override
	public RecruitVacancyAdvertisement getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("advertisementMedia", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails.hireApply", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails.hireApply.jabatan", FetchMode.JOIN);
		return (RecruitVacancyAdvertisement) criteria.uniqueResult();
	}
	
	@Override
    public RecruitVacancyAdvertisement getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", approvalActivityNumber));
        criteria.setFetchMode("advertisementMedia", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails.hireApply", FetchMode.JOIN);
		criteria.setFetchMode("recruitVacancyAdvertisementDetails.hireApply.jabatan", FetchMode.JOIN);
        return (RecruitVacancyAdvertisement) criteria.uniqueResult();
    }
	
	@Override
	public Long getCurrentMaxId(){
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
	}

	@Override
	public List<RecruitVacancyAdvertisement> getAllDataGreaterThanEffectiveDate(Date date) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.ge("effectiveDate", date));
		criteria.setFetchMode("advertisementMedia", FetchMode.JOIN);
		return criteria.list();
	}
	
	@Override
	public List<RecruitVacancyAdvertisement> getAllData() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setFetchMode("advertisementMedia", FetchMode.JOIN);
		return criteria.list();
	}

	
}
