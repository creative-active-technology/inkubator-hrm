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
		final StringBuilder query = new StringBuilder("SELECT careerEmpElimination.id AS id,");
		query.append(" wtPeriode.id AS lastWtPeriodId,");
		query.append(" wtPeriode.fromPeriode AS startDateLastWtPeriod,");
		query.append(" empData.id AS empDataId,");
		query.append(" bioData.id AS bioDataId,");
		query.append(" jabatan.id AS lastJabatanId, ");
		query.append(" empData.joinDate AS joinDate,");
		query.append(" empData.nik AS nik, ");
		query.append(" jabatan.name AS jabatanName ");
		query.append(" FROM CareerEmpElimination careerEmpElimination");
		query.append(" INNER JOIN careerEmpElimination.careerTerminationType careerTerminationType");
		query.append(" INNER JOIN careerEmpElimination.empData empData");
		query.append(" INNER JOIN empData.bioData bioData");
		query.append(" INNER JOIN empData.jabatanByJabatanId jabatan");
		query.append(" INNER JOIN careerEmpElimination.wtPeriode wtPeriode");
		query.append(" INNER JOIN careerTerminationType.systemCareerConst systemCareerConst");
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
		query.append(" FROM CareerEmpElimination careerEmpElimination");
		query.append(" INNER JOIN careerEmpElimination.careerTerminationType careerTerminationType");
		query.append(" INNER JOIN careerEmpElimination.empData empData");
		query.append(" INNER JOIN empData.bioData bioData");
		query.append(" INNER JOIN empData.jabatanByJabatanId jabatan");
		query.append(" INNER JOIN careerEmpElimination.wtPeriode wtPeriode");
		query.append(" INNER JOIN careerTerminationType.systemCareerConst systemCareerConst");
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
            query.append(" AND empData.nik LIKE :nik ");
        }

        if (!StringUtils.equals(searchParameter.getEmpName(), null)) {
            query.append(" AND ( bioData.firstName LIKE :empName OR bioData.lastName LIKE :empName ");
        }

        if (!StringUtils.equals(searchParameter.getLastJabatanName(), null)) {
            query.append(" AND jabatan.name LIKE :lastJabatanName  ");
        }
        
        query.append(" AND empData.status IN :listCareerHistoryStatus  ");
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
