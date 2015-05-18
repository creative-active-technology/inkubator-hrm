package com.inkubator.hrm.service;

import com.inkubator.hrm.entity.RecruitHireApply;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;

/**
 *
 * @author WebGenX
 */
public interface RecruitHireApplyService extends IService<RecruitHireApply> {

    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter searchParameter) throws Exception;
    
    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) throws Exception;
        
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) throws Exception;
    
    public void saveRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception;
    
    public void updateRecruitHireWithApproval(RecruitHireApply recruitHireApply) throws Exception;
    
    public RecruitHireApply getEntityByPkWithDetail(Long id) throws Exception;
}
