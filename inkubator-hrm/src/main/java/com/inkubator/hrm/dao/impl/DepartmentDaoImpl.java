/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.DepartmentDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.web.search.DepartmentSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deniarianto
 */
@Repository(value = "departmentDao")
@Lazy
public class DepartmentDaoImpl extends IDAOImpl<Department> implements DepartmentDao {

    @Override
    public Class<Department> getEntityClass() {
        return Department.class;
    }

    @Override
    public List<Department> getByParam(DepartmentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchDepartmentByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.setFetchMode("costCenterDept", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalDepartmentByParam(DepartmentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchDepartmentByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getByDepartmentCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("departmentCode", code, MatchMode.ANYWHERE));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchDepartmentByParam(DepartmentSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("departmentName", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("departmentCode", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
//        criteria.setFetchMode("costCenterDept", FetchMode.JOIN);
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("departmentCode", code, MatchMode.ANYWHERE));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Department getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenterDept", FetchMode.JOIN);
        return (Department) criteria.uniqueResult();
    }

    @Override
    public Department getByLevelOneAndCompany(String orgLevel, Long companyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("company", "co", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("orgLevel", orgLevel));
        criteria.add(Restrictions.eq("co.id", companyId));
        return (Department) criteria.uniqueResult();

    }

    @Override
    public List<Department> listChildGetByParentId(Long parentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("department", "dp", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("dp.id", parentId));
        criteria.setFetchMode("departments", FetchMode.JOIN);
        criteria.setFetchMode("departments.department", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    @Override
    public void saveAndMerge(Department department) throws Exception {
        getCurrentSession().update(department);
        getCurrentSession().flush();
    }
}
