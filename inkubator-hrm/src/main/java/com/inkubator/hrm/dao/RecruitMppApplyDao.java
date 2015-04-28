package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.AdmonitionType;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.web.search.AdmonitionTypeSearchParameter;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public interface RecruitMppApplyDao extends IDAO<RecruitMppApply> {

	public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable);

	public Long getTotalByParam(RecruitMppApplySearchParameter parameter);
        
        public RecruitMppApply getEntityWithDetailById(Long id);

}
