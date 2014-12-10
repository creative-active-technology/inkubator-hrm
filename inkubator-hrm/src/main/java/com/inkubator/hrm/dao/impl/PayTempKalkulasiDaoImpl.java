/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PayTempKalkulasiDao;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
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

/**
 *
 * @author denifahri
 */
@Repository(value = "payTempKalkulasiDao")
@Lazy
public class PayTempKalkulasiDaoImpl extends IDAOImpl<PayTempKalkulasi> implements PayTempKalkulasiDao {

    @Override
    public Class<PayTempKalkulasi> getEntityClass() {
        return PayTempKalkulasi.class;
    }

    @Override
    public void saveBatch(List<PayTempKalkulasi> data) {
        int counter = 0;
        for (PayTempKalkulasi kalkulasi : data) {
            getCurrentSession().save(kalkulasi);
            counter++;
            if (counter % 20 == 0) {
                getCurrentSession().flush();
                getCurrentSession().clear();
            }
        }
    }

    @Override
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("select B.code as code, B.name as name, count(A.empData) as jumlahKaryawan, sum(A.nominal) as nominal from PayTempKalkulasi A");
        query.append(" inner join A.paySalaryComponent B");
        if (searchParameter!= null) {
            query.append(" WHERE name like :name");
        }
        query.append(" group by B.name");
        if (order.toString().contains("jumlahKaryawan") || order.toString().contains("nominal")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by B." + order);
        }
        if (searchParameter != null) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameter("name", '%' + searchParameter + '%')
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayTempKalkulasiModel.class))
                    .list();
        } else {
            return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(PayTempKalkulasiModel.class))
                    .list();
        }
    }

    @Override
    public Long getTotalPayTempKalkulasiByParam(String searchParameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT count(B.name) FROM hrm.pay_temp_kalkulasi A INNER JOIN hrm.pay_salary_component B WHERE A.pay_salary_component_id = B.id");
        
        if (searchParameter!= null) {
            query.append(" AND B.name like :name ");
        }
        query.append(" GROUP BY B.name) AS jumlahRow");
        System.out.println(query.toString());
        if(searchParameter!= null){
            Query hbm = getCurrentSession().createSQLQuery(query.toString()).setParameter("name", '%' +searchParameter+ '%');
            return Long.valueOf(hbm.uniqueResult().toString());
        }else{
            Query hbm = getCurrentSession().createSQLQuery(query.toString());
            return Long.valueOf(hbm.uniqueResult().toString());
        }

    }

    private void doSearchByParam(PayTempKalkulasiSearchParameter parameter, Criteria criteria) {
        criteria.createAlias("paySalaryComponent", "paySalaryComponent", JoinType.INNER_JOIN);
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(parameter.getEmpData())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", parameter.getEmpData(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", parameter.getEmpData(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(parameter.getPaySalaryComponent())) {
            criteria.add(Restrictions.like("bioData.firstName", parameter.getPaySalaryComponent(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public PayTempKalkulasi getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("paySalaryComponent", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        return (PayTempKalkulasi) criteria.uniqueResult();
    }

}
