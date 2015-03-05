package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.entity.TempUnregPayroll;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "tempUnregPayrollDao")
@Lazy
public class TempUnregPayrollDaoImpl extends IDAOImpl<TempUnregPayroll> implements TempUnregPayrollDao {

	@Override
	public Class<TempUnregPayroll> getEntityClass() {
		return TempUnregPayroll.class;
		
	}
	
	@Override
    public List<TempUnregPayroll> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.taxComponent", "taxComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.isNotNull("paySalaryComponent.taxComponent"));
        criteria.addOrder(Order.desc("taxComponent.id"));
        return criteria.list();
    }

	@Override
	public TempUnregPayroll getEntityByEmpDataIdAndUnregSalaryIdAndSpecificModelComponent(Long empDataId, Long unregSalaryId, Integer specific) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryComponent.modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.add(Restrictions.eq("modelComponent.spesific", specific));
        criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
        return (TempUnregPayroll) criteria.uniqueResult();		
	}

	@Override
	public void deleteByUnregSalaryId(Long unregSalaryId) {
		Query query = getCurrentSession().createQuery("delete from TempUnregPayroll where unregSalary.id = :unregSalaryId");
		query.setParameter("unregSalaryId", unregSalaryId);
        query.executeUpdate();
	}
}
