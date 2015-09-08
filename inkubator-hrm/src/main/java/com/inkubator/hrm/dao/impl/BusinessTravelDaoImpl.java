package com.inkubator.hrm.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BusinessTravelDao;
import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BusinessTravelViewModel;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "businessTravelDao")
@Lazy
public class BusinessTravelDaoImpl extends IDAOImpl<BusinessTravel> implements BusinessTravelDao {

    @Override
    public Class<BusinessTravel> getEntityClass() {
        return BusinessTravel.class;

    }

    @Override
    public List<BusinessTravel> getByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
//        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
//        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
//        criteria.setFetchMode("empData", FetchMode.JOIN);
//        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(BusinessTravelSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(BusinessTravelSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);

        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(parameter.getBusinessTravelNumber())) {
            criteria.add(Restrictions.like("businessTravelNo", parameter.getBusinessTravelNumber(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getDestination())) {
            criteria.add(Restrictions.like("destination", parameter.getDestination(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public BusinessTravel getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("empData.golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public BusinessTravel getEntityByBusinessTravelNoWithDetail(String businessTravelNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByBusinessTravelNo(String businessTravelNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByBusinessTravelNoAndNotId(String businessTravelNo, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("businessTravelNo", businessTravelNo));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public BusinessTravel getEntityByApprovalActivityNumberWithDetail(String approvalActivityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", approvalActivityNumber));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return (BusinessTravel) criteria.uniqueResult();
    }

    @Override
    public List<BusinessTravel> getAllDataByEmpDataId(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.setFetchMode("travelZone", FetchMode.JOIN);
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BusinessTravel getByEmpIdAndDate(long empId, Date doDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empId));
        criteria.add(Restrictions.le("startDate", doDate));
        criteria.add(Restrictions.ge("endDate", doDate));
        return (BusinessTravel) criteria.uniqueResult();

    }
    
    @Override
	public List<BusinessTravel> getListByStartDateBetweenDateAndEmpIdAndNotOff(
			Long empDataId, Date dateFrom, Date dateUntill) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());		
        criteria.setFetchMode("empData", FetchMode.JOIN);       
        criteria.setFetchMode("travelType", FetchMode.JOIN);
        criteria.createAlias("travelType.attendanceStatus", "attendanceStatus", JoinType.INNER_JOIN);	   
        
        criteria.add(Restrictions.eq("empData.id", empDataId));  
        criteria.add(Restrictions.ge("startDate", dateFrom));
        criteria.add(Restrictions.le("startDate", dateUntill));
        criteria.add(Restrictions.ne("attendanceStatus.code", "OFF"));
        
        return criteria.list();
	}

	@Override
	public Long getTotalActualBusinessTravel(Date date, Long companyId) {
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

	@Override
	public List<BusinessTravelViewModel> getAllActivityByParam(BusinessTravelSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT approvalActivity.id AS approvalActivityId, " +
    			"businessTravel.id AS businessTravelId, " +
    			"empData.nik AS empNik, " +
    			"CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
    			"approvalActivity.approval_status AS approvalStatus, " +
    			"approvalActivity.pending_data AS jsonData, " +
    			"businessTravel.start_date AS startDate, " +
    			"businessTravel.end_date AS endDate, " +
    			"businessTravel.destination AS destination, " +
    			"businessTravel.business_travel_no AS businessTravelNo " +
    			"FROM approval_activity approvalActivity " +
    			"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
    			"LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " +
    			"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
    			"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
    			"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                "INNER JOIN company AS company ON department.company_id = company.id  " +
    			"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    			"LEFT JOIN business_travel AS businessTravel ON approvalActivity.activity_number = businessTravel.approval_activity_number " +
    			"WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +    			
    			"AND approvalActivity.approval_status != :approvalStatus " +
				"AND approvalDefinition.name = :appDefinitionName ");    	
    	selectQuery.append(this.setWhereQueryActivityByParam(parameter));
    	selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY " + orderable);
        
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(BusinessTravelViewModel.class));
    	hbm = this.setValueQueryActivityByParam(hbm, parameter);
    	
    	return hbm.list();
	}

	@Override
	public Long getTotalActivityByParam(BusinessTravelSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " +
    	    	"FROM approval_activity approvalActivity " +
    	    	"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
    	    	"LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " +
    	    	"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
    	    	"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
    	    	"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
    	        "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
    	        "INNER JOIN company AS company ON department.company_id = company.id  " +
    	    	"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
    	    	"LEFT JOIN business_travel AS businessTravel ON approvalActivity.activity_number = businessTravel.approval_activity_number " +
    	    	"WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +    			
    	    	"AND approvalActivity.approval_status != :approvalStatus " +
    	    	"AND approvalDefinition.name = :appDefinitionName ");    	
    	selectQuery.append(this.setWhereQueryActivityByParam(parameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryActivityByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String setWhereQueryActivityByParam(BusinessTravelSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	     
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
        	whereQuery.append("AND (bioData.first_name LIKE :employee OR bioData.last_name LIKE :employee OR empData.nik LIKE :employee) ");
        }    
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
        	whereQuery.append("AND (requester.user_id = :userId " +
        			"OR (approver.user_id = :userId AND approvalActivity.approval_status = 0)) ");
        }
        
        if (parameter.getCompanyId() != null) {
        	whereQuery.append("AND company.id = :companyId ");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryActivityByParam(Query hbm, BusinessTravelSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "employee")){
    			hbm.setParameter("employee", "%" + parameter.getEmployee() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		} else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.BUSINESS_TRAVEL);
    		} else if(StringUtils.equals(param, "companyId")){
    			hbm.setParameter("companyId", parameter.getCompanyId());
    		}  else if(StringUtils.equals(param, "approvalStatus")){
    			hbm.setParameter("approvalStatus", HRMConstant.APPROVAL_STATUS_DELETED);
    		}
    	}    	
    	return hbm;
    }

	@Override
	public Boolean isDuplicateRequestDate(Date startRequest, Date endRequest, Long empDataId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		
		Conjunction conjStartRequest = Restrictions.conjunction();
		conjStartRequest.add(Restrictions.le("startDate", startRequest));
		conjStartRequest.add(Restrictions.ge("endDate", startRequest));
        
        Conjunction conjEndRequest = Restrictions.conjunction();
        conjEndRequest.add(Restrictions.le("startDate", endRequest));
        conjEndRequest.add(Restrictions.ge("endDate", endRequest));
        
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(conjStartRequest);
        disjunction.add(conjEndRequest);
        
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(disjunction);        
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult() > 0;
	}
}
