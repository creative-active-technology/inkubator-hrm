/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RecruitSelectionApplicantInitialDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni, Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantInitialDao")
@Lazy
public class RecruitSelectionApplicantInitialDaoImpl extends IDAOImpl<RecruitSelectionApplicantInitial> implements RecruitSelectionApplicantInitialDao {

    @Override
    public Class<RecruitSelectionApplicantInitial> getEntityClass() {
        return RecruitSelectionApplicantInitial.class;
    }

	@Override
	public Long getTotalApplicantByRecruitHireApplyId(Long recruitHireApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("recruitHireApply", "recruitHireApply", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("recruitHireApply.id", recruitHireApplyId));
        Long result = criteria.setProjection(Projections.rowCount()).uniqueResult() == null ? 0l : (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
        return result;
	}

	@Override
	public List<RecruitmentScheduleSettingViewModel> getByParamForRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter,
			int firstResult, int maxResults, Order orderable) {
		final StringBuilder query = new StringBuilder("SELECT  recruitHireApply.id AS id,");
		query.append(" jabatan.id AS jabatanId,");
        query.append(" jabatan.name AS jabatanName,");
        query.append(" recruitMppPeriod.id AS mppPeriodId,");
        query.append(" COUNT(recruitSelectionApplicantInitial.id) AS totalApplicant,");
        query.append(" recruitHireApply.candidateCountRequest AS candidateCountRequest,");
        query.append(" recruitMppPeriod.periodeStart AS startMppPeriodDate,");
        query.append(" recruitMppPeriod.periodeEnd as endMppPeriodDate");
        query.append(" FROM RecruitSelectionApplicantInitial recruitSelectionApplicantInitial");
        query.append(" INNER JOIN recruitSelectionApplicantInitial.recruitApplicant recruitApplicant");
        query.append(" INNER JOIN recruitSelectionApplicantInitial.recruitHireApply recruitHireApply");
        query.append(" INNER JOIN recruitHireApply.jabatan jabatan");
        query.append(" INNER JOIN recruitHireApply.recruitMppPeriod recruitMppPeriod");
        query.append(this.setWhereQueryRecruitmentScheduleSettingByParam(searchParameter));
        query.append(" GROUP BY recruitHireApply.id ");
        query.append(" ORDER BY " + orderable);
        
       
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryRecruitmentScheduleSettingByParam(hbm, searchParameter);
        
        return 	hbm.setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(RecruitmentScheduleSettingViewModel.class))
                .list();
        
	}

	@Override
	public Long getTotalByParamforRecruitmentScheduleSetting(RecruitmentScheduleSettingSearchParameter searchParameter) {
		
		final StringBuilder query = new StringBuilder("SELECT COUNT(DISTINCT recruitHireApply.id) ");       
		query.append(" FROM RecruitSelectionApplicantInitial recruitSelectionApplicantInitial");
		query.append(" INNER JOIN recruitSelectionApplicantInitial.recruitApplicant recruitApplicant");
		query.append(" INNER JOIN recruitSelectionApplicantInitial.recruitHireApply recruitHireApply");
		query.append(" INNER JOIN recruitHireApply.jabatan jabatan");
		query.append(" INNER JOIN recruitHireApply.recruitMppPeriod recruitMppPeriod");
		
		query.append(this.setWhereQueryRecruitmentScheduleSettingByParam(searchParameter));
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryRecruitmentScheduleSettingByParam(hbm, searchParameter);
        
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	 private String setWhereQueryRecruitmentScheduleSettingByParam(RecruitmentScheduleSettingSearchParameter parameter) {
	    	StringBuffer whereQuery = new StringBuffer();    	
	    	Boolean isAlreadyHaveParameter = Boolean.FALSE;
	    	
	    	if(parameter.getCandidateStatusId() != null){
	    		
	    		//Jika Career Candidate nya tidak sama dengan HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL_AND_EXTERNAL, mka filter sesuai dengan jenis yang dipilih
	    		if(parameter.getCandidateStatusId() != HRMConstant.RECRUIT_APPLICANT_CAREER_CANDIDATE_INTERNAL_AND_EXTERNAL){
	    			whereQuery.append(" WHERE recruitApplicant.careerCandidate = :careerCandidate ");
		    		isAlreadyHaveParameter = Boolean.TRUE;
	    		}
	    		
	    	}
	    	
	    	if(!parameter.getListJabatanOnSelectedMppApply().isEmpty()){
	    		if(isAlreadyHaveParameter){
	    			whereQuery.append(" AND jabatan.id IN :listJabatanOnSelectedMppApply ");
	    		}else{
	    			whereQuery.append(" WHERE jabatan.id IN :listJabatanOnSelectedMppApply ");
	    		}
	    	}
	       
	        return whereQuery.toString();
	    }
	    
	    private Query setValueQueryRecruitmentScheduleSettingByParam(Query hbm, RecruitmentScheduleSettingSearchParameter parameter){    	
	    	for(String param : hbm.getNamedParameters()){
	    		if(StringUtils.equals(param, "careerCandidate")){
	    			hbm.setParameter("careerCandidate",  parameter.getCandidateStatusId());
	    		} else if(StringUtils.equals(param, "listJabatanOnSelectedMppApply")){
	    			hbm.setParameterList("listJabatanOnSelectedMppApply", parameter.getListJabatanOnSelectedMppApply());
	    		} 
	    	}    	
	    	return hbm;
	    }

		@Override
		public List<RecruitSelectionApplicantInitial> getListByRecruitHireApplyId(Long recruitHireApplyId) {
			Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
	        criteria.createAlias("recruitHireApply", "recruitHireApply", JoinType.INNER_JOIN);
			criteria.createAlias("recruitApplicant", "recruitApplicant", JoinType.INNER_JOIN);
	        criteria.createAlias("recruitApplicant.bioData", "bioData", JoinType.INNER_JOIN);
	        criteria.add(Restrictions.eq("recruitHireApply.id", recruitHireApplyId));
	        return criteria.list();
		}
}
