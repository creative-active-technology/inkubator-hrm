package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.web.model.MppApplyHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface RecruitMppApplyDao extends IDAO<RecruitMppApply> {

	public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RecruitMppApplySearchParameter parameter);
        
	public RecruitMppApply getEntityWithDetailById(Long id);

	public List<RecruitMppApplyViewModel> getUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter);

	public RecruitMppApply getEntityWithDetailByActivityNumber(String activityNumber);

	public Long getTotalDataByMppCode(String mppCode);

	public List<RecruitMppApply> getListWithDetailByApprovalStatus(Integer approvalStatus);

	public List<MppApplyHistoryViewModel> getAllDataMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter);

}
