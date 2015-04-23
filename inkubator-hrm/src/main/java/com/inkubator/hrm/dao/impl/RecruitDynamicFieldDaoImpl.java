/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitDynamicFieldDao;
import com.inkubator.hrm.entity.RecruitDynamicField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "recruitDynamicFieldDao")
@Lazy
public class RecruitDynamicFieldDaoImpl extends IDAOImpl<RecruitDynamicField> implements RecruitDynamicFieldDao{

    @Override
    public Class<RecruitDynamicField> getEntityClass() {
        return RecruitDynamicField.class;
    }
    
}
