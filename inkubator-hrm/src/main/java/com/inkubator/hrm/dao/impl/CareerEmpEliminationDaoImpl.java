package com.inkubator.hrm.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.CareerEmpEliminationDao;
import com.inkubator.hrm.entity.CareerEmpElimination;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
@Repository(value="careerEmpEliminationDao")
@Lazy
public class CareerEmpEliminationDaoImpl extends IDAOImpl<CareerEmpElimination> implements CareerEmpEliminationDao {

	@Override
	public Class<CareerEmpElimination> getEntityClass() {
		return CareerEmpElimination.class;
	}

	@Override
	public List<EmpEliminationViewModel> getListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
		
		final StringBuilder query = new StringBuilder("SELECT careerEmpElimination.id AS careerEmpEliminationId,");
		query.append(" careerEmpElimination.reason AS reason,");
		query.append(" careerEmpElimination.elimination_status AS status,");
		query.append(" careerEmpElimination.effective_date AS terminationDate,");
		query.append(" approvalActivity.id AS approvalActivityId,");
		query.append(" approvalActivity.activity_number AS activityNumber,");
		query.append(" approvalActivity.pending_data AS jsonData, ");
		query.append(" wtPeriode.id AS lastWtPeriodId,");
		query.append(" wtPeriode.from_periode AS startDateLastWtPeriod,");
		query.append(" empData.id AS empDataId,");
		query.append(" bioData.id AS bioDataId,");
		query.append(" CONCAT(bioData.first_name,' ',bioData.last_name) AS empName,");
		query.append(" jabatan.id AS lastJabatanId, ");
		query.append(" empData.join_date AS joinDate,");
		query.append(" empData.nik AS nik, ");
		query.append(" jabatan.name AS jabatanName ");
		query.append(" FROM approval_activity approvalActivity");
		query.append(" LEFT JOIN career_emp_elimination AS careerEmpElimination ON approvalActivity.activity_number = careerEmpElimination.approval_activity_number ");
		query.append(" INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id ");
		query.append(" LEFT JOIN career_termination_type AS careerTerminationType ON careerEmpElimination.career_termination_type_id = careerTerminationType.id ");
		query.append(" LEFT JOIN wt_periode AS wtPeriode ON careerEmpElimination.last_salary_periode_id = wtPeriode.id ");
		query.append(" LEFT JOIN emp_data AS empData ON careerEmpElimination.emp_data_id = empData.id  ");
		query.append(" LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id  ");
		query.append(" LEFT JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id ");
		query.append(" WHERE  "
				+ " (approvalActivity.activity_number,approvalActivity.sequence) "
				+ " IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
				+ " AND approvalDefinition.name = :appDefinitionName ");
		
		//filter by search param
        query.append(doSearchEmpEliminationViewModelByParam(searchParameter));
        query.append("ORDER BY " + order);
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        hbm = this.setValueQueryEmpEliminationViewModelByParam(hbm, searchParameter);
        
		return hbm.setMaxResults(maxResults).setFirstResult(firstResult)
				.setResultTransformer(Transformers.aliasToBean(EmpEliminationViewModel.class)).list();
	}

	@Override
	public Long getTotalListEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) {
		
		final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");
		query.append(" FROM approval_activity approvalActivity ");
		query.append(" LEFT JOIN career_emp_elimination AS careerEmpElimination ON approvalActivity.activity_number = careerEmpElimination.approval_activity_number ");
		query.append(" INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id ");
		query.append(" LEFT JOIN career_termination_type AS careerTerminationType ON careerEmpElimination.career_termination_type_id = careerTerminationType.id ");
		query.append(" LEFT JOIN wt_periode AS wtPeriode ON careerEmpElimination.last_salary_periode_id = wtPeriode.id ");
		query.append(" LEFT JOIN emp_data AS empData ON careerEmpElimination.emp_data_id = empData.id  ");
		query.append(" LEFT JOIN bio_data AS bioData ON empData.bio_data_id = bioData.id  ");
		query.append(" LEFT JOIN jabatan AS jabatan ON empData.jabatan_id = jabatan.id ");
		query.append(" WHERE  "
				+ " (approvalActivity.activity_number,approvalActivity.sequence) "
				+ " IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
				+ " AND approvalDefinition.name = :appDefinitionName ");
		
		//filter by search param
        query.append(doSearchEmpEliminationViewModelByParam(searchParameter));
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        hbm = this.setValueQueryEmpEliminationViewModelByParam(hbm, searchParameter);
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String doSearchEmpEliminationViewModelByParam(EmpEliminationSearchParameter searchParameter) {
        StringBuilder query = new StringBuilder();

        if (!StringUtils.equals(searchParameter.getNik(), null)) {
            query.append(" AND empData.nik LIKE :nik ");
        }

        if (!StringUtils.equals(searchParameter.getEmpName(), null)) {
            query.append(" AND ( bioData.first_name LIKE :empName OR bioData.last_name LIKE :empName ) ");
        }

        if (!StringUtils.equals(searchParameter.getLastJabatanName(), null)) {
            query.append(" AND jabatan.name LIKE :lastJabatanName  ");
        }
        
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
            }else if(StringUtils.equals(param, "appDefinitionName")){
    			hbm.setParameter("appDefinitionName", HRMConstant.EMPLOYEE_ELIMINATION);
    		}
        }
        
        return hbm;
    }

}
