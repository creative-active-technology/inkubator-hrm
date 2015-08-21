package com.inkubator.hrm.dao.impl;

import java.util.ArrayList;
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
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.convert.Converter;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LeaveImplementationDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.Leave;
import com.inkubator.hrm.entity.LeaveImplementation;
import com.inkubator.hrm.web.model.ReportLeaveDataViewModel;
import com.inkubator.hrm.web.search.LeaveImplementationReportSearchParameter;
import com.inkubator.hrm.web.search.LeaveImplementationSearchParameter;
import com.inkubator.hrm.web.search.ReportLeaveDataSearchParameter;

import java.util.Date;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "leaveImplementationDao")
@Lazy
public class LeaveImplementationDaoImpl extends IDAOImpl<LeaveImplementation> implements LeaveImplementationDao {

    @Override
    public Class<LeaveImplementation> getEntityClass() {
        return LeaveImplementation.class;

    }

    @Override
    public List<LeaveImplementation> getByParam(LeaveImplementationSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(LeaveImplementationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LeaveImplementationSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("leave", "leave", JoinType.INNER_JOIN);

        if (StringUtils.isNotEmpty(parameter.getLeave())) {
            criteria.add(Restrictions.like("leave.name", parameter.getLeave(), MatchMode.ANYWHERE));
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
    public LeaveImplementation getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.setFetchMode("leaveImplementationDates", FetchMode.JOIN);
        return (LeaveImplementation) criteria.uniqueResult();
    }

    @Override
    public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId, Order orderable) {
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
    public LeaveImplementation getEntityByApprovalActivityNumberWithDetail(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.setFetchMode("leaveImplementationDates", FetchMode.JOIN);
        return (LeaveImplementation) criteria.uniqueResult();
    }

    @Override
    public List<LeaveImplementation> getReportByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("leave", "leave", JoinType.INNER_JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getReportTotalByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchReportByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId, Criteria criteria) {
        //get approval definition ids

        if (parameter.getStartDate() != null) {
            criteria.add(Restrictions.eq("startDate", parameter.getStartDate()));
        }

        if (parameter.getEndDate() != null) {
            criteria.add(Restrictions.eq("endDate", parameter.getEndDate()));
        }

        if (StringUtils.isNotEmpty(parameter.getApprovalStatus())) {
            if (!activityNumbers.isEmpty()) {

                criteria.add(Restrictions.in("approvalActivityNumber", activityNumbers));
            } else {
                criteria.add(Restrictions.eq("approvalActivityNumber", "0"));
            }
        }

        if (empDataId != 0L) {
            criteria.add(Restrictions.eq("empData.id", empDataId));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LeaveImplementation> getReportHistoryByParam(LeaveImplementationReportSearchParameter parameter, List<String> activityNumbers, Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportByParam(parameter, activityNumbers, empDataId, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<LeaveImplementation> getAllDataByEmpDataId(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        return criteria.list();
    }

    @Override
    public LeaveImplementation getByEmpStardDateEndDate(long empId, Date doDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.le("startDate", doDate));
        criteria.add(Restrictions.ge("endDate", doDate));
        return (LeaveImplementation) criteria.uniqueResult();

    }
    
    @Override
	public List<LeaveImplementation> getListByStartDateBetweenDateAndEmpId(Long empDataId,	Date dateFrom, Date dateUntill) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
        criteria.setFetchMode("empData", FetchMode.JOIN);        
        criteria.setFetchMode("temporaryActing", FetchMode.JOIN);
        criteria.setFetchMode("temporaryActing.bioData", FetchMode.JOIN);
        criteria.setFetchMode("leave", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));  
        criteria.add(Restrictions.ge("startDate", dateFrom));
        criteria.add(Restrictions.le("startDate", dateUntill));
        return criteria.list();
	}

    @Override
    public Long getCurrentMaxId() {
	Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
    return (Long) criteria.setProjection(Projections.max("id")).uniqueResult();
    }


	@Override
	public List<ReportLeaveDataViewModel> getAllDataLeaveReport(ReportLeaveDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		 final StringBuilder query = new StringBuilder("select leaveImplementationDate.id AS id,");
	        query.append(" leaveImplementationDate.actualDate AS leaveDate,");
	        query.append(" leaveImplementation.id AS leaveImplementationId,");
	        query.append(" empData.nik AS nik,");
	        query.append(" bioData.firstName AS firstName,");
	        query.append(" bioData.lastName AS lastName,");
	        query.append(" leave.id AS leaveId,");
	        query.append(" leave.name AS leaveName,");
	        query.append(" leaveImplementation.numberFilling AS numberFilling,");
	        query.append(" leaveImplementation.approvalActivityNumber AS activityNumber");
	        query.append(" FROM LeaveImplementationDate leaveImplementationDate");
	        query.append(" INNER JOIN leaveImplementationDate.leaveImplementation leaveImplementation");
	        query.append(" INNER JOIN leaveImplementation.leave leave");
	        query.append(" INNER JOIN leaveImplementation.empData empData");
	        query.append(" INNER JOIN empData.bioData bioData ");
	        query.append(" INNER JOIN empData.jabatanByJabatanId jabatanByJabatanId");
	        query.append(" INNER JOIN jabatanByJabatanId.department department");
	        query.append(" INNER JOIN jabatanByJabatanId.golonganJabatan golonganJabatan ");
	        
	        
	        //Implement Filter
	        query.append(doFilterLeaveReport(parameter));
	        List<Long> listDepartmentId = Lambda.extract(parameter.getListDepartment(), Lambda.on(Department.class).getId());
	        
	        query.append(" ORDER BY  "  + getRealFieldLeaveReport(orderable.getPropertyName()) + (!orderable.isAscending() ? " DESC" : "  "));
	        
	        return getCurrentSession().createQuery(query.toString())
	        		.setParameter("startDate", parameter.getStartDate())
	        		.setParameter("endDate", parameter.getEndDate())
                    .setParameterList("listDepartmentId", listDepartmentId)
                    .setParameterList("listGolJabatan", parameter.getListGolJab())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    
                    .setResultTransformer(Transformers.aliasToBean(ReportLeaveDataViewModel.class))
                    .list();
	        
	}
	

	@Override
	public Long getTotalLeaveDataReport(ReportLeaveDataSearchParameter parameter) {
		
		final StringBuilder query = new StringBuilder("SELECT COUNT(*)  ");
        query.append(" FROM LeaveImplementationDate leaveImplementationDate");
        query.append(" INNER JOIN leaveImplementationDate.leaveImplementation leaveImplementation");
        query.append(" INNER JOIN leaveImplementation.leave leave");
        query.append(" INNER JOIN leaveImplementation.empData empData");
        query.append(" INNER JOIN empData.bioData bioData ");
        query.append(" INNER JOIN empData.jabatanByJabatanId jabatanByJabatanId");
        query.append(" INNER JOIN jabatanByJabatanId.department department");
        query.append(" INNER JOIN jabatanByJabatanId.golonganJabatan golonganJabatan ");
        
        //Implement Filter
        query.append(doFilterLeaveReport(parameter));
        List<Long> listDepartmentId = Lambda.extract(parameter.getListDepartment(), Lambda.on(Department.class).getId());
      
        return (Long) getCurrentSession().createQuery(query.toString())
        		.setParameter("startDate", parameter.getStartDate())
        		.setParameter("endDate", parameter.getEndDate())
                .setParameterList("listDepartmentId", listDepartmentId)
                .setParameterList("listGolJabatan", parameter.getListGolJab())
                .uniqueResult();
        
	}
	
	private String doFilterLeaveReport( ReportLeaveDataSearchParameter  parameter){
		StringBuilder query = new StringBuilder();
		
		query.append(" WHERE leaveImplementationDate.actualDate >= :startDate AND  leaveImplementationDate.actualDate <= :endDate ");
		
		if(!parameter.getListDepartment().isEmpty()){
			
			query.append(" AND department.id IN :listDepartmentId");
		}
		
		if(!parameter.getListGolJab().isEmpty()){
			query.append(" AND golonganJabatan.code IN :listGolJabatan");
		}
		
		return query.toString();
	}
	
	public String getRealFieldLeaveReport(String orderField){
		String realOrderField = StringUtils.EMPTY;
	
		switch (orderField) {
		
		case "leaveDate":
			realOrderField = "leaveImplementationDate.actualDate";
			break;
			
		case "firstName":
			realOrderField = "bioData.firstName";
			break;
			
		case "leaveName":
			realOrderField = "leave.name";
			break;
			
		case "numberFilling":
			realOrderField = "leaveImplementation.numberFilling";
			break;

		default:
			break;
		}
		return realOrderField;
	}

}
