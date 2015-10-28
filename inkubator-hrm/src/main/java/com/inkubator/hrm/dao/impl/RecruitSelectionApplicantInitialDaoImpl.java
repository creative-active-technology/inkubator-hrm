/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantInitialDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni, Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantInitialDao")
@Lazy
public class RecruitSelectionApplicantInitialDaoImpl extends IDAOImpl<RecruitSelectionApplicantInitial> implements RecruitSelectionApplicantInitialDao {

    @Override
    public Class<RecruitSelectionApplicantInitial> getEntityClass() {
        return RecruitSelectionApplicantInitial.class;
    }
}
