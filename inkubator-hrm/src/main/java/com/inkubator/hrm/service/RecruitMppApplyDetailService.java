package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailService extends IService<RecruitMppApplyDetail> {
    
    public List<RecruitMppApplyDetail> getListWithDetailByRecruitMppApplyId(Long recruitMppApplyId) throws Exception;
    
    public Long getRecruitPlanByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId) throws Exception;
    
    public List<RecruitMppApplyDetail> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDataByParam(RecruitMppApplyDetailSearchParameter searchParameter) throws Exception;
    
    public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId) throws Exception;
    
}
