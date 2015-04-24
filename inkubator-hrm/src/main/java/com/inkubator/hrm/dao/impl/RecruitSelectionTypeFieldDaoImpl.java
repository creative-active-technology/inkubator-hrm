/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionTypeFieldDao;
import com.inkubator.hrm.entity.RecruitSelectionTypeField;
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
@Repository(value = "recruitSelectionTypeFieldDao")
@Lazy
public class RecruitSelectionTypeFieldDaoImpl extends IDAOImpl<RecruitSelectionTypeField> implements RecruitSelectionTypeFieldDao {

    @Override
    public Class<RecruitSelectionTypeField> getEntityClass() {
        return RecruitSelectionTypeField.class;
    }

    @Override
    public List<RecruitSelectionTypeField> getEntityByRecruitSelectionTypeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitSelectionType.id", id));
        criteria.setFetchMode("recruitSelectionType", FetchMode.JOIN);
        criteria.setFetchMode("recruitDynamicField", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public void deleteAllDataBySelectionTypeId(Long id) {
        String hqlDeleteSelectionTypeField = "delete from RecruitSelectionTypeField rstf where rstf.recruitSelectionType.id = :id";
        int deletedSelectionType = getCurrentSession().createQuery( hqlDeleteSelectionTypeField ).setString( "id", String.valueOf(id) ).executeUpdate();
    }

}
