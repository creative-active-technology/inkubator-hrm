/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpCareerHistoryDao;
import com.inkubator.hrm.entity.EmpCareerHistory;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.CareerTransitionInboxViewModel;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.CareerTransitionInboxSearchParameter;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpMutationParameter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "empCareerHistoryDao")
@Lazy
public class EmpCareerHistoryDaoImpl extends IDAOImpl<EmpCareerHistory> implements EmpCareerHistoryDao {

    @Override
    public Class<EmpCareerHistory> getEntityClass() {
        return EmpCareerHistory.class;
    }

    @Override
    public List<EmpCareerHistory> getEmployeeCareerByBioId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.addOrder(Order.asc("tglPenganngkatan"));
        return criteria.list();
    }

    @Override
    public EmpCareerHistory getByBioIdandStatus(long id, String status) {
        DetachedCriteria maxEvaluationScore = DetachedCriteria.forClass(getEntityClass())
                .setProjection(Property.forName("createdOn").max())
                .createAlias("bioData", "bio", JoinType.INNER_JOIN)
                .add(Restrictions.eq("bio.id", id))
                .add(Restrictions.eq("status", status));

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bio.id", id));
        criteria.add(Restrictions.eq("status", status));
        criteria.add(Property.forName("createdOn").eq(maxEvaluationScore));
        return (EmpCareerHistory) criteria.uniqueResult();
    }
    
     private void doSearchEmpRotasiByParamReport(ReportEmpMutationParameter searchParameter, Criteria criteria) {
         if (searchParameter.getStartDate() != null && searchParameter.getEndDate()!= null) {   
             Conjunction conjunction = Restrictions.conjunction();
             conjunction.add(Restrictions.ge("tglPenganngkatan", searchParameter.getStartDate()));
             conjunction.add(Restrictions.le("tglPenganngkatan", searchParameter.getEndDate()));
             criteria.add(conjunction);
        }
    }
    
    @Override
    public List<EmpCareerHistory> getByParamReport(ReportEmpMutationParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan", "jabatan", JoinType.INNER_JOIN);     
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
        
        DetachedCriteria maxTglPengangkatanQuery = DetachedCriteria.forClass(getEntityClass());
        ProjectionList proj = Projections.projectionList();
        proj.add(Projections.max("tglPenganngkatan"));
        proj.add(Projections.groupProperty("nik"));
        maxTglPengangkatanQuery.setProjection(proj);        
       
        criteria.add(Subqueries.propertiesIn(new String[] {"tglPenganngkatan","nik"}, maxTglPengangkatanQuery));
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        
        List<EmpCareerHistory> listEmpCareerHistorys = criteria.list(); 
        
        //Set Jabatan Lama/sebelumnya dari masing - masing record
        for(EmpCareerHistory ech : listEmpCareerHistorys){
             Criteria criteriaOldPosition = getCurrentSession().createCriteria(getEntityClass());
             criteriaOldPosition.setFetchMode("jabatan", FetchMode.JOIN);
             criteriaOldPosition.add(Restrictions.eq("nik", ech.getNik()));
             criteriaOldPosition.add(Restrictions.lt("tglPenganngkatan", ech.getTglPenganngkatan()));
             criteriaOldPosition.addOrder(Order.desc("tglPenganngkatan"));
             criteriaOldPosition.setMaxResults(1);
             EmpCareerHistory prevPosition = (EmpCareerHistory) criteriaOldPosition.uniqueResult();
             
             //jika sebelumnya dia sudah pernah menjabat di posisi lain maka set oldJabatan dengan posisi tersebut
             if(null != prevPosition){
                  ech.setJabatanOldCode(prevPosition.getJabatan().getCode());
                 ech.setJabatanOldName(prevPosition.getJabatan().getName());                
             }else{
                 ech.setJabatanOldCode("-");
             }
        }        
        
        return listEmpCareerHistorys;
    }

    @Override
    public Long getTotalEmpCareerHistoryDataByParamReport(ReportEmpMutationParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpRotasiByParamReport(searchParameter, criteria);
        DetachedCriteria maxTglPengangkatanQuery = DetachedCriteria.forClass(getEntityClass());
        ProjectionList proj = Projections.projectionList();
        proj.add(Projections.max("tglPenganngkatan"));
        proj.add(Projections.groupProperty("nik"));
        maxTglPengangkatanQuery.setProjection(proj);
        criteria.add(Subqueries.propertiesIn(new String[] {"tglPenganngkatan","nik"}, maxTglPengangkatanQuery));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<CareerTransitionInboxViewModel> getEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT approvalActivity.id AS approvalActivityId, " +
                "empData.nik AS empNik," +
                "CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
                "approvalActivity.approval_status AS approvalStatus, " +
                "approvalActivity.pending_data AS jsonData, " +
                "approvalActivity.request_time AS requestTime, " +
                "approvalActivity.activity_number AS activityNumber " +
                "FROM approval_activity approvalActivity " +
                "LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
                "LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " +
                "LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
                "LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
                "INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                "INNER JOIN company AS company ON department.company_id = company.id  " +
                "LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
                "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +
                "AND approvalDefinition.name = :appDefinitionName "
                );
        selectQuery.append(this.setWhereQueryCareerTransitionInboxActivityByParam(searchParameter));
        selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY " + order);
        
        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(CareerTransitionInboxViewModel.class));
    	hbm = this.setValueQueryCareerTransitionInboxActivityByParam(hbm, searchParameter);
    	
    	return hbm.list();
    }

    @Override
    public Long getTotalgetEntityEmpCareerHistoryInboxByParam(CareerTransitionInboxSearchParameter searchParameter) {
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
    	                "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) " +
    	                "AND approvalDefinition.name = :appDefinitionName "
    	                );    	
    	selectQuery.append(this.setWhereQueryCareerTransitionInboxActivityByParam(searchParameter));
    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryCareerTransitionInboxActivityByParam(hbm, searchParameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String setWhereQueryCareerTransitionInboxActivityByParam(CareerTransitionInboxSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
        	whereQuery.append("AND empData.nik LIKE :empNik ");
        }        
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	whereQuery.append("AND (bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }        
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
        	whereQuery.append("AND (requester.user_id = :userId AND approvalActivity.approval_status IN (0,1,6) " +
        			"OR approver.user_id = :userId AND approvalActivity.approval_status IN (0)) ");
        } else {
        	//view for administrator(can view all employee)
        	whereQuery.append("AND approvalActivity.approval_status IN (0,1,6) ");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryCareerTransitionInboxActivityByParam(Query hbm, CareerTransitionInboxSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getEmpNik() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		} else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.EMPLOYEE_CAREER_TRANSITION);
    		}
    	}    	
    	return hbm;
    }

	@Override
	public EmpCareerHistory getEntityByApprovalActivityNumber(String approvalActivityNumber) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", approvalActivityNumber));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("copyOfLetterTo", FetchMode.JOIN);
        criteria.setFetchMode("copyOfLetterTo.bioData", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("jabatan.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatan.department.company", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("careerTransition", FetchMode.JOIN);
        return (EmpCareerHistory) criteria.uniqueResult();
	}

	@Override
	public List<EmpCareerHistory> getPreviousEmpCareerByBioDataIdAndCurrentCreatedOn(Long bioDataId, Date currentCreatedOn) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("bioData.id", bioDataId));
		criteria.add(Restrictions.lt("createdOn", currentCreatedOn));
		criteria.addOrder(Order.desc("createdOn"));
        criteria.setFetchMode("copyOfLetterTo", FetchMode.JOIN);
        criteria.setFetchMode("copyOfLetterTo.bioData", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("jabatan.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatan.department.company", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
		/*criteria.setMaxResults(1);*/
		return criteria.list();
		
	}

	@Override
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		
		final StringBuilder query = new StringBuilder("SELECT empCareerHistory.id AS empCareerHistoryId,");
		query.append(" bioData.id AS bioDataId,");
		query.append(" careerTransition.id AS careerTransitionId,");
		query.append(" jabatan.id AS lastJabatanId,");
		query.append(" empCareerHistory.nik as nik,");
		query.append(" jabatan.name AS jabatanName,");
		query.append(" empCareerHistory.status AS empCareerHistoryStatus,");
		query.append(" empCareerHistory.tglPenganngkatan AS terminationDate");
		query.append(" FROM EmpCareerHistory empCareerHistory");
		query.append(" INNER JOIN empCareerHistory.bioData bioData");
		query.append(" INNER JOIN empCareerHistory.careerTransition careerTransition");
		query.append(" INNER JOIN empCareerHistory.jabatan jabatan");
		query.append(" INNER JOIN careerTransition.systemCareerConst systemCareerConst");
		query.append(" WHERE systemCareerConst.isWork = 0 ");
		
		//filter by search param
        query.append(doSearchEmpEliminationViewModelByParam(searchParameter));
        query.append("ORDER BY " + order);
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryEmpEliminationViewModelByParam(hbm, searchParameter);
        
		return hbm.setMaxResults(maxResults).setFirstResult(firstResult)
				.setResultTransformer(Transformers.aliasToBean(EmpEliminationViewModel.class)).list();
	}

	@Override
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) {
		final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");
		query.append(" FROM EmpCareerHistory empCareerHistory");
		query.append(" INNER JOIN empCareerHistory.bioData bioData");
		query.append(" INNER JOIN empCareerHistory.careerTransition careerTransition");
		query.append(" INNER JOIN empCareerHistory.jabatan jabatan");
		query.append(" INNER JOIN careerTransition.systemCareerConst systemCareerConst");
		query.append(" WHERE systemCareerConst.isWork = 0 ");
		
		//filter by search param
        query.append(doSearchEmpEliminationViewModelByParam(searchParameter));
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryEmpEliminationViewModelByParam(hbm, searchParameter);
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String doSearchEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) {
        StringBuilder query = new StringBuilder();

        if (!StringUtils.equals(searchParameter.getNik(), null)) {
            query.append(" AND empCareerHistory.empNik LIKE :nik ");
        }

        if (!StringUtils.equals(searchParameter.getEmpName(), null)) {
            query.append(" AND ( bioData.firstName LIKE :empName OR bioData.lastName LIKE :empName ");
        }

        if (!StringUtils.equals(searchParameter.getLastJabatanName(), null)) {
            query.append(" AND jabatan.name LIKE :lastJabatanName  ");
        }
        
        query.append(" AND empCareerHistory.status IN :listCareerHistoryStatus  ");
        return query.toString();
    }

    private Query setValueQueryEmpEliminationViewModelByParam(Query hbm, EmpEliminationSearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "empName")) {
                hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
            } else if (StringUtils.equals(param, "nik")) {
                hbm.setParameter("nik", "%" + parameter.getNik() + "%");
            } else if (StringUtils.equals(param, "lastJabatanName")) {
                hbm.setParameter("lastJabatanName", "%" + parameter.getLastJabatanName() + "%");
            }
        }
        
        hbm.setParameterList("listCareerHistoryStatus", Arrays.asList(HRMConstant.EMP_TERMINATION, 
        		HRMConstant.EMP_STOP_CONTRACT, HRMConstant.EMP_LAID_OFF, HRMConstant.EMP_PENSION, HRMConstant.EMP_DISCHAGED));
        return hbm;
    }
}
