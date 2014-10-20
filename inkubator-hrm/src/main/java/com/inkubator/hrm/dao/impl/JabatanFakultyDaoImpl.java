/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanFakultyDao;
import com.inkubator.hrm.entity.JabatanFakulty;
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
public class JabatanFakultyDaoImpl extends IDAOImpl<JabatanFakulty> implements JabatanFakultyDao {

    @Override
    public Class<JabatanFakulty> getEntityClass() {
        return JabatanFakulty.class;
    }

    @Override
    public List<JabatanFakulty> getAllDataByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb");
        criteria.setFetchMode("faculty", FetchMode.JOIN);
        criteria.add(Restrictions.eq("jb.id", jabatanId));
        return criteria.list();
    }
    
}
