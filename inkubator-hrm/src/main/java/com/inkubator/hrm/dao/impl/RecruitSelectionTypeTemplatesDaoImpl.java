/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionTypeTemplatesDao;
import com.inkubator.hrm.entity.RecruitSelectionTypeTemplates;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author deni.fahri
 */
@Repository(value = "recruitSelectionTypeTemplatesDao")
@Lazy
public class RecruitSelectionTypeTemplatesDaoImpl extends IDAOImpl<RecruitSelectionTypeTemplates> implements RecruitSelectionTypeTemplatesDao {

    @Override
    public Class<RecruitSelectionTypeTemplates> getEntityClass() {
        return RecruitSelectionTypeTemplates.class;
    }

    @Override
    public RecruitSelectionTypeTemplates getLevelOne(String root) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("code", root));
        return (RecruitSelectionTypeTemplates) criteria.uniqueResult();

    }

    @Override
    public List<RecruitSelectionTypeTemplates> getByParentId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("recruitSelectionTypeTemplates", "dp", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("dp.id", id));
        criteria.setFetchMode("recruitSelectionTypeTemplateses", FetchMode.JOIN);
        criteria.setFetchMode("recruitSelectionTypeTemplateses.recruitSelectionTypeTemplates", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void saveAndMerge(RecruitSelectionTypeTemplates rstt) {
         getCurrentSession().update(rstt);
        getCurrentSession().flush();
    }

}
