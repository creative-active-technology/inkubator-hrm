package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AnnouncementDao;
import com.inkubator.hrm.dao.RecruitMppApplyDetailDao;
import com.inkubator.hrm.entity.Announcement;
import com.inkubator.hrm.entity.RecruitMppApplyDetail;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.RecruitMppApplyDetailViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.search.AnnouncementSearchParameter;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.RecruitMppApplyDetailSearchParameter;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
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

	@Override
	public RecruitMppApplyDetail getEntityByPkWithDetail(Long recruitMppApplyId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("jabatan.department", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", recruitMppApplyId));
        return (RecruitMppApplyDetail) criteria.uniqueResult();
	}

	@Override
	public List<RecruitMppApplyDetailViewModel> getAllDataByParam(RecruitMppApplyDetailSearchParameter searchParameter,	int firstResult, int maxResults, Order order) {
		
		final StringBuilder queryString = new StringBuilder("SELECT detail.id AS id, jabatan.code AS jabatanKode, jabatan.name AS jabatanName,");
        queryString.append(" jabatan.id AS jabatanId, period.periodeStart AS periodeStart, period.periodeEnd AS periodeEnd,");
        queryString.append(" SUM(detail.recruitPlan) AS mpp FROM RecruitMppApplyDetail detail ");
        queryString.append(" INNER JOIN detail.recruitMppApply recruitMppApply ");
        queryString.append(" INNER JOIN detail.jabatan jabatan ");
        queryString.append(" INNER JOIN recruitMppApply.recruitMppPeriod period  ");
        //queryString.append(doSearchByParam(searchParameter));
        queryString.append(" GROUP BY period.id, jabatan.code ");
        
        Query hbm = getCurrentSession().createQuery(queryString.toString());
                /*.setParameter("jabatanCode", searchParameter.getJabatanCode())
                .setParameter("jabatanCode", searchParameter.getJabatanName());*/
        
        return hbm.setMaxResults(maxResults)
                .setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(RecruitMppApplyDetailViewModel.class))
                .list();
        
      
	}

	@Override
	public Long getTotalDataByParam( RecruitMppApplyDetailSearchParameter searchParameter) {
		
		final StringBuilder queryString = new StringBuilder("SELECT COUNT(*) ");
        queryString.append(" FROM RecruitMppApplyDetail detail ");
        queryString.append(" INNER JOIN detail.recruitMppApply recruitMppApply ");
        queryString.append(" INNER JOIN detail.jabatan jabatan ");
        queryString.append(" INNER JOIN recruitMppApply.recruitMppPeriod period  ");
        //queryString.append(doSearchByParam(searchParameter));
        queryString.append(" GROUP BY period.id, jabatan.code ");
        
        Query hbm = getCurrentSession().createQuery(queryString.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
	}
	
	private String doSearchByParam( RecruitMppApplyDetailSearchParameter searchParameter) {
		
		StringBuffer stringBuffer = new StringBuffer();
        
        if (searchParameter.getJabatanCode() != null) {
        	stringBuffer.append(" WHERE jabatan.code LIKE :jabatanCode ");
            //criteria.add(Restrictions.like("jabatan.code", searchParameter.getJabatanCode(), MatchMode.START));
        }

        if (searchParameter.getJabatanName() != null) {
        	stringBuffer.append(" WHERE jabatan.name LIKE :jabatanName ");
            //criteria.add(Restrictions.like("jabatan.name", searchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

       /* if (searchParameter.getPeriode() != null) {
            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.like("bioData.firstName", dataSearchParameter.getName(), MatchMode.START));
//            disjunction.add(Restrictions.like("bioData.lastName", dataSearchParameter.getName(), MatchMode.START));
//            criteria.add(disjunction);
            criteria.add(Restrictions.ilike("bioData.combineName", searchParameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }*/
       
        return stringBuffer.toString();
    }

	@Override
	public RecruitMppApplyDetail getEntityWithDetail(Long idRecruitMppApplyDetailId) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("id", idRecruitMppApplyDetailId));
		criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApply", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppApply.recruitMppPeriod", FetchMode.JOIN);
		return (RecruitMppApplyDetail) criteria.uniqueResult();
	}

	@Override
	public Long getTotalNumberOfMppByJabatanIdAndDateRange(Long jabatanId, Date startDate, Date endDate) {
		 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		 criteria.setFetchMode("jabatan", FetchMode.JOIN);
		 criteria.add(Restrictions.eq("jabatan.id", jabatanId));
		 criteria.add(Restrictions.between("recruitMppMonth", startDate, endDate));
		 Long result = (Long) criteria.setProjection(Projections.sum("recruitPlan")).uniqueResult();
         return result == null ? 0l : result;
	}

}
