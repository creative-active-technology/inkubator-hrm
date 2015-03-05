package com.inkubator.hrm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "tempUnregPayrollEmpPajakDao")
@Lazy
public class TempUnregPayrollEmpPajakDaoImpl extends IDAOImpl<TempUnregPayrollEmpPajak> implements TempUnregPayrollEmpPajakDao {

	@Override
	public Class<TempUnregPayrollEmpPajak> getEntityClass() {
		return TempUnregPayrollEmpPajak.class;
		
	}

	@Override
	public TempUnregPayrollEmpPajak getEntityByEmpDataIdAndUnregSalaryIdAndTaxComponentId(Long empDataId, Long unregSalaryId, Long taxCompId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("taxComponent.id", taxCompId));
		criteria.add(Restrictions.eq("empData.id", empDataId));
		criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
        
        return (TempUnregPayrollEmpPajak) criteria.uniqueResult();
	}

	@Override
	public void deleteByUnregSalaryId(Long unregSalaryId) {
		Query query = getCurrentSession().createQuery("delete from TempUnregPayrollEmpPajak where unregSalary.id = :unregSalaryId");
		query.setParameter("unregSalaryId", unregSalaryId);
        query.executeUpdate();
	}
	
}
