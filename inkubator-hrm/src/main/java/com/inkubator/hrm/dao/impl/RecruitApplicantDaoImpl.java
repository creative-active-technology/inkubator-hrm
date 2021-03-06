package com.inkubator.hrm.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitApplicantDao;
import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import com.inkubator.hrm.web.model.ApplicantAgeViewModel;
import com.inkubator.hrm.web.model.ApplicantRealizationViewModel;
import com.inkubator.hrm.web.model.ApplicantViewModel;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;
import com.inkubator.hrm.web.search.RecruitInitialSelectionSearchParameter;
import com.inkubator.hrm.web.search.ReportSearchRecruitmentSearchParameter;
import com.inkubator.hrm.web.search.SelectionApplicantRealizationSearchParameter;

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
	
	@Override
	public List<RecruitApplicant> getAllDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter, int firstResults, int maxResults, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		this.doSearchByParamWithDetail(parameter, criteria);
		criteria.addOrder(orderable);
        criteria.setFirstResult(firstResults);
        criteria.setMaxResults(maxResults);
		return criteria.list();
	}

	@Override
	public Long getTotalDataByParamWithDetail(RecruitInitialSelectionSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamWithDetail(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private void doSearchByParamWithDetail(RecruitInitialSelectionSearchParameter parameter, Criteria criteria) {
		DetachedCriteria listRecruitApplicant = DetachedCriteria.forClass(RecruitSelectionApplicantInitial.class)
                .setProjection(Property.forName("recruitApplicant.id"));
		criteria.add(Property.forName("id").notIn(listRecruitApplicant));
		criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("educationLevel", "educationLevel", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("recruitVacancyAdvertisementDetail", "recruitVacancyAdvertisementDetail", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("recruitVacancyAdvertisementDetail.hireApply", "hireApply", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("hireApply.jabatan", "jabatan", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("isActive", Boolean.TRUE));
		criteria.add(Restrictions.eq("isVerified", Boolean.TRUE));
		if(parameter.getJabatanId() != null && parameter.getJabatanId() != 0){
			criteria.add(Restrictions.eq("jabatan.id", parameter.getJabatanId()));
		}
		
		if(parameter.getKandidatId() != null && parameter.getKandidatId() != 0){
			criteria.add(Restrictions.eq("careerCandidate", parameter.getKandidatId()));
		}
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
        criteria.setFetchMode("recruitVacancyAdvertisementDetail", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisementDetail.hireApply", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisementDetail.hireApply.jabatan", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisementDetail.vacancyAdvertisement", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisementDetail.vacancyAdvertisement.advertisementMedia", FetchMode.JOIN);
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
    			+ "FROM recruit_applicant recruitApplicant "
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

	@Override
	public Long getTotalByCareerCandidateAndOrgTypeOfSpecId(Integer careerCandidate, Long specId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT COUNT(*) "
    			+ "FROM ( "
    			+ "SELECT recruitApplicant.* "
    			+ "FROM recruit_applicant recruitApplicant "
    			+ "JOIN recruit_applicant_spec_list specList on recruitApplicant.id=specList.applicant_id "
    			+ "JOIN org_type_of_spec_list orgTypeList on orgTypeList.id=specList.org_type_of_spec_list_id "
    			+ "JOIN org_type_of_spec orgTypeSpec on orgTypeSpec.id=orgTypeList.org_type_of_spec_id "
    			+ "WHERE orgTypeSpec.id = :specId AND recruitApplicant.career_candidate = :careerCandidate "
    			+ "GROUP BY recruitApplicant.id"
    			+ ") as temp "); 
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
    	hbm.setParameter("specId", specId);
    	hbm.setParameter("careerCandidate", careerCandidate);
    	BigInteger total = (BigInteger) hbm.uniqueResult();
    	return total != null ? total.longValue() : 0L; 
	}

	@Override
	public Long getTotalByVacancyAdvertisementDetailId(Long vacancyAdvertisementDetailId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("recruitVacancyAdvertisementDetail.id", vacancyAdvertisementDetailId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
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

	@Override
	public List<ApplicantRealizationViewModel> getSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter, int firstResults, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT DISTINCT recruitApplicant.id AS applicantId, "
    			+ "recruitSelectionApplicantSchedulle.id AS selectionScheduleId, "
    			+ "bioData.combineName AS name, "
    			+ "bioData.mobilePhone AS phone, "
    			+ "jabatanApply.name AS positionApply, "
    			+ "recruitApplicant.lastJabatan AS positionLast "
    			+ "FROM RecruitApplicant recruitApplicant "
    			+ "JOIN recruitApplicant.bioData bioData "
    			+ "JOIN recruitApplicant.recruitSelectionApplicantSchedulleDetails recruitSelectionApplicantSchedulleDetails "
    			+ "JOIN recruitSelectionApplicantSchedulleDetails.recruitSelectionApplicantSchedulle recruitSelectionApplicantSchedulle "
    			+ "JOIN recruitSelectionApplicantSchedulle.hireApply hireApply "
    			+ "JOIN hireApply.jabatan jabatanApply ");    	
    	selectQuery.append(this.getWhereQuerySelectionApplicantRealizationByParam(parameter));
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResults)
                	.setResultTransformer(Transformers.aliasToBean(ApplicantRealizationViewModel.class));
    	hbm = this.setValueQuerySelectionApplicantRealizationByParam(hbm, parameter);
    	
    	return hbm.list();
	}

	@Override
	public Long getTotalSelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT COUNT(DISTINCT recruitApplicant.id) "
    			+ "FROM RecruitApplicant recruitApplicant "
    	    	+ "JOIN recruitApplicant.bioData bioData "
    	    	+ "JOIN recruitApplicant.recruitSelectionApplicantSchedulleDetails recruitSelectionApplicantSchedulleDetails "
    	    	+ "JOIN recruitSelectionApplicantSchedulleDetails.recruitSelectionApplicantSchedulle recruitSelectionApplicantSchedulle "
    	    	+ "JOIN recruitSelectionApplicantSchedulle.hireApply hireApply "
    	    	+ "JOIN hireApply.jabatan jabatanApply ");     	
    	selectQuery.append(this.getWhereQuerySelectionApplicantRealizationByParam(parameter));
    	
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString());    	
    	hbm = this.setValueQuerySelectionApplicantRealizationByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String getWhereQuerySelectionApplicantRealizationByParam(SelectionApplicantRealizationSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();
    	
    	if (StringUtils.isNotEmpty(parameter.getName())) {
    		if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
    		whereQuery.append("bioData.combineName LIKE :empName ");
        }
    	
        if (StringUtils.isNotEmpty(parameter.getPhone())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("bioData.mobilePhone LIKE :phone ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getJabatanApply())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("jabatanApply.name LIKE :positionApply ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getJabatanLast())) {
        	if(StringUtils.isNotEmpty(whereQuery)){
    			whereQuery.append("AND ");
    		}
        	whereQuery.append("recruitApplicant.lastJabatan LIKE :positionLast ");
        }
        
        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : whereQuery.toString();
    }
    
    private Query setValueQuerySelectionApplicantRealizationByParam(Query hbm, SelectionApplicantRealizationSearchParameter parameter){
    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getName() + "%");
    		} else if(StringUtils.equals(param, "phone")){
    			hbm.setParameter("phone", "%" + parameter.getPhone() + "%");
    		} else if(StringUtils.equals(param, "positionApply")){
    			hbm.setParameter("positionApply", "%" + parameter.getJabatanApply() + "%");
    		} else if(StringUtils.equals(param, "positionLast")){
    			hbm.setParameter("positionLast", "%" + parameter.getJabatanLast() + "%");
    		} 
    	}
    	
    	return hbm;
    }

	@Override
	public RecruitApplicant getEntityByPkWithHireApplyDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("recruitVacancyAdvertisementDetail", FetchMode.JOIN);
        criteria.setFetchMode("recruitVacancyAdvertisementDetail.hireApply", FetchMode.JOIN);
        return (RecruitApplicant) criteria.uniqueResult();
	}

	
    
}
