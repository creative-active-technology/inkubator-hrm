package com.inkubator.hrm.dao.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantPassedDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassedId;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.search.RecruitAgreementNoticeSearchParameter;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "recruitSelectionApplicantPassedDao")
@Lazy
public class RecruitSelectionApplicantPassedDaoImpl extends IDAOImpl<RecruitSelectionApplicantPassed>
        implements RecruitSelectionApplicantPassedDao {

    @Override
    public Class<RecruitSelectionApplicantPassed> getEntityClass() {
        // TODO Auto-generated method stub
        return RecruitSelectionApplicantPassed.class;
    }

    @Override
    public List<RecruitSelectionApplicantPassed> getAllWithPlacementStatus(String status) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("placementStatus", status));
        return criteria.list();
    }

	@Override
	public RecruitSelectionApplicantPassed getEntityByPk(RecruitSelectionApplicantPassedId id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", id));
		return (RecruitSelectionApplicantPassed) criteria.uniqueResult();
	}

	@Override
	public Long getTotalByHireApplyIdAndPlacementStatus(Long hireApplyId, String placementStatus) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("hireApply.id", hireApplyId));
		criteria.add(Restrictions.not(Restrictions.eq("placementStatus", placementStatus)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public List<RecruitSelectionApplicantPassedViewModel> getListSelectionPassedViewModelByParam(
			RecruitSelectionApplicantPassedSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
		
		final StringBuilder query = new StringBuilder("SELECT applicantPassed.id.applicantId AS id,");
        query.append(" applicant.id AS applicantId,");
        query.append(" CONCAT(bioData.firstName, ' ', bioData.lastName) AS applicantName,");
        query.append(" hireApply.id AS hireApplyId,");
        query.append(" jabatan.id AS jabatanId,");
        query.append(" jabatan.name AS jabatanName,");
        query.append(" department.id AS departmentId,");
        query.append(" department.departmentName AS departmentName,");
        query.append(" applicantPassed.latestTestDate as applyDate,");
        query.append(" employeeType.id as employeeTypeId ");
        query.append(" FROM RecruitSelectionApplicantPassed applicantPassed");
        query.append(" INNER JOIN applicantPassed.applicant applicant");
        query.append(" INNER JOIN applicantPassed.hireApply hireApply");
        query.append(" INNER JOIN hireApply.jabatan jabatan");
        query.append(" INNER JOIN jabatan.department department");
        query.append(" INNER JOIN applicantPassed.employeeType employeeType");
        query.append(" INNER JOIN applicant.bioData bioData");
        
        doSearchRecruitSelectionApplicantPassedByParam(searchParameter, query);
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = doSetValueSearchRecruitSelectionApplicantPassedByParam(searchParameter, hbm);
        hbm.setFirstResult(firstResult);
        hbm.setMaxResults(maxResults);
        return hbm.setResultTransformer(Transformers.aliasToBean(RecruitSelectionApplicantPassedViewModel.class)).list();
	}

	@Override
	public Long getTotalSelectionPassedViewModelByParam(
			RecruitSelectionApplicantPassedSearchParameter searchParameter) {
		final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");
        query.append(" FROM RecruitSelectionApplicantPassed applicantPassed");
        query.append(" INNER JOIN applicantPassed.applicant applicant");
        query.append(" INNER JOIN applicantPassed.hireApply hireApply");
        query.append(" INNER JOIN hireApply.jabatan jabatan");
        query.append(" INNER JOIN jabatan.department department");
        query.append(" INNER JOIN applicantPassed.employeeType employeeType");
        query.append(" INNER JOIN applicant.bioData bioData");
        doSearchRecruitSelectionApplicantPassedByParam(searchParameter, query);
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = doSetValueSearchRecruitSelectionApplicantPassedByParam(searchParameter, hbm);
        
        Long totalRecord = hbm.uniqueResult() == null ? 0l : Long.valueOf(hbm.uniqueResult().toString());
        return totalRecord;
	}
	
	private  void doSearchRecruitSelectionApplicantPassedByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, StringBuilder query){
		if(searchParameter.getCandidateName() != null){
			query.append(" WHERE bioData.firstName LIKE :candidateName ");
		}else if(searchParameter.getJabatanName() != null){
			query.append(" WHERE jabatan.name LIKE :jabatanName ");
		}else if(searchParameter.getDepartmentName() != null){
			query.append(" WHERE department.departmentName LIKE :departmentName ");
		}
    }
	
	private  Query doSetValueSearchRecruitSelectionApplicantPassedByParam(RecruitSelectionApplicantPassedSearchParameter searchParameter, Query query){
		if(searchParameter.getCandidateName() != null){
			query.setParameter("candidateName", "%" + searchParameter.getCandidateName() +  "%");
		}else if(searchParameter.getJabatanName() != null){
			query.setParameter("jabatanName", "%" + searchParameter.getJabatanName() +  "%");
		}else if(searchParameter.getDepartmentName() != null){
			query.setParameter("departmentName", "%" + searchParameter.getDepartmentName() +  "%");
		}
		
		return query;
    }

	@Override
	public RecruitSelectionApplicantPassed getEntityWithDetailByRecruitSelectionApplicantPassedId(RecruitSelectionApplicantPassedId id) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("applicant", FetchMode.JOIN);
        criteria.setFetchMode("hireApply", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("applicant.bioData", FetchMode.JOIN);
        criteria.setFetchMode("hireApply.jabatan", FetchMode.JOIN);
        criteria.setFetchMode("hireApply.jabatan.department", FetchMode.JOIN);
        criteria.setFetchMode("hireApply.jabatan.golonganJabatan", FetchMode.JOIN);
        return (RecruitSelectionApplicantPassed) criteria.uniqueResult();
	}

}
