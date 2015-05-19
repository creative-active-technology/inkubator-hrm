package com.inkubator.hrm.dao;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.RecruitHireApplyDetail;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import java.util.List;
import org.hibernate.criterion.Order;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitHireApplyDetailDao extends IDAO<RecruitHireApplyDetail> {
    public List<RecruitHireApplyDetail> getListWithDetailByRecruitHireApplyId(Long recruitHireApplyId);
}
