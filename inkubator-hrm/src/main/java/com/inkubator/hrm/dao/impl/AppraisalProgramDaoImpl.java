package com.inkubator.hrm.dao.impl;

import java.util.List;

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
import com.inkubator.hrm.dao.AppraisalProgramDao;
import com.inkubator.hrm.entity.AppraisalProgram;
import com.inkubator.hrm.util.StringUtils;
import com.inkubator.hrm.web.model.AppraisalProgramDistributionViewModel;
import com.inkubator.hrm.web.search.AppraisalProgramSearchParameter;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalProgramDao")
@Lazy
public class AppraisalProgramDaoImpl extends IDAOImpl<AppraisalProgram> implements AppraisalProgramDao {

    @Override
    public Class<AppraisalProgram> getEntityClass() {
        // TODO Auto-generated method stub
        return AppraisalProgram.class;
    }

    @Override
    public List<AppraisalProgram> getAllDataByParam(AppraisalProgramSearchParameter searchParameter, int firstResult, int maxResult, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
//        criteria.setFetchMode("appraisalProgramEmps", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(AppraisalProgramSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(AppraisalProgramSearchParameter searchParameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(searchParameter.getName())) {
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));

    }

    @Override
    public AppraisalProgram getEntityByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("appraisalAchievementPrograms", FetchMode.JOIN);
        criteria.setFetchMode("appraisalAchievementPrograms.careerAwardType", FetchMode.JOIN);
        criteria.setFetchMode("appraisalIndisciplinePrograms", FetchMode.JOIN);
        criteria.setFetchMode("appraisalIndisciplinePrograms.careerDisciplineType", FetchMode.JOIN);
        criteria.setFetchMode("appraisalPerformanceGroup", FetchMode.JOIN);
        criteria.setFetchMode("appraisalProgramEmps", FetchMode.JOIN);
        criteria.setFetchMode("appraisalProgramEmps.empData", FetchMode.JOIN);
 criteria.setFetchMode("appraisalProgramEmps.empData.bioData", FetchMode.JOIN);
  criteria.setFetchMode("appraisalProgramEmps.empData.jabatanByJabatanId", FetchMode.JOIN);
        return (AppraisalProgram) criteria.uniqueResult();
    }

    @Override
    public List<AppraisalProgramDistributionViewModel> getAllEmpDistributionByParam(AppraisalProgramSearchParameter parameter, int firstResult, int maxResult, Order orderable) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT appraisalProgram.id AS programId, "
                + "appraisalProgram.name AS programName, "
                + "appraisalProgram.evalStartDate AS programStartDate, "
                + "appraisalProgram.evalEndDate AS programEndDate, "
                + "SUM(CASE WHEN appraisalProgramEmp IS NULL THEN 0 ELSE 1 END) AS totalEmployee "
                + "FROM AppraisalProgram AS appraisalProgram "
                + "LEFT JOIN appraisalProgram.appraisalProgramEmps AS appraisalProgramEmp ");
        selectQuery.append(this.getWhereQueryByParam(parameter));
        selectQuery.append("GROUP BY appraisalProgram.id ");
        selectQuery.append("ORDER BY " + orderable);

        Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResult).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(AppraisalProgramDistributionViewModel.class));
        hbm = this.setValueQueryByParam(hbm, parameter);

        return hbm.list();
    }

    @Override
    public Long getTotalEmpDistributionByParam(AppraisalProgramSearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT count(*) "
                + "FROM AppraisalProgram AS appraisalProgram ");
        selectQuery.append(this.getWhereQueryByParam(parameter));

        Query hbm = getCurrentSession().createQuery(selectQuery.toString());
        hbm = this.setValueQueryByParam(hbm, parameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String getWhereQueryByParam(AppraisalProgramSearchParameter parameter) {
        StringBuffer whereQuery = new StringBuffer();

        if (StringUtils.isNotEmpty(parameter.getName())) {
            if (StringUtils.isNotEmpty(whereQuery)) {
                whereQuery.append("AND ");
            }
            whereQuery.append("appraisalProgram.name LIKE :programName ");
        }

        return StringUtils.isNotEmpty(whereQuery) ? "WHERE " + whereQuery.toString() : StringUtils.EMPTY;
    }

    private Query setValueQueryByParam(Query hbm, AppraisalProgramSearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "programName")) {
                hbm.setParameter("programName", "%" + parameter.getName() + "%");
            }
        }

        return hbm;
    }

}
