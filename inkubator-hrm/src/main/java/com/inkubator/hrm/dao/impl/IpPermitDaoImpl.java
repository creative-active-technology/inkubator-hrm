/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.IpPermitDao;
import com.inkubator.hrm.entity.IpPermit;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "ipPermitDao")
@Lazy
public class IpPermitDaoImpl extends IDAOImpl<IpPermit> implements IpPermitDao {

    @Override
    public Class<IpPermit> getEntityClass() {
        return IpPermit.class;
    }

    @Override
    public List<IpPermit> getByIpHeader(int ipHeader) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("fromAddress1", ipHeader));
        return criteria.list();
    }

}
