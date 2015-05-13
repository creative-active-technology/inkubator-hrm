package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitMppApplyDetailDao")
@Lazy
public class RecruitMppApplyDetailDaoImpl extends IDAOImpl<RecruitMppApplyDetail> implements RecruitMppApplyDetailDao {

    @Override
    public Class<RecruitMppApplyDetail> getEntityClass() {
        return RecruitMppApplyDetail.class;
    }

    @Override
    public Long getTotalByRecruitMppApplyId(Long recruitMppApplyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppApply", FetchMode.JOIN);
        criteria.add(Restrictions.eq("recruitMppApply,id", recruitMppApplyId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<RecruitMppApplyDetail> getListWithDetailByRecruitMppApplyId(Long recruitMppApplyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppApply", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.add(Restrictions.eq("recruitMppApply.id", recruitMppApplyId));
        return criteria.list();
    }

    @Override
    public Long getRecruitPlanByJabatanIdAndMppPeriodId(Long jabatanId, Long mppPeriodId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        
        criteria.createAlias("jabatan", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("recruitMppApply", "recruitMppApply", JoinType.INNER_JOIN);
        criteria.createAlias("recruitMppApply.recruitMppPeriod", "recruitMppPeriod", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("jabatan.id", jabatanId));
        criteria.add(Restrictions.eq("recruitMppPeriod.id", mppPeriodId));
        
        RecruitMppApplyDetail recruitMppApplyDetail = (RecruitMppApplyDetail) criteria.uniqueResult();
        if(null == recruitMppApplyDetail){
            return 0l;
        }else{
            return recruitMppApplyDetail.getRecruitPlan().longValue();
        }

    }

}
