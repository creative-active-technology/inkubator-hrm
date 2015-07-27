package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PermitImplementationDao;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;

import java.util.Date;

/**
 *
 * @author Taufik
 */
@Repository(value = "permitImplementationDao")
@Lazy
public class PermitImplementationDaoImpl extends IDAOImpl<PermitImplementation> implements PermitImplementationDao {

    @Override
    public Class<PermitImplementation> getEntityClass() {
        return PermitImplementation.class;

    }

    @Override
    public List<PermitImplementation> getByParam(PermitImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);        
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(PermitImplementationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(PermitImplementationSearchParameter parameter, Criteria criteria) {
    	criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
    	criteria.createAlias("permitClassification", "permitClassification", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
    	
    	if (StringUtils.isNotEmpty(parameter.getPermit())) {            
            criteria.add(Restrictions.like("permitClassification.name", parameter.getPermit(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {            
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(parameter.getNumberFilling())) {
            criteria.add(Restrictions.like("numberFilling", parameter.getNumberFilling(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PermitImplementation getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return (PermitImplementation) criteria.uniqueResult();
    }

    @Override
    public List<PermitImplementation> getAllDataByEmpDataId(Long empDataId, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.addOrder(orderable);
        return criteria.list();
    }

    @Override
    public long getTotalByNumberFilling(String numberFilling) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public long getTotalByNumberFillingAndNotId(String numberFilling, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("numberFilling", numberFilling));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PermitImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
//        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);
        return (PermitImplementation) criteria.uniqueResult();
    }

    @Override
    public List<PermitImplementation> getReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportPermitHistoryByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getReportPermitHistoryTotalByParam(ReportPermitHistorySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportPermitHistoryByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReportPermitHistoryByParam(ReportPermitHistorySearchParameter parameter, Criteria criteria) {
    	criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
    	criteria.createAlias("jabatan.department", "department", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
    	criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
    	criteria.createAlias("permitClassification", "permitClassification", JoinType.INNER_JOIN);
    	
    	if (parameter.getStartDate() != null) {
            criteria.add(Restrictions.ge("startDate", parameter.getStartDate()));
        }

        if (parameter.getEndDate() != null) {
            criteria.add(Restrictions.le("startDate", parameter.getEndDate()));
        }
        
        if(!parameter.getListDepartment().isEmpty()){
        	criteria.add(Restrictions.in("department.id", parameter.getListDepartment()));
        }
        
        if(!parameter.getListGolJab().isEmpty()){
        	criteria.add(Restrictions.in("golonganJabatan.code", parameter.getListGolJab()));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PermitImplementation getByEmpStardDateEndDate(long empId, Date doDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.le("startDate", doDate));
        criteria.add(Restrictions.ge("endDate", doDate));
        return (PermitImplementation) criteria.uniqueResult();
    }
    
    @Override
	public List<PermitImplementation> getListByStartDateBetweenDateAndEmpId(Long empDataId, Date dateFrom, Date dateUntill) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
        criteria.setFetchMode("empData", FetchMode.JOIN);        
        criteria.setFetchMode("permitClassification", FetchMode.JOIN);        
        criteria.add(Restrictions.eq("empData.id", empDataId));  
        criteria.add(Restrictions.ge("startDate", dateFrom));
        criteria.add(Restrictions.le("startDate", dateUntill));
        return criteria.list();
	}
    
    @Override
	public Long getTotalActualPermit(Date date, Long companyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
        criteria.add(Restrictions.le("startDate", date));
        criteria.add(Restrictions.ge("endDate", date));
        criteria.add(Restrictions.eq("company.id", companyId));
        criteria.add(Restrictions.not(Restrictions.eq("empData.status", HRMConstant.EMP_TERMINATION)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public Long getCurrentMaxId() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
	}
}
