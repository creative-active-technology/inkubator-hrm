package com.inkubator.hrm.dao.impl;

import ch.lambdaj.Lambda;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.dao.HrmUserDao;
import com.inkubator.hrm.dao.JabatanDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.json.util.JsonUtil;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.hamcrest.Matchers;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author WebGenX
 */
@Repository(value = "recruitHireApplyDao")
@Lazy
public class RecruitHireApplyDaoImpl extends IDAOImpl<RecruitHireApply> implements RecruitHireApplyDao {
    
    @Autowired
    private HrmUserDao hrmUserDao;
    @Autowired
    private EmpDataDao empDataDao;    
    @Autowired
    private JabatanDao jabatanDao;

    @Override
    public Class<RecruitHireApply> getEntityClass() {
        return RecruitHireApply.class;
    }

    @Override
    public List<RecruitHireApply> getByParam(RecruitHireApplySearchParameter parameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitHireApplyByParam(parameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalRecruitHireApplyByParam(RecruitHireApplySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchRecruitHireApplyByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchRecruitHireApplyByParam(RecruitHireApplySearchParameter parameter, Criteria criteria) {
        if (parameter.getReason() != null) {
            criteria.add(Restrictions.like("reason", parameter.getReason(), MatchMode.ANYWHERE));
        }
        if (parameter.getReqHireCode() != null) {
            criteria.add(Restrictions.like("reqHireCode", parameter.getReqHireCode(), MatchMode.ANYWHERE));
        }
        if (parameter.getMaritalStatus() != null) {
            criteria.add(Restrictions.like("maritalStatus", parameter.getMaritalStatus(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<RecruitReqHistoryViewModel> getRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter, int firstResult, int maxResults, Order orderable) {

        StringBuffer selectQuery = new StringBuffer(
                "SELECT approvalActivity.id AS approvalActivityId, "
                + "approvalActivity.activity_number AS activityNumber, "
                + "rha.id AS rhaId, "
                + "approvalActivity.request_by AS requestBy, "
                + "approvalActivity.approval_status AS approvalStatus, "
                + "approvalActivity.pending_data AS jsonData  "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "  
                + "LEFT JOIN recruit_hire_apply AS rha ON approvalActivity.activity_number = rha.approval_activity_number "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName  ");

        selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
        selectQuery.append("GROUP BY approvalActivity.activity_number ");
        selectQuery.append("ORDER BY ").append(orderable);

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(RecruitReqHistoryViewModel.class));
        hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);       
        
        return hbm.list();
    }

    @Override
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT COUNT(*) "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_hire_apply AS rha ON approvalActivity.activity_number = rha.approval_activity_number "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName  ");
        selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
        hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String setWhereQueryUndisbursedActivityByParam(RecruitReqHistorySearchParameter parameter) {
        StringBuffer whereQuery = new StringBuffer();

        if (StringUtils.isNotEmpty(parameter.getFormCode())) {
            whereQuery.append("AND rha.req_hire_code LIKE :formCode ");
        }
        if (StringUtils.isNotEmpty(parameter.getJabatanName())) {
            whereQuery.append("AND jabatan.name LIKE :jabatanName ");
        }
        
         if (StringUtils.isNotEmpty(parameter.getUserId())) {
            whereQuery.append("AND (approvalActivity.request_by = :userId AND approvalActivity.approval_status IN (").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)    
                        .append(",").append(HRMConstant.APPROVAL_STATUS_REJECTED)
                        .append(")) ");                 
                        
        } else {
        	//view for administrator(can view all employee requester)        	
                whereQuery.append("AND approvalActivity.approval_status IN ( ").append(HRMConstant.APPROVAL_STATUS_WAITING_APPROVAL)
                        .append(",").append(HRMConstant.APPROVAL_STATUS_APPROVED)   
                        .append(",").append(HRMConstant.APPROVAL_STATUS_REJECTED)
                        .append(")");
        }       

        return whereQuery.toString();
    }

    private Query setValueQueryUndisbursedActivityByParam(Query hbm, RecruitReqHistorySearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "formCode")) {
                hbm.setParameter("formCode", "%" + parameter.getFormCode() + "%");
            } else if (StringUtils.equals(param, "jabatanName")) {
                hbm.setParameter("jabatanName", "%" + parameter.getJabatanName() + "%");
            } else if (StringUtils.equals(param, "appDefinitionName")) {
                hbm.setParameter("appDefinitionName", HRMConstant.RECRUITMENT_REQUEST);
            }
        }
        return hbm;
    }
    
        

    @Override
    public Long getTotalDataByReqHireCode(String reqHireCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("reqHireCode", reqHireCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public RecruitHireApply getEntityWithDetailByPk(Long recruitHireApplyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", recruitHireApplyId));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        return (RecruitHireApply) criteria.uniqueResult();
    }
    
    @Override
    public Long getCurrentMaxId() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public RecruitHireApply getEntityWithDetailByActivityNumber(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        
//        criteria.createAlias("employeeType", "employeeType", JoinType.INNER_JOIN);
//        criteria.createAlias("currency", "currency", JoinType.INNER_JOIN);
//        criteria.createAlias("jabatan", "jabatan", JoinType.INNER_JOIN);
//        criteria.createAlias("recruitMppPeriod", "recruitMppPeriod", JoinType.INNER_JOIN);
        
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        
        return (RecruitHireApply) criteria.uniqueResult();
    }
}
