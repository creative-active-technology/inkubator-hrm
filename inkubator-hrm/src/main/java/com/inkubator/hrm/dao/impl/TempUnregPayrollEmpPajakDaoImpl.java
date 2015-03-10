package com.inkubator.hrm.dao.impl;

import java.util.List;

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
import com.inkubator.hrm.dao.TempUnregPayrollEmpPajakDao;
import com.inkubator.hrm.entity.TempUnregPayrollEmpPajak;
import com.inkubator.hrm.web.model.UnregPayrollEmpPajakModel;
import com.inkubator.hrm.web.search.UnregPayrollEmpPajakSearchParameter;

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
		Query query = getCurrentSession().createQuery("delete from TempUnregPayrollEmpPajak where unregSalary.id = :unregSalaryId")
				.setLong("unregSalaryId", unregSalaryId);		
        query.executeUpdate();
	}

	@Override
	public List<UnregPayrollEmpPajakModel> getAllDataGroupingTaxCompByUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT unregSalary.id AS unregSalaryId, " +
    			"taxComponent.id AS taxComponentId, " +
    			"taxComponent.name AS taxComponentName, " +
    			"SUM(nominal) AS nominal " +
    			"FROM TempUnregPayrollEmpPajak " +
    			"WHERE unregSalary.id = :unregSalaryId " +
    			"GROUP BY taxComponent " +
    			"ORDER BY taxComponent.id ASC");
    			
		Query hbm = getCurrentSession().createQuery(selectQuery.toString())
			.setResultTransformer(Transformers.aliasToBean(UnregPayrollEmpPajakModel.class))
			.setParameter("unregSalaryId", unregSalaryId);
    	
		return hbm.list();
		
	}

	@Override
	public List<TempUnregPayrollEmpPajak> getByParam(UnregPayrollEmpPajakSearchParameter parameter, int first, int pageSize, Order orderable) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.addOrder(orderable);
        criteria.setFirstResult(first);
        criteria.setMaxResults(pageSize);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParam(UnregPayrollEmpPajakSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(UnregPayrollEmpPajakSearchParameter parameter, Criteria criteria) {
        
    	if (parameter.getNikOrName() != null) {
        	criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        	criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        	Disjunction disjunction = Restrictions.disjunction();
        	disjunction.add(Restrictions.like("bioData.firstName", parameter.getNikOrName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getNikOrName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("empData.nik", parameter.getNikOrName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        
        if (parameter.getUnregSalaryId() != null){
            criteria.add(Restrictions.eq("unregSalary.id", parameter.getUnregSalaryId()));
        }
        
        if (parameter.getTaxComponentId() != null){
            criteria.add(Restrictions.eq("taxComponent.id", parameter.getTaxComponentId()));
        }
        
        criteria.add(Restrictions.isNotNull("id"));
    }
	
}
