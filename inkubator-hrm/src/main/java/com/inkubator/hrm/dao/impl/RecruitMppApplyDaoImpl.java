package com.inkubator.hrm.dao.impl;

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

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.RecruitMppApplyDao;
import com.inkubator.hrm.entity.RecruitMppApply;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.MppApplyHistoryViewModel;
import com.inkubator.hrm.web.model.RecruitMppApplyViewModel;
import com.inkubator.hrm.web.search.RecruitMppApplySearchParameter;

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
                + "approvalActivity.request_by AS createdBy,  "
                + "approvalActivity.approval_status AS approvalStatus, "
                + "approvalActivity.pending_data AS jsonData  "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_mpp_apply AS mpp ON approvalActivity.activity_number = mpp.approval_activity_number  "
                + "LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName ");
        		
        		// dicomment untuk task Evaluation #1518 - tampilkan semua data pada halaman rencana perekrutan tenaga kerja (MASTER)
                /*+ "AND requester.user_id = :userId ");*/

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

        /*if (StringUtils.isNotEmpty(parameter.getRecruitMppApplyCode())) {
            whereQuery.append("AND mpp.recruit_mpp_apply_code LIKE :recruitMppApplyCode ");
        }
        if (StringUtils.isNotEmpty(parameter.getRecruitMppApplyName())) {
            whereQuery.append("AND mpp.recruit_mpp_apply_name LIKE :recruitMppApplyName ");
        }*/
        
        if (StringUtils.isNotEmpty(parameter.getRequester())) {
            whereQuery.append("AND requester.user_id LIKE :userId ");
        }

        return whereQuery.toString();
    }

    private Query setValueQueryUndisbursedActivityByParam(Query hbm, RecruitMppApplySearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            /*if (StringUtils.equals(param, "recruitMppApplyCode")) {
                hbm.setParameter("recruitMppApplyCode", "%" + parameter.getRecruitMppApplyCode() + "%");
            } else if (StringUtils.equals(param, "recruitMppApplyName")) {
                hbm.setParameter("recruitMppApplyName", "%" + parameter.getRecruitMppApplyName() + "%");
            }*/ if (StringUtils.equals(param, "appDefinitionName")) {
                hbm.setParameter("appDefinitionName", HRMConstant.RECRUIT_MPP_APPLY);
            } else if (StringUtils.equals(param, "userId")) {
                hbm.setParameter("userId", "%" + parameter.getRequester()  + "%");
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
                + "LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName ");
        // dicomment untuk task Evaluation #1518 - tampilkan semua data pada halaman rencana perekrutan tenaga kerja (MASTER)
        //        + "AND requester.user_id = :userId ");
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

	@Override
	public List<RecruitMppApply> getListWithDetailByApprovalStatus(Integer approvalStatus) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("applicationStatus", approvalStatus));
        criteria.setFetchMode("recruitMppPeriod", FetchMode.JOIN);
        return criteria.list();
	}
	
	
	@Override
    public List<MppApplyHistoryViewModel> getAllDataMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT approvalActivity.id AS approvalActivityId, "
                + "mppApply.id AS recruitMppApplyId, "
                + "mppApply.recruit_mpp_apply_name AS mppApplyName,  "
                + "mppPeriod.periode_start AS mppApplyPeriodStart,  "
                + "mppPeriod.periode_end AS mppApplyPeriodEnd,  "
                + "requesterEmployee.nik AS requesterNik, "
                + "CONCAT(requesterBiodata.first_name, ' ',requesterBiodata.last_name)  AS requesterName, "
                + "approverEmployee.nik AS approverNik, "
                + "CONCAT(approverBiodata.first_name, ' ',approverBiodata.last_name) AS approverName, "
                + "approvalActivity.approval_status AS approvalStatus, "
                + "approvalActivity.activity_number AS activityNumber,  "
                + "approvalActivity.pending_data AS jsonData  "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_mpp_apply AS mppApply ON approvalActivity.activity_number = mppApply.approval_activity_number "
                + "LEFT JOIN recruit_mpp_period AS mppPeriod ON mppApply.recruit_mpp_period_id = mppPeriod.id "
                + "LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by "
                + "LEFT JOIN emp_data AS requesterEmployee ON requester.emp_data_id = requesterEmployee.id "
                + "LEFT JOIN bio_data AS requesterBiodata ON requesterEmployee.bio_data_id = requesterBiodata.id "
                + "LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " 
    			+ "LEFT JOIN emp_data AS approverEmployee ON approver.emp_data_id = approverEmployee.id " 
    			+ "LEFT JOIN bio_data AS approverBiodata ON approverEmployee.bio_data_id = approverBiodata.id "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName "
                + "AND (requester.user_id = :userId OR approver.user_id = :userId) ");

        selectQuery.append(this.setWhereQueryMppApplyHistoryByParam(parameter));
        selectQuery.append("GROUP BY approvalActivity.activity_number ");
        selectQuery.append("ORDER BY ").append(orderable);

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(MppApplyHistoryViewModel.class));
        hbm = this.setValueQueryMppApplyHistoryByParam(hbm, parameter);

        return hbm.list();
    }

    private String setWhereQueryMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter) {
        StringBuffer whereQuery = new StringBuffer();
        
        if (StringUtils.isNotEmpty(parameter.getRequester())) {
        	whereQuery.append("AND (requesterBiodata.first_name LIKE :requester OR requesterBiodata.last_name LIKE :requester OR requesterEmployee.nik LIKE :requester) ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getApprover())) {
        	whereQuery.append("AND (approverBiodata.first_name LIKE :approver OR approverBiodata.last_name LIKE :approver OR approverEmployee.nik LIKE :approver) ");
        }
        
        if (StringUtils.isNotEmpty(parameter.getRecruitMppApplyName())) {
            whereQuery.append("AND mppApply.recruit_mpp_apply_name LIKE :recruitMppApplyName ");
        }

        return whereQuery.toString();
    }

    private Query setValueQueryMppApplyHistoryByParam(Query hbm, RecruitMppApplySearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "recruitMppApplyName")) {
                hbm.setParameter("recruitMppApplyName", "%" + parameter.getRecruitMppApplyName() + "%");
            } else if (StringUtils.equals(param, "appDefinitionName")) {
                hbm.setParameter("appDefinitionName", HRMConstant.RECRUIT_MPP_APPLY);
            } else if (StringUtils.equals(param, "userId")) {
                hbm.setParameter("userId", HrmUserInfoUtil.getUserName());
            } else if (StringUtils.equals(param, "requester")) {
                hbm.setParameter("requester", "%" + parameter.getRequester() + "%");
            } else if (StringUtils.equals(param, "approver")) {
                hbm.setParameter("approver", "%" + parameter.getApprover() + "%");
            }
        }
        return hbm;
    }

    @Override
    public Long getTotalMppApplyHistoryByParam(RecruitMppApplySearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT COUNT(*) "
                + "FROM approval_activity approvalActivity "
                + "INNER JOIN approval_definition AS approvalDefinition ON approvalDefinition.id = approvalActivity.approval_def_id "
                + "LEFT JOIN recruit_mpp_apply AS mppApply ON approvalActivity.activity_number = mppApply.approval_activity_number "
                + "LEFT JOIN recruit_mpp_period AS mppPeriod ON mppApply.recruit_mpp_period_id = mppPeriod.id "
                + "LEFT JOIN hrm_user AS requester ON requester.user_id = approvalActivity.request_by "
                + "LEFT JOIN emp_data AS requesterEmployee ON requester.emp_data_id = requesterEmployee.id "
                + "LEFT JOIN bio_data AS requesterBiodata ON requesterEmployee.bio_data_id = requesterBiodata.id "
                + "LEFT JOIN hrm_user AS approver ON approver.user_id = approvalActivity.approved_by " 
    			+ "LEFT JOIN emp_data AS approverEmployee ON approver.emp_data_id = approverEmployee.id " 
    			+ "LEFT JOIN bio_data AS approverBiodata ON approverEmployee.bio_data_id = approverBiodata.id "
                + "WHERE (approvalActivity.activity_number,approvalActivity.sequence) IN (SELECT app.activity_number,max(app.sequence) FROM approval_activity app GROUP BY app.activity_number) "
                + "AND approvalDefinition.name = :appDefinitionName "
                + "AND (requester.user_id = :userId OR approver.user_id = :userId) ");       
        selectQuery.append(this.setWhereQueryMppApplyHistoryByParam(parameter));

        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
        hbm = this.setValueQueryMppApplyHistoryByParam(hbm, parameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }
	
}
