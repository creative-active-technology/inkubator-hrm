/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.UnregGoljabDao;
import com.inkubator.hrm.entity.UnregGoljab;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni
 */
@Repository(value = "unregGoljabDao")
@Lazy
public class UnregGoljabDaoImpl extends IDAOImpl<UnregGoljab> implements UnregGoljabDao {

    @Override
    public Class<UnregGoljab> getEntityClass() {
        return UnregGoljab.class;
    }

    @Override
    public List<UnregGoljab> getAllDataByUnregSalaryId(Long unregSalaryId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("unregSalary", "us");
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("us.id", unregSalaryId));
        return criteria.list();
    }

}
