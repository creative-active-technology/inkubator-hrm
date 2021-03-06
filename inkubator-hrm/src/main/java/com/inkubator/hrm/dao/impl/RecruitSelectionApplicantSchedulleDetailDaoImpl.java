package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.RecruitSelectionApplicantSchedulleDetailDao;
import com.inkubator.hrm.entity.RecruitSelectionApplicantSchedulleDetail;

import java.util.List;
import org.eclipse.jdt.internal.compiler.ast.ASTNode;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ahmad Mudzakkir Amal
 */
@Repository(value = "recruitSelectionApplicantSchedulleDetailDao")
@Lazy
public class RecruitSelectionApplicantSchedulleDetailDaoImpl extends IDAOImpl<RecruitSelectionApplicantSchedulleDetail> implements RecruitSelectionApplicantSchedulleDetailDao {

    @Override
    public Class<RecruitSelectionApplicantSchedulleDetail> getEntityClass() {
        return RecruitSelectionApplicantSchedulleDetail.class;
    }

    @Override
    public List<RecruitSelectionApplicantSchedulleDetail> getAllDataByApplicantIdAndSelectionApplicantSchedulleId(
            Long applicantId, Long selectionApplicantSchedulleId) {

        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("selectionType", FetchMode.JOIN);

        criteria.createAlias("applicant", "applicant", JoinType.INNER_JOIN);
        criteria.createAlias("recruitSelectionApplicantSchedulle", "recruitSelectionApplicantSchedulle", JoinType.INNER_JOIN);

        criteria.add(Restrictions.eq("applicant.id", applicantId));
        criteria.add(Restrictions.eq("recruitSelectionApplicantSchedulle.id", selectionApplicantSchedulleId));
        return criteria.list();
    }

    @Override
    public List<RecruitSelectionApplicantSchedulleDetail> getListByRecruitSelectionApplicantSchedulleId(Long recruitSelectionApplicantSchedulleId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("applicant", FetchMode.JOIN);
        criteria.setFetchMode("applicant.bioData", FetchMode.JOIN);
        criteria.setFetchMode("selectionType", FetchMode.JOIN);
        criteria.createAlias("recruitSelectionApplicantSchedulle", "recruitSelectionApplicantSchedulle", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("recruitSelectionApplicantSchedulle.id", recruitSelectionApplicantSchedulleId));
        return criteria.list();
    }

    @Override
    public List<RecruitSelectionApplicantSchedulleDetail> getAllByApplicatIdAndMaxListOrder(Long applicantId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("applicant", "ap", JoinType.INNER_JOIN);
        ProjectionList proList = Projections.projectionList();
        proList.add(Property.forName("selectionListOrder").max());
        proList.add(Projections.groupProperty("applicant"));
        DetachedCriteria maxSelection = DetachedCriteria.forClass(getEntityClass())
                .add(Restrictions.eq("ap.id", applicantId))
                .setProjection(proList);
        String[] var = {"selectionListOrder", "ap.id"};
        criteria.add(Subqueries.propertiesIn(var, maxSelection));
        criteria.add(Restrictions.eq("ap.id", applicantId));
        return criteria.list();

    }

}
