/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitmenSelectionSeriesDetailDao;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetail;
import com.inkubator.hrm.entity.RecruitmenSelectionSeriesDetailId;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "recruitmenSelectionSeriesDetailDao")
@Lazy
public class RecruitmenSelectionSeriesDetailDaoImpl extends IDAOImpl<RecruitmenSelectionSeriesDetail> implements RecruitmenSelectionSeriesDetailDao {

    @Override
    public Class<RecruitmenSelectionSeriesDetail> getEntityClass() {
        return RecruitmenSelectionSeriesDetail.class;
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getByParam(int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getAllDataBySelectionSeriesId(Long id, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitmenSelectionSeries.id", id));
        criteria.setFetchMode("recruitmenSelectionSeries", FetchMode.JOIN);
        criteria.setFetchMode("recruitSelectionType", FetchMode.JOIN);
        criteria.setFetchMode("systemLetterReferenceByRejectLetterId", FetchMode.JOIN);
        criteria.setFetchMode("systemLetterReferenceByAcceptLetterId", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Integer getLastIndexBySelectionSeriesId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitmenSelectionSeries.id", id));
        criteria.setProjection(Projections.max("listOrder"));
        return (Integer) criteria.uniqueResult();
    }

    @Override
    public Long getTotalBySelectionSeriesId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitmenSelectionSeries.id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public RecruitmenSelectionSeriesDetail getByListOrderAndRecSelectionSeriesId(Integer number, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("listOrder", number));
        criteria.add(Restrictions.eq("recruitmenSelectionSeries.id", id));
        return (RecruitmenSelectionSeriesDetail) criteria.uniqueResult();
    }

    @Override
    public RecruitmenSelectionSeriesDetail getEntityByPk(RecruitmenSelectionSeriesDetailId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("recruitSelectionType", FetchMode.JOIN);
        return (RecruitmenSelectionSeriesDetail) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByPk(RecruitmenSelectionSeriesDetailId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<RecruitmenSelectionSeriesDetail> getEntityBySelectionTypeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitSelectionType.id", id));
        criteria.setFetchMode("recruitmenSelectionSeries", FetchMode.JOIN);
        return criteria.list();
    }

}
