package com.inkubator.hrm.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulle> implements RecruitSelectionApplicantSchedulleDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulle> getEntityClass() {
        return RecruitSelectionApplicantSchedulle.class;
    }

	@Override
	public RecruitSelectionApplicantSchedulle getEntityByPkWithDetail(Long id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		criteria.setFetchMode("selectionSeries", FetchMode.JOIN);
		criteria.setFetchMode("hireApply", FetchMode.JOIN);
		criteria.setFetchMode("hireApply.jabatan", FetchMode.JOIN);
		criteria.setFetchMode("hireApply.recruitMppPeriod", FetchMode.JOIN); 
		return (RecruitSelectionApplicantSchedulle) criteria.uniqueResult();
	}

	@Override
	public Boolean isHireApplyAlreadyHaveSelectionSchedulle(Long hireApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("hireApply", "hireApply", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		return !criteria.list().isEmpty();
	}

	@Override
	public RecruitSelectionApplicantSchedulle getEntityWithDetailByHireApplyId(Long hireApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("hireApply", "hireApply", JoinType.INNER_JOIN);
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		criteria.setFetchMode("selectionSeries", FetchMode.JOIN);
		return (RecruitSelectionApplicantSchedulle) criteria.uniqueResult();
	}
	
	@Override
	public List<SelectionPositionPassedViewModel> getSelectionPositionPassedByParam(String parameter, int firstResults, int maxResults, Order orderable) {
		
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT scheduleId, positionId, positionName, candidateRequest, "
    			+ "SUM(CASE WHEN status='PASS' THEN 1 ELSE 0 END) AS candidatePassed, "
    			+ "SUM(CASE WHEN status='PASS' THEN maxScore ELSE 0 END) AS totalMaxScore, "
    			+ "SUM(CASE WHEN status='PASS' THEN minScore ELSE 0 END) AS totalMinScore "
    			+ "FROM ( "
    			+ "SELECT schedule.id AS scheduleId, jabatan.id AS positionId, jabatan.name AS positionName, hireApply.candidate_count_request AS candidateRequest, "
    			+ "CASE WHEN SUM(CASE WHEN scheduleRealization.id IS NULL OR scheduleRealization.status!='PASS' THEN 1 ELSE 0 END) = 0 THEN 'PASS' ELSE 'FAILED' END AS status, "
    			+ "MAX(scheduleRealization.scoring_point) AS maxScore, "
    			+ "MIN(scheduleRealization.scoring_point) AS minScore "
    			+ "FROM recruit_selection_applicant_schedulle AS schedule "
    			+ "JOIN recruit_hire_apply AS hireApply ON hireApply.id=schedule.hire_apply_id "
    			+ "JOIN jabatan AS jabatan ON jabatan.id = hireApply.jabatan_id "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail AS scheduleDetail ON scheduleDetail.schedulle_id = schedule.id "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail_realization AS scheduleRealization ON scheduleRealization.schedulle_detail_id = scheduleDetail.id "
    			+ "WHERE jabatan.name LIKE '%" + parameter + "%' "
    			+ "GROUP BY scheduleDetail.applicant_id "
    			+ ") AS temp "
    			+ "GROUP BY positionId "
    			+ "ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResults)
    			.setResultTransformer(Transformers.aliasToBean(SelectionPositionPassedViewModel.class));
    	return hbm.list(); 
	}

	@Override
	public Long getTotalSelectionPositionPassedByParam(String parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) "
    			+ "FROM ( "
    			+ "SELECT jabatan.id AS positionId, jabatan.name AS positionName, hireApply.candidate_count_request AS candidateRequest, "
    			+ "CASE WHEN SUM(CASE WHEN scheduleRealization.id IS NULL OR scheduleRealization.status!='PASS' THEN 1 ELSE 0 END) = 0  THEN 'PASS' ELSE 'FAILED' END AS status, "
    			+ "MAX(scheduleRealization.scoring_point) AS maxScore, "
    			+ "MIN(scheduleRealization.scoring_point) AS minScore "
    			+ "FROM recruit_selection_applicant_schedulle AS schedule "
    			+ "JOIN recruit_hire_apply AS hireApply ON hireApply.id=schedule.hire_apply_id "
    			+ "JOIN jabatan AS jabatan ON jabatan.id = hireApply.jabatan_id "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail AS scheduleDetail ON scheduleDetail.schedulle_id = schedule.id "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail_realization AS scheduleRealization ON scheduleRealization.schedulle_detail_id = scheduleDetail.id "
    			+ "WHERE jabatan.name LIKE '%" + parameter + "%' "
    			+ "GROUP BY scheduleDetail.applicant_id "
    			+ ") AS temp "
    			+ "GROUP BY positionId ");				    	
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());   
    	
    	BigInteger total = (BigInteger) hbm.uniqueResult();
    	return total != null ? total.longValue() : 0L; 
	}
	
	@Override
	public List<SelectionApplicantPassedViewModel> getSelectionApplicantPassedByParam(Long scheduleId, int firstResults, int maxResults, Order orderable) {
		
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT applicantId, applicantName, applicantCareerCandidate, maxScore, minScore "
    			+ "FROM ( "
    			+ "SELECT recruitApplicant.id AS applicantId, "
    			+ "ltrim(concat(concat(bioData.first_name, ' '), bioData.last_name)) AS applicantName, "
    			+ "recruitApplicant.career_candidate AS applicantCareerCandidate, "
    			+ "CASE WHEN SUM(CASE WHEN scheduleRealization.id IS NULL OR scheduleRealization.status!='PASS' THEN 1 ELSE 0 END) = 0 THEN 'PASS' ELSE 'FAILED' END AS status, "
    			+ "MAX(scheduleRealization.scoring_point) AS maxScore, "
    			+ "MIN(scheduleRealization.scoring_point) AS minScore "
    			+ "FROM recruit_selection_applicant_schedulle AS schedule "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail AS scheduleDetail ON scheduleDetail.schedulle_id = schedule.id "
    			+ "LEFT JOIN recruit_applicant AS recruitApplicant ON recruitApplicant.id = scheduleDetail.applicant_id "
    			+ "LEFT JOIN bio_data AS bioData ON bioData.id = recruitApplicant.bio_data_id "
    			+ "LEFT JOIN recruit_selection_applicant_schedulle_detail_realization AS scheduleRealization ON scheduleRealization.schedulle_detail_id = scheduleDetail.id "
    			+ "WHERE schedule.id= " + scheduleId + " "
    			+ "GROUP BY scheduleDetail.applicant_id "
    			+ ") AS temp "
    			+ "WHERE status = 'PASS' "
    			+ "ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResults)
    			.setResultTransformer(Transformers.aliasToBean(SelectionApplicantPassedViewModel.class));
    	return hbm.list(); 
	}

	@Override
	public Long getTotalSelectionApplicantPassedByParam(Long scheduleId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) "
    			+ "FROM ( "
    	    	+ "SELECT recruitApplicant.id AS applicantId, "
    	    	+ "ltrim(concat(concat(bioData.first_name, ' '), bioData.last_name)) AS applicantName, "
    	    	+ "recruitApplicant.career_candidate AS applicantCareerCandidate, "
    	    	+ "CASE WHEN SUM(CASE WHEN scheduleRealization.id IS NULL OR scheduleRealization.status!='PASS' THEN 1 ELSE 0 END) = 0 THEN 'PASS' ELSE 'FAILED' END AS status, "
    	    	+ "MAX(scheduleRealization.scoring_point) AS maxScore, "
    	    	+ "MIN(scheduleRealization.scoring_point) AS minScore "
    	    	+ "FROM recruit_selection_applicant_schedulle AS schedule "
    	    	+ "LEFT JOIN recruit_selection_applicant_schedulle_detail AS scheduleDetail ON scheduleDetail.schedulle_id = schedule.id "
    	    	+ "LEFT JOIN recruit_applicant AS recruitApplicant ON recruitApplicant.id = scheduleDetail.applicant_id "
    	    	+ "LEFT JOIN bio_data AS bioData ON bioData.id = recruitApplicant.bio_data_id "
    	    	+ "LEFT JOIN recruit_selection_applicant_schedulle_detail_realization AS scheduleRealization ON scheduleRealization.schedulle_detail_id = scheduleDetail.id "
    	    	+ "WHERE schedule.id= " + scheduleId + " "
    	    	+ "GROUP BY scheduleDetail.applicant_id "
    	    	+ ") AS temp "
    	    	+ "WHERE status = 'PASS' ");				    	
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());   
    	
    	BigInteger total = (BigInteger) hbm.uniqueResult();
    	return total != null ? total.longValue() : 0L; 
	}

}
