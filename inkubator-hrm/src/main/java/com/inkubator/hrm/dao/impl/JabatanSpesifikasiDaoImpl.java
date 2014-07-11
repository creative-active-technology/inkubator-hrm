/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanSpesifikasiDao;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.web.search.JabatanSpesifikasiSearchParameter;
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

/**
 *
 * @author Deni
 */
@Repository(value = "jabatanSpesifikasiDao")
@Lazy
public class JabatanSpesifikasiDaoImpl extends IDAOImpl<JabatanSpesifikasi> implements JabatanSpesifikasiDao{

    @Override
    public Class<JabatanSpesifikasi> getEntityClass() {
        return JabatanSpesifikasi.class;
    }

    @Override
    public List<JabatanSpesifikasi> getByParam(JabatanSpesifikasiSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalJabatanSpesifikasiByParam(JabatanSpesifikasiSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    private void doSearchByParam(JabatanSpesifikasiSearchParameter parameter, Criteria criteria) {
        System.out.println(" heheh eksekusi");
        criteria.createAlias("jabatan", "jb");
        //criteria.createAlias("jb.jabatan", "jbb");
        criteria.createAlias("specificationAbility", "sa");
        if (StringUtils.isNotEmpty(parameter.getJabatan())) {
            criteria.add(Restrictions.like("jb.name", parameter.getJabatan(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getValue())) {
            criteria.add(Restrictions.like("value", parameter.getValue(), MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotEmpty(parameter.getSpecificationAbility())){
            criteria.add(Restrictions.like("sa.name", parameter.getValue(), MatchMode.ANYWHERE));
        }

    }

    @Override
    public List<JabatanSpesifikasi> getByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.add(Restrictions.eq("jabatan.id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalJabatanSpesifikasiByJabatan(JabatanSpesifikasiSearchParameter searchParameter, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.add(Restrictions.eq("jabatan.id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public JabatanSpesifikasi getDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return (JabatanSpesifikasi) criteria.uniqueResult();
    }
}
