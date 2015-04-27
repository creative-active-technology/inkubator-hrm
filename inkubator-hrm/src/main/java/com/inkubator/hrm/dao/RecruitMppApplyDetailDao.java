package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.List;
import org.hibernate.criterion.Order;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
public interface RecruitMppApplyDetailDao extends IDAO<RecruitMppApplyDetail> {

//    public List<RecruitMppApplyDetail> getByParam(AnnouncementSearchParameter searchParameter, int firstResult, int maxResults, Order order);
//
//    public Long getTotalByParam(AnnouncementSearchParameter searchParameter);
//
//	public RecruitMppApplyDetail getEntityByPkWithDetail(Long id);
    
    public Long getTotalByRecruitMppApplyId(Long recruitMppApplyId);
}
