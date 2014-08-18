/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioKeahlianDao;
import com.inkubator.hrm.entity.BioKeahlian;
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
@Repository(value = "bioKeahlianDao")
@Lazy
public class BioKeahlianDaoImpl extends IDAOImpl<BioKeahlian> implements BioKeahlianDao{

    @Override
    public Class<BioKeahlian> getEntityClass() {
        return BioKeahlian.class;
    }

    @Override
    public BioKeahlian getAllDataByPK(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("biodata", FetchMode.JOIN);
        return (BioKeahlian) criteria.uniqueResult();
    }

    @Override
    public List<BioKeahlian> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("biodata.id", bioDataId));
        return criteria.list();
    }
    
}
