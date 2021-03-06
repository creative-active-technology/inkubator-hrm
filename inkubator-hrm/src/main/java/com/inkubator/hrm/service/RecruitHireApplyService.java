package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.RecruitHireApply;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitmentScheduleSettingViewModel;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import com.inkubator.hrm.web.search.RecruitmentScheduleSettingSearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitHireApplyService extends IService<RecruitHireApply>, BaseApprovalService {

    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception;
    
    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
        
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) throws Exception;
    
//    public void saveRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception;
    
    public String saveRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception;
    
    public RecruitHireApply getEntityByPkWithDetail(Long id) throws Exception;

    public void updateRecruitHireWithApproval(RecruitHireApply recruitHireApply, String activityNumber) throws Exception;
    
    public Long getCurrentMaxId() throws Exception;
    
    public RecruitHireApply getEntityWithDetailByActivityNumber(String activityNumber) throws Exception;
    
    public List<RecruitHireApply> getAllDataWithDetail() throws Exception;
    
}
