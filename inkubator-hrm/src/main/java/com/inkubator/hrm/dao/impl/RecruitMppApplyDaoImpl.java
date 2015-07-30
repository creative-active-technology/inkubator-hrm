package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.web.model.RecruitMppApplyModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitMppApplyDao")
@Lazy
public class RecruitMppApplyDaoImpl extends IDAOImpl<RecruitMppApply> implements RecruitMppApplyDao {

    @Override
    public Class<RecruitMppApply> getEntityClass() {
        return RecruitMppApply.class;
    }

    @Override
    public List<RecruitMppApply> getByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        doSearchLoanTypeByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();

    }

    @Override
    public Long getTotalByParam(RecruitMppApplySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchLoanTypeByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchLoanTypeByParam(RecruitMppApplySearchParameter parameter, Criteria criteria) {
        if (parameter.getRecruitMppApplyCode() != null) {
            criteria.add(Restrictions.like("recruitMppApplyCode", parameter.getRecruitMppApplyCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getRecruitMppApplyName() != null) {
            criteria.add(Restrictions.like("recruitMppApplyName", parameter.getRecruitMppApplyName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public RecruitMppApply getEntityWithDetailById(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        return (RecruitMppApply) criteria.uniqueResult();
    }

    @Override
    public List<RecruitMppApplyViewModel> getUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT approvalActivity.id AS approvalActivityId, "
                + "approvalActivity.activity_number AS activityNumber, "
                + "mpp.id AS recruitMppApplyId, "
                + "mpp.recruit_mpp_apply_code AS recruitMppApplyCode, "
                + "mpp.recruit_mpp_apply_name AS recruitMppApplyName,  "
                + "approvalActivity.approval_status AS approvalStatus, "
                + "approvalActivity.pending_data AS jsonData  "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_mpp_apply AS mpp ON approvalActivity.activity_number = mpp.approval_activity_number  "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName  ");

        selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
        selectQuery.append("GROUP BY approvalActivity.activity_number ");
        selectQuery.append("ORDER BY ").append(orderable);

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(RecruitMppApplyViewModel.class));
        hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);

        return hbm.list();
    }

    private String setWhereQueryUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter) {
        StringBuffer whereQuery = new StringBuffer();

        if (StringUtils.isNotEmpty(parameter.getRecruitMppApplyCode())) {
            whereQuery.append("AND mpp.recruit_mpp_apply_code LIKE :recruitMppApplyCode ");
        }
        if (StringUtils.isNotEmpty(parameter.getRecruitMppApplyName())) {
            whereQuery.append("AND mpp.recruit_mpp_apply_name LIKE :recruitMppApplyName ");
        }

        return whereQuery.toString();
    }

    private Query setValueQueryUndisbursedActivityByParam(Query hbm, RecruitMppApplySearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "recruitMppApplyCode")) {
                hbm.setParameter("recruitMppApplyCode", "%" + parameter.getRecruitMppApplyCode() + "%");
            } else if (StringUtils.equals(param, "recruitMppApplyName")) {
                hbm.setParameter("recruitMppApplyName", "%" + parameter.getRecruitMppApplyName() + "%");
            } else if (StringUtils.equals(param, "appDefinitionName")) {
                hbm.setParameter("appDefinitionName", HRMConstant.RECRUIT_MPP_APPLY);
            }
        }
        return hbm;
    }

    @Override
    public Long getTotalUndisbursedActivityByParam(RecruitMppApplySearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT COUNT(*) "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_mpp_apply AS mpp ON approvalActivity.activity_number = mpp.approval_activity_number  "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName  ");
        selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
        hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public RecruitMppApply getEntityWithDetailByActivityNumber(String activityNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("approvalActivityNumber", activityNumber));
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        return (RecruitMppApply) criteria.uniqueResult();
    }

    @Override
    public Long getTotalDataByMppCode(String mppCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("recruitMppApplyCode", mppCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
