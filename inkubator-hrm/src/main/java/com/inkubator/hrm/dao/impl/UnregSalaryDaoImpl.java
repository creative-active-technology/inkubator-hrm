/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregSalaryDao;
import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.web.model.UnregSalaryViewModel;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "unregSalaryDao")
@Lazy
public class UnregSalaryDaoImpl extends IDAOImpl<UnregSalary> implements UnregSalaryDao {

    @Override
    public Class<UnregSalary> getEntityClass() {
        return UnregSalary.class;
    }

    @Override
    public List<UnregSalary> getByParam(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("wtPeriode", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(UnregSalarySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(UnregSalarySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public UnregSalary getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("wtPeriode", FetchMode.JOIN);
        return (UnregSalary) criteria.uniqueResult();
    }

    @Override
    public void deleteAllDataByUnregSalaryId(Long unregSalaryId) {
        String hqlDeleteUnregDepartment = "delete from UnregDepartement ud where ud.unregSalary.id = :unregSalaryId";
        String hqlDeleteUnregEmpReligion = "delete from UnregEmpReligion uer where uer.unregSalary.id = :unregSalaryId";
        String hqlDeleteUnregEmpType = "delete from UnregEmpType uet where uet.unregSalary.id = :unregSalaryId";
        String hqlDeleteUnregGoljab = "delete from UnregGoljab ug where ug.unregSalary.id = :unregSalaryId";
        String hqlDeleteUnregGender = "delete from UnregGender ugen where ugen.unregSalary.id = :unregSalaryId";
        int deletedJabatanEdukasi = getCurrentSession().createQuery(hqlDeleteUnregDepartment).setString("unregSalaryId", String.valueOf(unregSalaryId)).executeUpdate();
        int deletedJabatanMajor = getCurrentSession().createQuery(hqlDeleteUnregEmpReligion).setString("unregSalaryId", String.valueOf(unregSalaryId)).executeUpdate();
        int deletedJabatanFaculty = getCurrentSession().createQuery(hqlDeleteUnregEmpType).setString("unregSalaryId", String.valueOf(unregSalaryId)).executeUpdate();
        int deletedJabatanOccupation = getCurrentSession().createQuery(hqlDeleteUnregGoljab).setString("unregSalaryId", String.valueOf(unregSalaryId)).executeUpdate();
        int deletedGender = getCurrentSession().createQuery(hqlDeleteUnregGender).setString("unregSalaryId", String.valueOf(unregSalaryId)).executeUpdate();
        
    }

    @Override
    public List<UnregSalaryViewModel> getByParamWithViewModel(UnregSalarySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        
        final StringBuilder query = new StringBuilder("SELECT A.id AS unregSalaryId, A.code AS code, A.name AS name, A.salary_date AS salaryDate, C.bulan AS bulan, C.tahun AS year, count(B.unreg_id) AS total");
        query.append(" FROM unreg_salary A");
        query.append(" LEFT JOIN unreg_pay_components B ON A.id = B.unreg_id");
        query.append(" INNER JOIN wt_periode C WHERE A.based_period_id = C.id");
        doSearchByParam(searchParameter, query);
        query.append(" GROUP BY A.code");
        if (order.toString().contains("code") || order.toString().contains("name") || order.toString().contains("total") || order.toString().contains("salaryDate")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by A." + order);
        }
        query.append(" LIMIT " + firstResult + ", " + maxResults);
       
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(UnregSalaryViewModel.class))
                .list();
    }

    @Override
    public Long getTotalByParamViewModel(UnregSalarySearchParameter searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(A.code)");
        query.append(" FROM unreg_salary A");
        query.append(" LEFT JOIN unreg_pay_components B ON A.id = B.unreg_id");
        query.append(" INNER JOIN wt_periode C WHERE A.based_period_id = C.id");
        doSearchByParam(searchParameter, query);
        query.append(" GROUP BY A.code) as totalData");
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    public void doSearchByParam(UnregSalarySearchParameter searchParameter, StringBuilder query) {
        if (searchParameter.getCode() != null) {
            query.append(" AND A.code like '%" + searchParameter.getCode() + "%'");
        } else if (searchParameter.getName() != null) {
            query.append(" AND A.name like '%" + searchParameter.getName() + "%'");
        }
    }

	@Override
	public List<UnregSalary> getByParamBySalaryCalculation(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("wtPeriode", "wtPeriode", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("isAlreadyPaid", false));
        criteria.add(Restrictions.lt("wtPeriode.fromPeriode", fromPeriodPayrollType));
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParamBySalaryCalculation(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("wtPeriode", "wtPeriode", JoinType.INNER_JOIN);
		criteria.add(Restrictions.eq("isAlreadyPaid", false));
        criteria.add(Restrictions.lt("wtPeriode.fromPeriode", fromPeriodPayrollType));
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	@Override
	public List<UnregSalary> getByParamBySalaryPayroll(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType, int firstResult, int maxResults, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("wtPeriode", "wtPeriode", JoinType.INNER_JOIN);
        criteria.add(Restrictions.lt("wtPeriode.fromPeriode", fromPeriodPayrollType));
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
		
	}

	@Override
	public Long getTotalByParamBySalaryPayroll(UnregSalarySearchParameter searchParameter, Date fromPeriodPayrollType) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("wtPeriode", "wtPeriode", JoinType.INNER_JOIN);
        criteria.add(Restrictions.lt("wtPeriode.fromPeriode", fromPeriodPayrollType));
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
}
