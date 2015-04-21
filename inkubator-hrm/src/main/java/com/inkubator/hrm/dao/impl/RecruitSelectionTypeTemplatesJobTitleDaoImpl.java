/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionTypeTemplatesJobTitleDao;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplatesJobTitle;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni.fahri
 */
@Repository(value = "recruitSelectionTypeTemplatesJobTitleDao")
@Lazy
public class RecruitSelectionTypeTemplatesJobTitleDaoImpl extends IDAOImpl<RecruitSelectionTypeTemplatesJobTitle> implements RecruitSelectionTypeTemplatesJobTitleDao {

    @Override
    public Class<RecruitSelectionTypeTemplatesJobTitle> getEntityClass() {
        return RecruitSelectionTypeTemplatesJobTitle.class;
    }

    @Override
    public List<RecruitSelectionTypeTemplatesJobTitle> getByRecruitSelectionTypeTemplatesId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("recruitSelectionTypeTemplates", "dp");
        criteria.add(Restrictions.eq("dp.id", id));
        criteria.addOrder(Order.desc("jabatan"));
        criteria.setFetchMode("recruitSelectionTypeTemplates", FetchMode.JOIN);
        return criteria.list();
    }

}
