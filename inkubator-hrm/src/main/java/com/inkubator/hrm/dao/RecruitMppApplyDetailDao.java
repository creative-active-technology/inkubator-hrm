package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailDao extends IDAO<RecruitMppApplyDetail> {
    
    public Long getTotalByRecruitMppApplyId(Long recruitMppApplyId);
    
    public List<RecruitMppApplyDetail> getListWithDetailByRecruitMppApplyId(Long recruitMppApplyId);
    
    public Long getRecruitPlanByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId);
    
    public RecruitMppApplyDetail getEntityByPkWithDetail(Long recruitMppApplyId);
    
    public List<RecruitMppApplyDetailViewModel> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalDataByParam(RecruitMppApplyDetailSearchParameter searchParameter) ;
    
    public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId);
    
    public Long getTotalNumberOfMppByJabatanIdAndDateRange(Long jabatanId, Date startDate, Date endDate);
    
    public RecruitMppApplyDetail getEntityByDateRangeAndJabatanId(Long jabatanId, Date startDate, Date endDate);
    
    public List<RecruitMppApplyDetail> getListInSelectedMppPeriodIdWithApprovalStatus(Long recruitMppPeriodId, Integer approvalStatus);
    
    public Boolean isJabatanMppExistOnSelectedMppPeriod(Long jabatanId, Long recruitMppPeriodId);
    
    public List<RecruitMppApplyDetail> getAllDataJabatanByRecruitMppApplyId(Long recruitMppApplyId);
}
