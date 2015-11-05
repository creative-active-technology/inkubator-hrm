package com.inkubator.hrm.service;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailService extends IService<RecruitMppApplyDetail> {
    
    public List<RecruitMppApplyDetail> getListWithDetailByRecruitMppApplyId(Long recruitMppApplyId) throws Exception;
    
    public Long getRecruitPlanByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId) throws Exception;
    
    public List<RecruitMppApplyDetailViewModel> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalDataByParam(RecruitMppApplyDetailSearchParameter searchParameter) throws Exception;
    
    public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId) throws Exception;
    
    public List<RecruitMppApplyDetailViewModel> getListPerMonthByMppPeriodIdAndJabatanId(Long mppPeriodId, Long jabatanId) throws Exception;
    
    public List<RecruitMppApplyDetail> getListInSelectedMppPeriodIdWithApprovalStatus(Long recruitMppPeriodId, Integer approvalStatus) throws Exception;
    
    public Boolean isJabatanMppExistOnSelectedMppPeriod(Long jabatanId, Long recruitMppPeriodId) throws Exception;
    
    public List<RecruitMppApplyDetail> getAllDataJabatanByRecruitMppApplyId(Long recruitMppApplyId) throws Exception;
    
    public List<RecruitMppApplyDetail> getListByJabatanIdAndMppPeriodId(Long jabatanId, Long recruitMppPeriodId) throws Exception;

	public RecruitMppApplyDetail getEntityByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId)  throws Exception;
    
    public List<RecruitMppApplyDetail> getAllDataWithDetail() throws Exception;
}
