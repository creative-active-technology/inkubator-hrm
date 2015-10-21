/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.LoanNewApplicationDao;
import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.LoanHistoryViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.model.LoanNewApplicationStatusViewModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;
import com.inkubator.hrm.web.search.LoanStatusSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "loanNewApplicationDao")
@Lazy
public class LoanNewApplicationDaoImpl extends IDAOImpl<LoanNewApplication> implements LoanNewApplicationDao {

    @Override
    public Class<LoanNewApplication> getEntityClass() {
        return LoanNewApplication.class;
    }

    @Override
    public LoanNewApplication getEntityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        return (LoanNewApplication) criteria.uniqueResult();
    }

    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<LoanNewApplication> getListUnpaidLoanByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));
        criteria.add(Restrictions.ne("loanStatus", HRMConstant.LOAN_PAID));
        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewSchemaId(Long empDataId, Long loanNewSchemaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("loanNewSchema.id", loanNewSchemaId));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_DISBURSED));
        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_PAID));
        criteria.add(disjunction);

        return criteria.list();
    }

    @Override
    public List<LoanNewApplication> getByParamByStatusUndisbursed(LoanNewSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamByStatusUndisbursed(LoanNewSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_UNDISBURSED));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(LoanNewSearchParameter parameter, Criteria criteria) {

        if (StringUtils.isNotEmpty(parameter.getLoanType())) {
            criteria.createAlias("loanNewType", "loanNewType", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("loanNewType.loanTypeName", parameter.getLoanType(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getEmployee())) {
            criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
            criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmployee(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmployee(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<LoanNewApplicationBoxViewModel> getUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        
        StringBuffer selectQuery = new StringBuffer(
    			"SELECT approvalActivity.id AS approvalActivityId, " +
                        "approvalActivity.activity_number AS activityNumber, " +
                        "loanApplication.id AS loanNewApplicationId, " +
                        "empData.nik AS empNik, " +
                        "CONCAT(bioData.first_name,' ',bioData.last_name) AS empName, " +
                        "empDataApprover.nik AS approverNik, " +
                        "CONCAT(bioDataApprover.first_name,' ',bioDataApprover.last_name) AS approverName, " +
                        "loanType.id AS loanNewTypeId, " +
                        "loanType.loan_type_name AS loanNewTypeName, " +
                        "approvalActivity.approval_status AS approvalStatus, " +
                        "approvalActivity.pending_data AS jsonData " +                     
                        "FROM approval_activity approvalActivity " +
                        "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
                        "INNER JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by  " +
                        "INNER JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by  " +
                        "INNER JOIN emp_data AS empData ON requester.emp_data_id = empData.id  " +
                        "INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                        "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                        "INNER JOIN company AS company ON department.company_id = company.id  " +
                        "INNER JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id  " +
                        "INNER JOIN emp_data AS empDataApprover ON approver.emp_data_id = empDataApprover.id  " +
                        "INNER JOIN bio_data AS bioDataApprover ON empDataApprover.bio_data_id = bioDataApprover.id  " +
                        "INNER JOIN loan_new_type AS loanType ON approvalActivity.type_specific = loanType.id  " +
                        "LEFT JOIN loan_new_application AS loanApplication ON approvalActivity.activity_number = loanApplication.approval_activity_number  " +
                        "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number)  " +
                        "AND approvalDefinition.name = :appDefinitionName"
                        + " AND  company.id = :companyId "); 

        
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
    	selectQuery.append("GROUP BY approvalActivity.activity_number ");
    	selectQuery.append("ORDER BY ").append(orderable);
    	
    	   Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                	.setResultTransformer(Transformers.aliasToBean(LoanNewApplicationBoxViewModel.class));
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
    	return hbm.list();                
    }
    
    private String setWhereQueryUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) {
    	StringBuffer whereQuery = new StringBuffer();    	
    	
        if (StringUtils.isNotEmpty(parameter.getEmpNik())) {
        	whereQuery.append("AND empData.nik LIKE :empNik ");
        }        
        if (StringUtils.isNotEmpty(parameter.getEmpName())) {
        	whereQuery.append("AND (bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName) ");
        }        
        if (StringUtils.isNotEmpty(parameter.getLoanTypeName())) {
        	whereQuery.append("AND loanType.loan_type_name LIKE :loanTypeName ");
        }    
        
        if (StringUtils.isNotEmpty(parameter.getUserId())) {
            whereQuery.append("AND (requester.user_id = :userId AND approvalActivity.approval_status IN (").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_WAITING_REVISED)
                        .append(") ")
                        .append("OR approver.user_id = :userId AND approvalActivity.approval_status IN (")
                        .append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(")) ");
        } else {
        	//view for administrator(can view all employee)        	
                whereQuery.append("AND approvalActivity.approval_status IN ( ").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_WAITING_REVISED)
                        .append(")");
        }
        
        return whereQuery.toString();
    }
    
    private Query setValueQueryUndisbursedActivityByParam(Query hbm, LoanNewApplicationBoxSearchParameter parameter){    	
    	for(String param : hbm.getNamedParameters()){
    		if(StringUtils.equals(param, "empName")){
    			hbm.setParameter("empName", "%" + parameter.getEmpName() + "%");
    		} else if(StringUtils.equals(param, "empNik")){
    			hbm.setParameter("empNik", "%" + parameter.getEmpNik() + "%");
    		} else if(StringUtils.equals(param, "loanTypeName")){
    			hbm.setParameter("loanTypeName", "%" + parameter.getLoanTypeName() + "%");
    		} else if(StringUtils.equals(param, "userId")){
    			hbm.setParameter("userId", parameter.getUserId());
    		} else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.LOAN);
    		} else if(StringUtils.equals(param, "companyId")){
    			hbm.setParameter("companyId", HrmUserInfoUtil.getCompanyId());
    		}
    	}    	
    	return hbm;
    }

    @Override
    public Long getTotalUndisbursedActivityByParam(LoanNewApplicationBoxSearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
    			"SELECT count(*) " +
    			"FROM approval_activity approvalActivity " +
                        "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
                        "INNER JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by  " +
                        "INNER JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by  " +
                        "INNER JOIN emp_data AS empData ON requester.emp_data_id = empData.id  " +
                        "INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id  " +
                        "INNER JOIN department AS department ON jabatan.departement_id = department.id  " +
                        "INNER JOIN company AS company ON department.company_id = company.id  " +
                        "INNER JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id  " +
                        "INNER JOIN emp_data AS empDataApprover ON requester.emp_data_id = empDataApprover.id  " +
                        "INNER JOIN bio_data AS bioDataApprover ON empDataApprover.bio_data_id = bioDataApprover.id  " +
                        "INNER JOIN loan_new_type AS loanType ON approvalActivity.type_specific = loanType.id  " +
                        "LEFT JOIN loan_new_application AS loanApplication ON approvalActivity.activity_number = loanApplication.approval_activity_number  " +
                        "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number)  " +
                        "AND approvalDefinition.name = :appDefinitionName "
                        + " AND  company.id = :companyId ");    	
    	selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
    }

	@Override
	public List<LoanHistoryViewModel> getListLoanHistoryByEmpDataId(
			Long empDataId) {
		StringBuffer selectQuery = new StringBuffer(
    			" SELECT loanNewApplication.id AS loanNewApplicationId, " +
    			" loanNewApplication.applicationDate AS tglPengajuan, " +
				" loanNewApplication.loanStatus AS loanStatus, " +
				" loanNewType.id AS loanNewTypeId, " +
				" loanNewType.loanTypeName AS loanNewTypeName, " +
				" loanNewApplication.nominalPrincipal AS loanNominal, " +
				" loanNewApplication.termin AS totalNumberOfInstallment, " +
				" loanNewType.interestMethod AS typeOfInterest, " +
				" loanNewType.interest AS loanInterestRate, " +
				" loanNewApplication.dibursementDate AS loanPaymentDate, " +
				" loanNewApplication.bufferTime AS buffer " +
    			" FROM LoanNewApplication loanNewApplication " +
    			" INNER JOIN loanNewApplication.empData empData  " +
    			" INNER JOIN loanNewApplication.loanNewType loanNewType  " +
    			" INNER JOIN loanNewApplication.loanNewSchema loanNewSchema  " +
    			" WHERE empData.id = :empDataId ") ;
    		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("empDataId", empDataId)    		
    			.setResultTransformer(Transformers.aliasToBean(LoanHistoryViewModel.class));
    	
    	return hbm.list();
	}

	@Override
	public List<LoanNewApplication> getListLoanDisbursedOrPaidByEmpDataIdAndLoanNewTypeId(Long empDataId, Long loanNewTypeId) {
		 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

	        criteria.setFetchMode("empData", FetchMode.JOIN);
	        criteria.setFetchMode("loanNewType", FetchMode.JOIN);
	        criteria.add(Restrictions.eq("empData.id", empDataId));
	        criteria.add(Restrictions.eq("loanNewType.id", loanNewTypeId));

	        Disjunction disjunction = Restrictions.disjunction();
	        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_DISBURSED));
	        disjunction.add(Restrictions.eq("loanStatus", HRMConstant.LOAN_PAID));
	        criteria.add(disjunction);

	        return criteria.list();
	}

	@Override
	public List<LoanNewApplicationStatusViewModel> getAllDataLoanNewApplicationStatus(LoanStatusSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
				"SELECT loanNewApplication.id as id, bioData.first_name as firstName, bioData.last_name as lastName, loanNewSchema.loan_schema_name as loanSchemaName, " +
				"loanNewApplication.nominal_principal as nominalPrincipal, approvalDefinition.name as approvalName, loanNewApplication.dibursement_date as dibursmentDate, " +
				"loanNewApplication.purpose_note as purposeNote, loanNewApplication.loan_status as loanStatus, loanNewApplication.termin as totalTermin, " +
				"(select loanNewApplicationInstallment.num_of_installment from loan_new_application_installment loanNewApplicationInstallment " +
				"where loanNewApplicationInstallment.installment_date < now() AND loanNewApplicationInstallment.loan_new_application_id = loanNewApplication.id) as termin, " +
				"approvalActivity.pending_data AS jsonData, loanNewApplication.approval_activity_number as approvalActivityNumber, " +
				"approvalActivity.approval_status as approvalStatus, approvalActivity.activity_number as activityNumber " +
				"FROM hrm.approval_activity approvalActivity " +
				"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
				"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
				"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
				"INNER JOIN golongan_jabatan AS golonganJabatan ON empData.gol_jab_id = golonganJabatan.id " +
				"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id " +
				"INNER JOIN department AS department ON jabatan.departement_id = department.id " +
				"INNER JOIN company AS company ON department.company_id = company.id " +
				"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
				"LEFT JOIN loan_new_application AS loanNewApplication ON approvalActivity.activity_number = loanNewApplication.approval_activity_number " +
				"LEFT JOIN loan_new_schema AS loanNewSchema ON loanNewApplication.loan_new_schema = loanNewSchema.id " +
				"WHERE approvalDefinition.name = 'LOAN' " +
				"AND (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) ");    	

		selectQuery.append(this.doSearchLoanNewApplicationStatus(parameter));
		selectQuery.append(" ORDER BY " + orderable);
		System.out.println(selectQuery.toString());

		Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
            	.setResultTransformer(Transformers.aliasToBean(LoanNewApplicationStatusViewModel.class));
		//hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
	
		return hbm.list();                
	}

	@Override
	public Long getTotalDataLoanNewApplicationStatus(LoanStatusSearchParameter parameter) {
		StringBuffer selectQuery = new StringBuffer(
				"SELECT count(*) " +
				"FROM hrm.approval_activity approvalActivity " +
				"LEFT JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id " +
				"LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by " +
				"LEFT JOIN emp_data AS empData ON requester.emp_data_id = empData.id " +
				"INNER JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id " +
				"INNER JOIN department AS department ON jabatan.departement_id = department.id " +
				"INNER JOIN company AS company ON department.company_id = company.id " +
				"INNER JOIN golongan_jabatan AS golonganJabatan ON empData.gol_jab_id = golonganJabatan.id " +
				"LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id " +
				"LEFT JOIN loan_new_application AS loanNewApplication ON approvalActivity.activity_number = loanNewApplication.approval_activity_number " +
				"LEFT JOIN loan_new_schema AS loanNewSchema ON loanNewApplication.loan_new_schema = loanNewSchema.id " +
				"WHERE approvalDefinition.name = 'LOAN' " +
				"AND (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number)");    	
		selectQuery.append(this.doSearchLoanNewApplicationStatus(parameter));
		//selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));    	
    	Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());    	
    	//hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);
    	
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	public String doSearchLoanNewApplicationStatus(LoanStatusSearchParameter parameter){
		StringBuffer searchQuery = new StringBuffer();  
		
		if(!parameter.getListDepartment().isEmpty()){
			searchQuery.append(" AND department.id IN (");
			int sizeDepartmentParameter = parameter.getListDepartment().size();
			for(int i = 0; i < sizeDepartmentParameter; i++){
				 if (i < (sizeDepartmentParameter - 1)) {
					 searchQuery.append(parameter.getListDepartment().get(i).getId());
					 searchQuery.append(", ");
				 }else{
					 searchQuery.append(parameter.getListDepartment().get(i).getId());
				 }
			 }
			searchQuery.append(" )");
		}
		
		if(!parameter.getListGolonganJabatan().isEmpty()){
			searchQuery.append(" AND golonganJabatan.id IN (");
			int sizeGolonganJabatanParameter = parameter.getListGolonganJabatan().size();
			for(int i = 0; i < sizeGolonganJabatanParameter; i++){
				 if (i < (sizeGolonganJabatanParameter - 1)) {
					 searchQuery.append(parameter.getListGolonganJabatan().get(i).getId());
					 searchQuery.append(", ");
				 }else{
					 searchQuery.append(parameter.getListGolonganJabatan().get(i).getId());
				 }
			 }
			searchQuery.append(" )");
		}
		
		if(parameter.getStartNominal() != null && parameter.getEndNominal() != null){
			searchQuery.append(" AND loanNewApplication.nominal_principal BETWEEN " + parameter.getStartNominal() + " AND " + parameter.getEndNominal());
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(parameter.getStartDate() != null && parameter.getEndDate() != null){
			searchQuery.append(" AND loanNewApplication.application_date BETWEEN '" + dateFormat.format(parameter.getStartDate()) + "' AND '" + dateFormat.format(parameter.getEndDate()) + "' ");
		}
		
		return searchQuery.toString();
	}
}
