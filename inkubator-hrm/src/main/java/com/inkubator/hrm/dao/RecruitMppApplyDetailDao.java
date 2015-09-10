package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

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
    
    public List<RecruitMppApplyDetail> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalDataByParam(RecruitMppApplyDetailSearchParameter searchParameter) ;
    
    public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId);
}
