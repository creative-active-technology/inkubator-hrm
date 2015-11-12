package com.inkubator.hrm.dao;

import java.util.List;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitSelectionApplicantSchedulleDetailDao extends IDAO<RecruitSelectionApplicantSchedulleDetail> {

    public List<RecruitSelectionApplicantSchedulleDetail> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
            Long applicantId, Long selectionApplicantSchedulleId);

    public List<RecruitSelectionApplicantSchedulleDetail> getListByRecruitSelectionApplicantSchedulleId(Long recruitSelectionApplicantSchedulleId);

    public List<RecruitSelectionApplicantSchedulleDetail> getAllByApplicatIdAndMaxListOrder(Long applicantId);
}
