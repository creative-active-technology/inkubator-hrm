/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitLetterSelectionDao;
import com.inkubator.hrm.entity.RecruitLetterSelection;
import com.inkubator.hrm.entity.RecruitSelectionType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "recruitLetterSelectionDao")
@Lazy
public class RecruitLetterSelectionDaoImpl  extends IDAOImpl<RecruitLetterSelection> implements RecruitLetterSelectionDao{

    @Override
    public Class<RecruitLetterSelection> getEntityClass() {
      return RecruitLetterSelection.class;
    }

 
    
}
