/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.AppraisalProgramEmpAssesorDao;
import com.inkubator.hrm.entity.AppraisalProgramEmpAssesor;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denifahri
 */
@Repository(value = "appraisalProgramEmpAssesorDao")
@Lazy
public class AppraisalProgramEmpAssesorDaoImpl extends IDAOImpl<AppraisalProgramEmpAssesor> implements AppraisalProgramEmpAssesorDao {

    @Override
    public Class<AppraisalProgramEmpAssesor> getEntityClass() {
        return AppraisalProgramEmpAssesor.class;
    }

    @Override
    public Long getTotalAssesorByAppraisalIAndEmpId(Long appraisalId, Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("appraisalProgram", "ap");
        criteria.createAlias("empDataByEmpId", "em");
        criteria.add(Restrictions.eq("ap.id", appraisalId));
        criteria.add(Restrictions.eq("em.id", empId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<AppraisalProgramEmpAssesor> getAllBy(Long appraisalId, Long empId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("appraisalProgram", "ap");
        criteria.createAlias("empDataByEmpId", "em");
        criteria.setFetchMode("appraisalProgram", FetchMode.JOIN);
        criteria.setFetchMode("empDataByAssesorEmpId", FetchMode.JOIN);
        criteria.setFetchMode("empDataByAssesorEmpId.jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("empDataByAssesorEmpId.bioData", FetchMode.JOIN);
        criteria.add(Restrictions.eq("ap.id", appraisalId));
        criteria.add(Restrictions.eq("em.id", empId));
        return criteria.list();
    }

}
