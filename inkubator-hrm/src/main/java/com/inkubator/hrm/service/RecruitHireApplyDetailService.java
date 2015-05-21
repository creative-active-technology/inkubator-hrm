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
public interface RecruitHireApplyDetailService extends IService<RecruitHireApplyDetail> {

    public List<RecruitHireApplyDetail> getListWithDetailByRecruitHireApplyId(Long recruitHireApplyId) throws Exception;
    
}
