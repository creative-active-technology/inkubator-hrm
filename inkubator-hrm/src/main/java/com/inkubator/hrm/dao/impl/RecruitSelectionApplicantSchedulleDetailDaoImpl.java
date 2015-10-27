package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDetailDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulleDetail> implements RecruitSelectionApplicantSchedulleDetailDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulleDetail> getEntityClass() {
        return RecruitSelectionApplicantSchedulleDetail.class;
    }

}
