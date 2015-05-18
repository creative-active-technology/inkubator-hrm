package com.inkubator.hrm.dao.impl;

import com.inkubator.hrm.entity.RecruitHireApply;
import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import com.inkubator.hrm.dao.RecruitHireApplyDao;
import com.inkubator.hrm.web.model.RecruitReqHistoryViewModel;
import com.inkubator.hrm.web.search.RecruitHireApplySearchParameter;
import com.inkubator.hrm.web.search.RecruitReqHistorySearchParameter;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;

/**
 *
 * @author WebGenX
 */
@Repository(value = "recruitHireApplyDao")
@Lazy
public class RecruitHireApplyDaoImpl extends IDAOImpl<RecruitHireApply> implements RecruitHireApplyDao {

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
                + "jabatan.code AS jabatanCode, "
                + "jabatan.name AS jabatanName, "
                + "CONCAT(empRequester.NIK,' ',bioRequester.first_name,' ',bioRequester.last_name) AS requestBy, "
                + "approvalActivity.approval_status AS approvalStatus, "
                + "approvalActivity.pending_data AS jsonData  "
                + "FROM hrm.approval_activity approvalActivity "
                + "INNER JOIN hrm.approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "INNER JOIN hrm.hrm_user AS userRequester ON userRequester.user_id = approvalActivity.request_by "
                + "INNER JOIN hrm.emp_data AS empRequester ON empRequester.id = userRequester.emp_data_id "
                + "INNER JOIN hrm.bio_data AS bioRequester ON bioRequester.id = empRequester.bio_data_id "
                + "LEFT JOIN hrm.recruit_hire_apply AS rha ON approvalActivity.activity_number = rha.approval_activity_number "
                + "INNER JOIN hrm.jabatan AS jabatan ON rha.jabatan_id = jabatan.id "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName  ");

        selectQuery.append(this.setWhereQueryUndisbursedActivityByParam(parameter));
        selectQuery.append("GROUP BY approvalActivity.activity_number ");
        selectQuery.append("ORDER BY ").append(orderable);

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(RecruitReqHistoryViewModel.class));
        hbm = this.setValueQueryUndisbursedActivityByParam(hbm, parameter);

        return hbm.list();
    }

    @Override
    public Long getTotalRecruitmentReqActivityByParam(RecruitReqHistorySearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT COUNT(*) "
                + "FROM hrm.approval_activity approvalActivity "
                + "INNER JOIN hrm.approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "INNER JOIN hrm.hrm_user AS userRequester ON userRequester.user_id = approvalActivity.request_by "
                + "INNER JOIN hrm.emp_data AS empRequester ON empRequester.id = userRequester.emp_data_id "
                + "INNER JOIN hrm.bio_data AS bioRequester ON bioRequester.id = empRequester.bio_data_id "
                + "LEFT JOIN hrm.recruit_hire_apply AS rha ON approvalActivity.activity_number = rha.approval_activity_number "
                + "INNER JOIN hrm.jabatan AS jabatan ON rha.jabatan_id = jabatan.id "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM hrm.approval_activity app GROUP BY app.activity_number) "
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
    public RecruitHireApply getEntityByPkWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        return (RecruitHireApply) criteria.uniqueResult();
    }
}
