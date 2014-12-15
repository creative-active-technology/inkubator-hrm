/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.TaxFreeDao;
import com.inkubator.hrm.entity.TaxFree;
import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "taxFreeDao")
@Lazy
public class TaxFreeDaoImpl extends IDAOImpl<TaxFree> implements TaxFreeDao{

    @Override
    public Class<TaxFree> getEntityClass() {
        return TaxFree.class;
    }

    @Override
    public TaxFree getEntityByTfStatusAndIncPerson(String tfStatus, Integer incPerson) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("tfStatus", tfStatus));
        criteria.add(Restrictions.eq("incPerson", incPerson));
        return (TaxFree) criteria.uniqueResult();
    }
    
}
