package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import java.util.List;

/**
 *
 * @author rizkykojek
 */
public interface RecruitSelectionApplicantPassedDao extends IDAO<RecruitSelectionApplicantPassed> {

    public List<RecruitSelectionApplicantPassed>getAllWithPlacementStatus(String status);
}
