package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "approvalActivityDao")
@Lazy
public class ApprovalActivityDaoImpl extends IDAOImpl<ApprovalActivity> implements ApprovalActivityDao {

    @Override
    public Class<ApprovalActivity> getEntityClass() {
        return ApprovalActivity.class;

    }

    @Override
    public List<ApprovalActivity> getRequestHistory(String userName) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("requestBy", userName));
        criteria.add(Restrictions.not(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING)));
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getReguestHistoryById(long id) {
        ProjectionList proList = Projections.projectionList();

        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria activityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("id", id))
                .setProjection(proList);

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Property.forName("activityNumber").eq(activityNumber));
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getPendingRequest(String userName) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("requestBy", userName));
        criteria.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING));
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getPendingTask(String userName) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvedBy", userName));
        criteria.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(ApprovalActivitySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public ApprovalActivity getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return (ApprovalActivity) criteria.uniqueResult();
    }

    private void doSearchByParam(ApprovalActivitySearchParameter searchParameter, Criteria criteria) {
        if (searchParameter.getRequestBy()!= null) {
        	criteria.add(Restrictions.like("requestBy", searchParameter.getRequestBy(), MatchMode.ANYWHERE));
        } 
        
        criteria.add(Restrictions.isNotNull("id"));
    }
}
