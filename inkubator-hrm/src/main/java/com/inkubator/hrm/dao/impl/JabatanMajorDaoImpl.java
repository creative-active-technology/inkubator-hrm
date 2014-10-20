/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanMajorDao;
import com.inkubator.hrm.entity.JabatanMajor;
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
public class JabatanMajorDaoImpl extends IDAOImpl<JabatanMajor> implements JabatanMajorDao{

    @Override
    public Class<JabatanMajor> getEntityClass() {
        return JabatanMajor.class;
    }

    @Override
    public List<JabatanMajor> getAllDataByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb");
        criteria.setFetchMode("major", FetchMode.JOIN);
        criteria.add(Restrictions.eq("jb.id", jabatanId));
        return criteria.list();
    }
    
}
