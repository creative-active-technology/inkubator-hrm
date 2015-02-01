/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.common.util.DateTimeUtil;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PaySalaryComponentDao;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.model.PayComponentDataExceptionModelView;
import com.inkubator.hrm.web.search.PayComponentDataExceptionSearchParameter;
import com.inkubator.hrm.web.search.PaySalaryComponentSearchParameter;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;

import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "paySalaryComponentDao")
@Lazy
public class PaySalaryComponentDaoImpl extends IDAOImpl<PaySalaryComponent> implements PaySalaryComponentDao {

    @Override
    public Class<PaySalaryComponent> getEntityClass() {
        return PaySalaryComponent.class;
    }

    @Override
    public List<PaySalaryComponent> getByParamWithDetail(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryJurnal", FetchMode.JOIN);
        criteria.setFetchMode("taxComponent", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalResourceTypeByParam(PaySalaryComponentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PaySalaryComponent getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryJurnal", FetchMode.JOIN);
        criteria.setFetchMode("taxComponent", FetchMode.JOIN);;
        criteria.add(Restrictions.eq("id", id));
        return (PaySalaryComponent) criteria.uniqueResult();
    }

    private void doSearchByParam(PaySalaryComponentSearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getName() != null) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (searchParameter.getCode() != null) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<PaySalaryComponent> getAllDataComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchComponentUploadByParam(searchParameter, criteria);
        /*criteria.setFetchMode("payTempUploadDatas", FetchMode.JOIN);
         criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);*/
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();

    }

    @Override
    public Long getTotalComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchComponentUploadByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchComponentUploadByParam(PaySalaryComponentSearchParameter searchParameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(searchParameter.getName())) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getCode())) {
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
        criteria.createAlias("modelComponent", "modelComponent");
        criteria.add(Restrictions.eq("modelComponent.spesific", HRMConstant.MODEL_COMP_UPLOAD));
    }

    @Override
    public void saveAndMerge(PaySalaryComponent paySalaryComponent) {
        getCurrentSession().update(paySalaryComponent);
        getCurrentSession().flush();
    }

    @Override
    public PaySalaryComponent getByEployeeTypeIdComponentIdAndJoinDate(Long typeId, Long componentId, Date joinDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryEmpTypes", "payType");
        criteria.createAlias("payType.employeeType", "employeeType");
        criteria.add(Restrictions.eq("id", componentId));
        criteria.add(Restrictions.eq("employeeType.id", typeId));
        int timeTmb = 0;
        try {
            timeTmb = DateTimeUtil.getTotalDay(joinDate, new Date());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        criteria.add(Restrictions.ge("activeFromTmb", timeTmb));
        return (PaySalaryComponent) criteria.uniqueResult();
    }

    @Override
    public List<PaySalaryComponent> getAllDataByEmpTypeIdAndActiveFromTmAndIdNotIn(Long empTypeId, int fromTbm, List<Long> componentIds) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("paySalaryEmpTypes", "payType");
        criteria.createAlias("payType.employeeType", "employeeType");
        criteria.add(Restrictions.eq("employeeType.id", empTypeId));
        criteria.add(Restrictions.le("activeFromTmb", fromTbm));
        if (!componentIds.isEmpty()) {
            criteria.add(Restrictions.not(Restrictions.in("id", componentIds)));
        }
        return criteria.list();
    }

    @Override
    public Long getTotalByModelComponentAndModelReferensi(Long modelComponentId, Integer modelReferensi) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.add(Restrictions.eq("modelComponent.id", modelComponentId));
        criteria.add(Restrictions.eq("modelReffernsil", modelReferensi));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByModelComponentAndModelReferensiAndNotId(Long modelComponentId, Integer modelReferensi, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.setFetchMode("modelComponent", FetchMode.JOIN);
        criteria.add(Restrictions.eq("modelComponent.id", modelComponentId));
        criteria.add(Restrictions.eq("modelReffernsil", modelReferensi));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PaySalaryComponent getEntityBySpecificModelComponent(Integer specific) {
        PaySalaryComponent paySalaryComponent = null;

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("modelComponent", "modelComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("modelComponent.spesific", specific));
        criteria.addOrder(Order.desc("createdOn"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(1);

        if (!criteria.list().isEmpty()) {
            paySalaryComponent = (PaySalaryComponent) criteria.list().get(0);
        }

        return paySalaryComponent;
    }

    @Override
    public List<PayComponentDataExceptionModelView> getByParamWithDetailForDataException(PayComponentDataExceptionSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("SELECT B.id AS paySalaryComponentId, B.name AS name, B.code AS code, count(A.emp_id) AS jumlahKaryawan, sum(A.nominal) AS jumlahNominal");
        query.append(" FROM hrm.pay_salary_component B");
        query.append(" LEFT JOIN hrm.pay_component_data_exception A ON A.pay_component_id = B.id");
        if (searchParameter.getCode() != null) {
            query.append(" WHERE B.code like '%" + searchParameter.getCode() + "%'");
        } else if (searchParameter.getName() != null) {
            query.append(" WHERE B.name like '%" + searchParameter.getName() + "%'");
        }
        query.append(" GROUP BY B.name");
        if (order.toString().contains("code") || order.toString().contains("name") || order.toString().contains("jumlahKaryawan") || order.toString().contains("jumlahNominal")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by B." + order);
        }
        
        query.append(" LIMIT " + firstResult + ", " + maxResults);

        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(PayComponentDataExceptionModelView.class))
                .list();

    }

    @Override
    public Long getTotalByParamDataException(PayComponentDataExceptionSearchParameter searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(B.name)");
        query.append(" FROM hrm.pay_salary_component B");
        query.append(" LEFT JOIN hrm.pay_component_data_exception A ON A.pay_component_id = B.id");
        if (searchParameter.getCode() != null) {
            query.append(" WHERE B.code like '%" + searchParameter.getCode() + "%'");
        } else if (searchParameter.getName() != null) {
            query.append(" WHERE B.name like '%" + searchParameter.getName() + "%'");
        }
        query.append(" GROUP BY B.name) as totalData");
        
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());

    }

    @Override
    public List<Integer> getAllModelReferensiId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setProjection(Projections.property("modelReffernsil"));
        return criteria.list();
    }
}
