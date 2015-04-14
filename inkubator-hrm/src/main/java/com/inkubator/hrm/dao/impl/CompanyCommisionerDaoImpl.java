/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CompanyCommisionerDao;
import com.inkubator.hrm.entity.CompanyCommisioner;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "companyCommisionerDao")
@Lazy
public class CompanyCommisionerDaoImpl  extends IDAOImpl<CompanyCommisioner> implements CompanyCommisionerDao {

    @Override
    public Class<CompanyCommisioner> getEntityClass() {
         return CompanyCommisioner.class;
    }

    @Override
    public List<CompanyCommisioner> getEntityByCompanyId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("company", "company", JoinType.LEFT_OUTER_JOIN);
        criteria.add(Restrictions.eq("company.id", id));
        return criteria.list();
    }
    
}
