package com.inkubator.hrm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.ApprovalActivityDao;
import com.inkubator.hrm.entity.ApprovalActivity;
import com.inkubator.hrm.entity.ApprovalDefinition;
import com.inkubator.hrm.web.search.ApprovalActivitySearchParameter;

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
    public List<ApprovalActivity> getRequestHistory(String userName, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));

        criteria.add(Restrictions.eq("requestBy", userName));
        criteria.add(Restrictions.not(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)));
        criteria.add(Restrictions.not(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED)));

        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
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
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));

        Disjunction disjStatus = Restrictions.disjunction();
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
        criteria.add(disjStatus);

        criteria.add(Restrictions.eq("requestBy", userName));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.addOrder(Order.desc("requestTime"));
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getPendingTask(String userName) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        Conjunction conjApprover = Restrictions.conjunction();
        conjApprover.add(Restrictions.eq("approvedBy", userName));
        conjApprover.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));

        Conjunction conjRequester = Restrictions.conjunction();
        conjRequester.add(Restrictions.eq("requestBy", userName));
        conjRequester.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(conjRequester);
        disjunction.add(conjApprover);

        criteria.add(disjunction);
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.addOrder(Order.desc("requestTime"));
        return criteria.list();
    }

    @Override
    public ApprovalActivity getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return (ApprovalActivity) criteria.uniqueResult();

    }

    @Override
    public List<ApprovalActivity> getAllDataWithAllRelation(ApprovalActivitySearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
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
        criteria.createAlias("approvalDefinition", "approvalDefinition", JoinType.INNER_JOIN);
        if (searchParameter.getRequestBy() != null) {
            criteria.add(Restrictions.like("requestBy", searchParameter.getRequestBy(), MatchMode.START));
        }
        if (searchParameter.getApprovedBy() != null) {
            criteria.add(Restrictions.like("approvedBy", searchParameter.getApprovedBy(), MatchMode.START));
        }
        if (StringUtils.isNotEmpty(searchParameter.getName())) {
            criteria.add(Restrictions.like("approvalDefinition.name", searchParameter.getName(), MatchMode.START));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<ApprovalActivity> getAllDataByActivityNumberWithDetail(String activityNumber, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("activityNumber", activityNumber));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.addOrder(order);
        return criteria.list();
    }

    public List<ApprovalActivity> getAllDataByPreviousActivityNumber(String previousActivityNumber, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("previousActivityNumber", previousActivityNumber));
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getDataNotSendEmailYet() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("notificationSend", Boolean.FALSE));
        return criteria.list();
    }

    @Override
    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs) {
        //get approval definition ids
        List<Long> ids = new ArrayList<Long>();
        for (ApprovalDefinition appDef : appDefs) {
            if (appDef.getId() != null) {
                ids.add(appDef.getId());
            }
        }

        boolean isStillHaveWaitingStatus = false;
        if (!ids.isEmpty()) {
            Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

            Disjunction disjStatus = Restrictions.disjunction();
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
            criteria.add(disjStatus);

            criteria.add(Restrictions.in("approvalDefinition.id", ids));
            isStillHaveWaitingStatus = criteria.list().size() > 0;
        }

        return isStillHaveWaitingStatus;
    }

    @Override
    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs, String requestBy) {
        //get approval definition ids
        List<Long> ids = new ArrayList<Long>();
        for (ApprovalDefinition appDef : appDefs) {
            if (appDef.getId() != null) {
                ids.add(appDef.getId());
            }
        }

        boolean isStillHaveWaitingStatus = false;
        if (!ids.isEmpty()) {
            Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

            Disjunction disjStatus = Restrictions.disjunction();
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
            criteria.add(disjStatus);

            criteria.add(Restrictions.eq("requestBy", requestBy));
            criteria.add(Restrictions.in("approvalDefinition.id", ids));
            isStillHaveWaitingStatus = criteria.list().size() > 0;
        }

        return isStillHaveWaitingStatus;
    }

    @Override
    public Boolean isStillHaveWaitingStatus(List<ApprovalDefinition> appDefs, String requestBy, Long typeSpecific) {
        //get approval definition ids
        List<Long> ids = new ArrayList<Long>();
        for (ApprovalDefinition appDef : appDefs) {
            if (appDef.getId() != null) {
                ids.add(appDef.getId());
            }
        }

        boolean isStillHaveWaitingStatus = false;
        if (!ids.isEmpty()) {
            Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

            Disjunction disjStatus = Restrictions.disjunction();
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
            disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
            criteria.add(disjStatus);

            criteria.add(Restrictions.eq("typeSpecific", typeSpecific));
            criteria.add(Restrictions.eq("requestBy", requestBy));
            criteria.add(Restrictions.in("approvalDefinition.id", ids));
            isStillHaveWaitingStatus = criteria.list().size() > 0;
        }

        return isStillHaveWaitingStatus;
    }

    @Override
    public Boolean isStillHaveWaitingStatus(Long appDefId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        Disjunction disjStatus = Restrictions.disjunction();
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
        criteria.add(disjStatus);

        criteria.add(Restrictions.eq("approvalDefinition.id", appDefId));
        return criteria.list().size() > 0;
    }

    @Override
    public Boolean isStillHaveWaitingStatus(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        Disjunction disjStatus = Restrictions.disjunction();
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
        criteria.add(disjStatus);

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("activityNumber", activityNumber));
        disjunction.add(Restrictions.eq("previousActivityNumber", activityNumber));
        criteria.add(disjunction);
        return criteria.list().size() > 0;
    }

    @Override
    public List<ApprovalActivity> getAllDataWaitingStatusApproval() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getByApprovalStatus(Integer approvalStatus) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalStatus", approvalStatus));
        return criteria.list();
    }

    @Override
    public void updateAndFlush(ApprovalActivity approvalActivity) {
        getCurrentSession().update(approvalActivity);
        getCurrentSession().flush();

    }

    @Override
    public ApprovalActivity getApprovalTimeByApprovalActivityNumber(String activityNumber) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("activityNumber", activityNumber))
                .setProjection(proList);
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("activityNumber", activityNumber));
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));
        return (ApprovalActivity) criteria.uniqueResult();
    }

    @Override
    public ApprovalActivity getEntityByActivityNumberAndSequence(String activityNumber, Integer sequence) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("activityNumber", activityNumber));
        criteria.add(Restrictions.eq("sequence", sequence));
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return (ApprovalActivity) criteria.uniqueResult();
    }

    @Override
    public Boolean isAlreadyHaveApprovedStatus(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_APPROVED));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("activityNumber", activityNumber));
        disjunction.add(Restrictions.eq("previousActivityNumber", activityNumber));
        criteria.add(disjunction);

        return criteria.list().size() > 0;

    }

    @Override
    public List<ApprovalActivity> getAllDataNotApprovedYet(String userId, String approvalDefinitionName) {
        Criteria criteria = getCurrentSession().createCriteria(ApprovalActivity.class, "main");

        //sub query first, get the latest of approval activity based on parameter
        DetachedCriteria subQueryMaxSeqAndActvNumber = DetachedCriteria.forClass(ApprovalActivity.class, "subFirst");
        subQueryMaxSeqAndActvNumber.createAlias("approvalDefinition", "approvalDefinition");
        subQueryMaxSeqAndActvNumber.add(Restrictions.eq("approvalDefinition.name", approvalDefinitionName));
        if (StringUtils.isNotEmpty(userId)) {
            subQueryMaxSeqAndActvNumber.add(Restrictions.eq("requestBy", userId));
        }
        ProjectionList projectionMaxSeqAndActvNumber = Projections.projectionList();
        projectionMaxSeqAndActvNumber.add(Property.forName("sequence").max());
        projectionMaxSeqAndActvNumber.add(Projections.groupProperty("activityNumber"));
        subQueryMaxSeqAndActvNumber.setProjection(projectionMaxSeqAndActvNumber);

        //sub query second, get approval activity which is not (approved or canceled or rejected) yet
        DetachedCriteria subQueryNotApprovedYet = DetachedCriteria.forClass(ApprovalActivity.class, "subSecond");
        subQueryNotApprovedYet.add(Restrictions.eqProperty("subSecond.activityNumber", "main.activityNumber"));
        Disjunction disjuntionStatus = Restrictions.disjunction();
        disjuntionStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_APPROVED));
        disjuntionStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_CANCELLED));
        disjuntionStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_REJECTED));
        disjuntionStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_DELETED));
        subQueryNotApprovedYet.add(disjuntionStatus);
        ProjectionList projectionNotApprovedYet = Projections.projectionList();
        projectionNotApprovedYet.add(Property.forName("id"));
        subQueryNotApprovedYet.setProjection(projectionNotApprovedYet);

        //binding sub query to main criteria
        String[] var = {"main.sequence", "main.activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, subQueryMaxSeqAndActvNumber));
        criteria.add(Subqueries.notExists(subQueryNotApprovedYet));

        return criteria.list();
    }

    @Override
    public List<ApprovalActivity> getAllDataWaitingStatusApprovalFetchedApprovalDef() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        criteria.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        return criteria.list();
    }

    @Override
    public ApprovalActivity getEntityByActivityNumberAndLastSequence(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("activityNumber", activityNumber));
        criteria.addOrder(Order.desc("sequence"));
        criteria.setMaxResults(1);
        return (ApprovalActivity) criteria.uniqueResult();
    }

    @Override

    public Long getTotalRequestHistory(String userName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));

        criteria.add(Restrictions.eq("requestBy", userName));
        criteria.add(Restrictions.not(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)));
        criteria.add(Restrictions.not(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED)));

