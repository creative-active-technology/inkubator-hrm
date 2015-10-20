package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitApplicantDao")
@Lazy
public class RecruitApplicantDaoImpl extends IDAOImpl<RecruitApplicant>implements RecruitApplicantDao {

	@Override
	public Class<RecruitApplicant> getEntityClass() {
		
		return RecruitApplicant.class;
	}

	@Override
	public List<RecruitApplicant> getByParam(RecruitApplicantSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchByParam(parameter, criteria);
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalByParam(RecruitApplicantSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParam(RecruitApplicantSearchParameter parameter, Criteria criteria) {
		criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("bioData.city", "city", JoinType.LEFT_OUTER_JOIN);

        if (StringUtils.isNotEmpty(parameter.getName())) {  
        	Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(parameter.getEmail())) {
        	criteria.add(Restrictions.like("bioData.personalEmail", parameter.getEmail(),MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCityName())) {
        	criteria.add(Restrictions.like("city.cityName", parameter.getCityName(),MatchMode.ANYWHERE));
        }
    }

	@Override
	public RecruitApplicant getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("bioData", FetchMode.JOIN);
		criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.race", FetchMode.JOIN);
        criteria.setFetchMode("bioData.religion", FetchMode.JOIN);
        criteria.setFetchMode("bioData.nationality", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("bioData.dialect", FetchMode.JOIN);
        criteria.setFetchMode("klasifikasiKerja", FetchMode.JOIN);
        criteria.setFetchMode("businessType", FetchMode.JOIN);
        criteria.setFetchMode("institutionEducation", FetchMode.JOIN);
        criteria.setFetchMode("educationLevel", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisement", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisement.advertisementMedia", FetchMode.JOIN);
        criteria.setFetchMode("recruitApplicantSpecLists", FetchMode.JOIN);
		criteria.setFetchMode("recruitApplicantSpecLists.orgTypeOfSpecList", FetchMode.JOIN);
		criteria.setFetchMode("recruitApplicantSpecLists.orgTypeOfSpecList.orgTypeOfSpec", FetchMode.JOIN);
		return (RecruitApplicant) criteria.uniqueResult();
	}

	@Override
	public List<ApplicantViewModel> getAllDataGroupByEducationLevel() {
		
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT educationLevel.name AS name, "
    			+ "SUM(CASE WHEN recruitApplicant.careerCandidate=0 then 1 else 0 END) AS totalExternal, "
    			+ "SUM(CASE WHEN recruitApplicant.careerCandidate=1 then 1 else 0 END) AS totalInternal "
    			+ "FROM RecruitApplicant recruitApplicant "
    			+ "JOIN recruitApplicant.educationLevel educationLevel "
    			+ "GROUP BY educationLevel.id "
    			+ "ORDER BY educationLevel.level DESC "); 
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setResultTransformer(Transformers.aliasToBean(ApplicantViewModel.class));
    	return hbm.list(); 
	}

    @Override
    public List<RecruitApplicant> getByParamForReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitApplicantByParamforReport(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamforReportSearchRecruitment(ReportSearchRecruitmentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitApplicantByParamforReport(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    public void doSearchRecruitApplicantByParamforReport(ReportSearchRecruitmentSearchParameter searchParameter, Criteria criteria){
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.createAlias("klasifikasiKerja", "klasifikasiKerja", JoinType.INNER_JOIN);
        
        if(!searchParameter.getListEducationLevel().isEmpty()){
            criteria.add(Restrictions.in("educationLevel.id", searchParameter.getListEducationLevel()));
        }
        
        if(!searchParameter.getListKlasifikasiKerja().isEmpty()){
            criteria.add(Restrictions.in("klasifikasiKerja.id", searchParameter.getListKlasifikasiKerja()));
        }
        
        if (StringUtils.isNotEmpty(searchParameter.getAgeStart())) {
            criteria.add(Restrictions.ge("bioData.age", Integer.parseInt(searchParameter.getAgeStart())));
        }

        if (StringUtils.isNotEmpty(searchParameter.getAgeEnd())) {
            criteria.add(Restrictions.le("bioData.age", Integer.parseInt(searchParameter.getAgeEnd())));
        }
        
        if(StringUtils.isNotBlank(searchParameter.getGender())){
            criteria.add(Restrictions.eq("bioData.gender", Integer.parseInt(searchParameter.getGender())));
        }
        
        if(StringUtils.isNotBlank(searchParameter.getCareerCandidate())){
            criteria.add(Restrictions.eq("careerCandidate", Integer.parseInt(searchParameter.getCareerCandidate())));
        }
        
        criteria.add(Restrictions.isNotNull("id"));
        
    }
    
}
