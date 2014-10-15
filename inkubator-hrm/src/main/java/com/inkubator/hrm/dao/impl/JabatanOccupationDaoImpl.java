/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanOccupationDao;
import com.inkubator.hrm.entity.JabatanProfesi;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository
@Lazy
public class JabatanOccupationDaoImpl extends IDAOImpl<JabatanProfesi> implements JabatanOccupationDao{

    @Override
    public Class<JabatanProfesi> getEntityClass() {
        return JabatanProfesi.class;
    }

    @Override
    public List<JabatanProfesi> getAllDataByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb");
        criteria.setFetchMode("occupationType", FetchMode.JOIN);
        criteria.add(Restrictions.eq("jb.id", jabatanId));
        return criteria.list();
    }
    
}
