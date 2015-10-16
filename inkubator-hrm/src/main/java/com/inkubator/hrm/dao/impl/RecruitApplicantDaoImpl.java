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
import com.inkubator.hrm.web.model.ApplicantAgeViewModel;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;

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
	public List<ApplicantViewModel> getDataChartEducationLevel() {
		
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
	public List<ApplicantViewModel> getDataChartJobClassification() {
		
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT name, "
    			+ "SUM(CASE WHEN career_candidate=0 THEN 1 ELSE 0 END) AS totalExternalDecimal, "
    			+ "SUM(CASE WHEN career_candidate=1 THEN 1 ELSE 0 END) AS totalInternalDecimal "
    			+ "FROM ( "
    			+ "SELECT orgTypeSpec.name AS name, recruitApplicant.career_candidate, recruitApplicant.id "
    			+ "FROM hrm.recruit_applicant recruitApplicant "
    			+ "JOIN recruit_applicant_spec_list specList on recruitApplicant.id=specList.applicant_id "
    			+ "JOIN org_type_of_spec_list orgTypeList on orgTypeList.id=specList.org_type_of_spec_list_id "
    			+ "JOIN org_type_of_spec orgTypeSpec on orgTypeSpec.id=orgTypeList.org_type_of_spec_id "
    			+ "GROUP BY recruitApplicant.id, name"
    			+ ") as temp "
    			+ "GROUP BY name "); 
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setResultTransformer(Transformers.aliasToBean(ApplicantViewModel.class));
    	return hbm.list(); 
	}

	@Override
	public ApplicantViewModel getDataChartWorkingExperience() {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(CASE WHEN careerCandidate=0 AND isFreshGraduate=true then 1 else 0 END) AS totalNotFreshGraduate, "
    			+ "SUM(CASE WHEN careerCandidate=0 AND isFreshGraduate=false then 1 else 0 END) AS totalFreshGraduate, "
    			+ "SUM(CASE WHEN careerCandidate=1 then 1 else 0 END) AS totalInternal "
    			+ "FROM RecruitApplicant "); 
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setResultTransformer(Transformers.aliasToBean(ApplicantViewModel.class));
    	return (ApplicantViewModel) hbm.uniqueResult();
	}
	
	@Override
	public List<ApplicantAgeViewModel> getDataChartAge() {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT CASE WHEN recruitApplicant.careerCandidate=1 THEN 'Internal' ELSE 'External' END AS candidate, "
    			+ "SUM(CASE WHEN bioData.age<25 AND bioData.gender=1 THEN 1 ELSE 0 END) AS maleAgeBelow25, "
    			+ "SUM(CASE WHEN bioData.age<25 AND bioData.gender=0 THEN 1 ELSE 0 END) AS femaleAgeBelow25, "
    			+ "SUM(CASE WHEN bioData.age>=25 AND bioData.age<=30 AND bioData.gender=1 THEN 1 ELSE 0 END) AS maleAgeBetween25And30, "
    			+ "SUM(CASE WHEN bioData.age>=25 AND bioData.age<=30 AND bioData.gender=0 THEN 1 ELSE 0 END) AS femaleAgeBetween25And30, "
    			+ "SUM(CASE WHEN bioData.age>30 AND bioData.gender=1 THEN 1 ELSE 0 END) AS maleAgeAbove30, "
    			+ "SUM(CASE WHEN bioData.age>30 AND bioData.gender=0 THEN 1 ELSE 0 END) AS femaleAgeAbove30 "
    			+ "FROM RecruitApplicant recruitApplicant "
    			+ "JOIN recruitApplicant.bioData  bioData "
    			+ "GROUP BY recruitApplicant.careerCandidate"); 
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setResultTransformer(Transformers.aliasToBean(ApplicantAgeViewModel.class));
    	return hbm.list();
	}

}
