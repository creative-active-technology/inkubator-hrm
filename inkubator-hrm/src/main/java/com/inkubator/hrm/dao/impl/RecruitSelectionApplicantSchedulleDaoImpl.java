package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulle;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulle> implements RecruitSelectionApplicantSchedulleDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulle> getEntityClass() {
        return RecruitSelectionApplicantSchedulle.class;
    }

}
