package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantInitialDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantInitial;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantInitialDao")
@Lazy
public class RecruitSelectionApplicantInitialDaoImpl extends IDAOImpl<RecruitSelectionApplicantInitial> implements RecruitSelectionApplicantInitialDao {

    @Override
    public Class<RecruitSelectionApplicantInitial> getEntityClass() {
        return RecruitSelectionApplicantInitial.class;
    }

}
