package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;

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
    
    public RecruitHireApply getEntityByPkWithDetail(Long id);
}
