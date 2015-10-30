package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitHireApplyDao extends IDAO<RecruitHireApply> {

    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter);

    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter);

    public Long getTotalDataByReqHireCode(String reqHireCode);

    public RecruitHireApply getEntityWithDetailByPk(Long recruitHireApplyId);

    public Long getCurrentMaxId();

    public RecruitHireApply getEntityWithDetailByActivityNumber(String activityNumber);
    
    public RecruitHireApply getEntityByJabatanId(Long jabatanId);
    
    public List<RecruitHireApply> getAllDataWithDetail();
    
}
