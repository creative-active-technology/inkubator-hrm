/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanSpesifikasiDao;
import com.inkubator.hrm.entity.JabatanSpesifikasi;
import com.inkubator.hrm.entity.JabatanSpesifikasiId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
public class JabatanSpesifikasiDaoImpl extends IDAOImpl<JabatanSpesifikasi> implements JabatanSpesifikasiDao {

    @Override
    public Class<JabatanSpesifikasi> getEntityClass() {
        return JabatanSpesifikasi.class;
    }

    @Override
    public JabatanSpesifikasi getDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return (JabatanSpesifikasi) criteria.uniqueResult();
    }

    @Override
    public List<JabatanSpesifikasi> getAllDataByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("jabatan.id", jabatanId));
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public JabatanSpesifikasi getEntityByBioJabatanSpesifikasiId(JabatanSpesifikasiId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("specificationAbility", FetchMode.JOIN);
        return (JabatanSpesifikasi) criteria.uniqueResult();
    }

    @Override
    public Long getTotalEntityByBioJabatanSpesifikasiId(JabatanSpesifikasiId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

}
