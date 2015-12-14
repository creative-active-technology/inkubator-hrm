/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LicenseAppDao;
import com.inkubator.hrm.entity.LicenseApp;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "licenseAppDao")
@Lazy
public class LicenseAppDaoImpl extends IDAOImpl<LicenseApp> implements LicenseAppDao {

    @Override
    public Class<LicenseApp> getEntityClass() {
        return LicenseApp.class;
    }

    @Override
    public LicenseApp getByStatus(String status) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("licenseStatus", status));
        return (LicenseApp) criteria.uniqueResult();

    }

}
