/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitLettersDao;
import com.inkubator.hrm.entity.RecruitLetters;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */

@Repository(value = "recruitLettersDao")
@Lazy
public class RecruitLettersDaoImpl extends IDAOImpl<RecruitLetters> implements RecruitLettersDao {

    @Override
    public Class<RecruitLetters> getEntityClass() {
        return RecruitLetters.class;
    }

}
