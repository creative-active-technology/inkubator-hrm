/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempKalkulasiEmpPajakDao;
import com.inkubator.hrm.entity.PayTempKalkulasiEmpPajak;
import com.inkubator.hrm.web.model.PayTempKalkulasiEmpPajakModel;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
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

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiEmpPajakDao")
@Lazy
public class PayTempKalkulasiEmpPajakDaoImpl extends IDAOImpl<PayTempKalkulasiEmpPajak> implements PayTempKalkulasiEmpPajakDao {

    @Override
    public Class<PayTempKalkulasiEmpPajak> getEntityClass() {
        return PayTempKalkulasiEmpPajak.class;
    }

    @Override
    public void deleteAllData() {
        Query query = getCurrentSession().createQuery("delete from PayTempKalkulasiEmpPajak");
        query.executeUpdate();
    }

    @Override
    public List<PayTempKalkulasiEmpPajakModel> getByParam() {
        final StringBuilder query = new StringBuilder("select B.id as taxId, B.name as taxName, sum(A.nominal) as nominal from PayTempKalkulasiEmpPajak A");
        query.append(" inner join A.taxComponent B");
        query.append(" group by B.name");
        query.append(" order by B.id");
        return getCurrentSession().createQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(PayTempKalkulasiEmpPajakModel.class))
                .list();

    }

    @Override
    public Long getTotalPayTempKalkulasiEmpPajakByParam() {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(B.name) FROM pay_temp_kalkulasi_emp_pajak A INNER JOIN tax_component B WHERE A.tax_component_id = B.id");
        query.append(" GROUP BY B.name) AS jumlahRow");
      
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());

    }

    @Override
    public List<PayTempKalkulasiEmpPajak> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long taxComponentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria, taxComponentId);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPayTempKalkulasiEmpPajakByParamForDetail(String searchParameter, Long taxComponentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria, taxComponentId);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doSearchByParam(String searchParameter, Criteria criteria, Long taxComponentId) {
        criteria.createAlias("taxComponent", "taxComponent", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("taxComponent.id", taxComponentId));
        if (StringUtils.isNotEmpty(searchParameter)) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("empData.nik", searchParameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter, MatchMode.ANYWHERE));
            criteria.add(disjunction);
//            criteria.add(Restrictions.like("bioData.firstName", parameter, MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

	@Override
	public PayTempKalkulasiEmpPajak getEntityByEmpDataIdAndTaxComponentId(Long empDataId, Long taxComponentId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("taxComponent.id", taxComponentId));
		criteria.add(Restrictions.eq("empData.id", empDataId));
		
		return (PayTempKalkulasiEmpPajak) criteria.uniqueResult();
	}

    @Override
    public List<PayTempKalkulasiEmpPajak> getAllDataByEmpDataId(Long empDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("taxComponent", "taxComponent", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("empData.id", empDataId));
        criteria.addOrder(Order.asc("taxComponent.id"));
        return criteria.list();
    }
}