//        criteria.setFetchMode("approvalDefinition", FetchMode.JOIN);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

    }

    @Override
    public Long getTotalPendingRequest(String userName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("sequence").max());
        proList.add(Projections.groupProperty("activityNumber"));
        DetachedCriteria maxSequenceAndActivityNumber = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("requestBy", userName))
                .setProjection(proList);
        String[] var = {"sequence", "activityNumber"};
        criteria.add(Subqueries.propertiesIn(var, maxSequenceAndActivityNumber));

        Disjunction disjStatus = Restrictions.disjunction();
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
        criteria.add(disjStatus);

        criteria.add(Restrictions.eq("requestBy", userName));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalPendingTask(String userName) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        Conjunction conjApprover = Restrictions.conjunction();
        conjApprover.add(Restrictions.eq("approvedBy", userName));
        conjApprover.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));

        Conjunction conjRequester = Restrictions.conjunction();
        conjRequester.add(Restrictions.eq("requestBy", userName));
        conjRequester.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));

        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(conjRequester);
        disjunction.add(conjApprover);

        criteria.add(disjunction);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<ApprovalActivity> getAllDataPendingRequestByApprovalDefName(String approvalDefName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("approvalDefinition", "approvalDefinition", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("approvalDefinition.name", approvalDefName));
        Disjunction disjStatus = Restrictions.disjunction();
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL));
        disjStatus.add(Restrictions.eq("approvalStatus", HRMConstant.APPROVAL_STATUS_WAITING_REVISED));
        criteria.add(disjStatus);
        return criteria.list();
    }

}
