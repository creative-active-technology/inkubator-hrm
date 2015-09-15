package com.inkubator.hrm.dao.impl;

import java.math.BigDecimal;
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
import com.inkubator.hrm.dao.TempUnregPayrollDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.web.model.UnregSalaryCalculationExecuteModel;
import com.inkubator.hrm.web.search.UnregCalculationSearchParameter;

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
		Query query = getCurrentSession().createQuery("delete from TempUnregPayroll where unregSalary.id = :unregSalaryId")
				.setLong("unregSalaryId", unregSalaryId);
        query.executeUpdate();
	}

	@Override
	public List<UnregSalaryCalculationExecuteModel> getByParamUnregSalaryId(Long unregSalaryId, int first, int pageSize, Order orderable) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT unregSalary.id AS unregSalaryId, " +
    			"paySalaryComponent.id AS paySalaryCompId, " +
    			"paySalaryComponent.name AS paySalaryCompName, " +
    			"SUM(CASE WHEN nominal != 0 THEN 1 ELSE 0 END) AS totalEmployee, " +
    			"SUM(nominal) AS totalNominal " +
    			"FROM TempUnregPayroll " +
    			"WHERE unregSalary.id = :unregSalaryId " +
    			"GROUP BY paySalaryComponent " +
    			"ORDER BY " + orderable);
		Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(pageSize).setFirstResult(first)
            	.setResultTransformer(Transformers.aliasToBean(UnregSalaryCalculationExecuteModel.class)).setParameter("unregSalaryId", unregSalaryId);
    	
		return hbm.list();
		
	}

	@Override
	public Long getTotalByParamUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT COUNT(distinct paySalaryComponent) " +
    			"FROM TempUnregPayroll " +
    			"WHERE unregSalary.id = :unregSalaryId ");
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setParameter("unregSalaryId", unregSalaryId);
    	
		return Long.valueOf(hbm.uniqueResult().toString());
		
	}

	@Override
	public List<TempUnregPayroll> getByParam(UnregCalculationSearchParameter parameter, int first, int pageSize, Order orderable) {
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
	public Long getTotalByParam(UnregCalculationSearchParameter parameter) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
	}
	
	private void doSearchByParam(UnregCalculationSearchParameter parameter, Criteria criteria) {
        
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
        
        if (parameter.getPaySalaryComponentId() != null){
            criteria.add(Restrictions.eq("paySalaryComponent.id", parameter.getPaySalaryComponentId()));
        }
        
        criteria.add(Restrictions.ne("nominal", new BigDecimal(0)));
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public Long getTotalEmployeeByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
		criteria.add(Restrictions.eq("paySalaryComponent.id", paySalaryComponentId));
		criteria.add(Restrictions.ne("nominal", new BigDecimal(0)));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public BigDecimal getTotalNominalByUnregSalaryIdAndPaySalaryCompId(Long unregSalaryId, Long paySalaryComponentId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(nominal) " +
    			"FROM TempUnregPayroll " +
    			"WHERE unregSalary.id = :unregSalaryId " +
    			"AND paySalaryComponent.id = :paySalaryComponentId");
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("unregSalaryId", unregSalaryId)
    			.setParameter("paySalaryComponentId", paySalaryComponentId);
    	
    	Object result = hbm.uniqueResult();
    	
		return result != null ? new BigDecimal(result.toString()) : new BigDecimal(0);
	}

	@Override
	public Long getTotalByUnregSalaryId(Long unregSalaryId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("unregSalary.id", unregSalaryId));
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		
	}

	@Override
	public List<EmpData> getAllDataEmployeeByUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT distinct empData.id as id " +
    			"FROM TempUnregPayroll " +
    			"WHERE unregSalary.id = :unregSalaryId");
		Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setResultTransformer(Transformers.aliasToBean(EmpData.class)).setParameter("unregSalaryId", unregSalaryId);
    	
		return hbm.list();
	}
	
	@Override
	public BigDecimal getTotalNominalByUnregSalaryId(Long unregSalaryId) {
		StringBuffer selectQuery = new StringBuffer(
    			"SELECT SUM(nominal) " +
    			"FROM TempUnregPayroll " +
    			"WHERE unregSalary.id = :unregSalaryId ");
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("unregSalaryId", unregSalaryId);
    	
    	Object result = hbm.uniqueResult();
    	
		return result != null ? new BigDecimal(result.toString()) : new BigDecimal(0);
	}
}
