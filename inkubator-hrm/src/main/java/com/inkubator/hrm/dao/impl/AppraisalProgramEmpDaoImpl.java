package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.AppraisalProgramEmpDao;
import com.inkubator.hrm.entity.AppraisalProgramEmp;
import com.inkubator.hrm.entity.AppraisalProgramEmpId;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.search.AppraisalProgramEmployeeSearchParameter;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "appraisalProgramEmpDao")
@Lazy
public class AppraisalProgramEmpDaoImpl extends IDAOImpl<AppraisalProgramEmp> implements AppraisalProgramEmpDao {

    @Override
    public Class<AppraisalProgramEmp> getEntityClass() {
        // TODO Auto-generated method stub
        return AppraisalProgramEmp.class;
    }

    @Override
    public List<EmpData> getAllEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter parameter, int firstResult, int maxResult, Order order) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT empData  "
                + "FROM EmpData empData "
                + "JOIN FETCH empData.bioData AS bioData "
                + "LEFT JOIN empData.appraisalProgramEmps AS appraisalProgramEmp "
                + "JOIN empData.golonganJabatan AS golonganJabatan "
                + "JOIN empData.jabatanByJabatanId AS jabatan "
                + "JOIN jabatan.department AS department "
                + "JOIN jabatan.unitKerja AS unitKerja "
                + "WHERE empData.status != :empStatus "
                + "AND appraisalProgramEmp IS NULL ");
        selectQuery.append(this.getWhereQueryEmployeeNotDistributedByParam(parameter));
        selectQuery.append("ORDER BY " + order);

        Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResult).setFirstResult(firstResult);
        hbm = this.setValueQueryEmployeeNotDistributedByParam(hbm, parameter);

        return hbm.list();
    }

    @Override
    public Long getTotalEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter parameter) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT COUNT(empData) "
                + "FROM EmpData empData "
                + "LEFT JOIN empData.appraisalProgramEmps AS appraisalProgramEmp "
                + "JOIN empData.golonganJabatan AS golonganJabatan "
                + "JOIN empData.jabatanByJabatanId AS jabatan "
                + "JOIN jabatan.department AS department "
                + "JOIN jabatan.unitKerja AS unitKerja "
                + "WHERE empData.status != :empStatus "
                + "AND appraisalProgramEmp IS NULL ");
        selectQuery.append(this.getWhereQueryEmployeeNotDistributedByParam(parameter));

        Query hbm = getCurrentSession().createQuery(selectQuery.toString());
        hbm = this.setValueQueryEmployeeNotDistributedByParam(hbm, parameter);

        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String getWhereQueryEmployeeNotDistributedByParam(AppraisalProgramEmployeeSearchParameter parameter) {
        StringBuffer whereQuery = new StringBuffer();

        if (!parameter.getListGolJab().isEmpty()) {
            whereQuery.append("AND golonganJabatan.code IN (:golJabatanCode) ");
        }
        if (!parameter.getListDepartment().isEmpty()) {
            whereQuery.append("AND department.id IN (:departmentId) ");
        }
        if (!parameter.getListUnitKerja().isEmpty()) {
            whereQuery.append("AND unitKerja.id IN (:unitKerjaId) ");
        }

        return whereQuery.toString();
    }

    private Query setValueQueryEmployeeNotDistributedByParam(Query hbm, AppraisalProgramEmployeeSearchParameter parameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "golJabatanCode")) {
                hbm.setParameterList("golJabatanCode", parameter.getListGolJab());
            } else if (StringUtils.equals(param, "unitKerjaId")) {
                hbm.setParameterList("unitKerjaId", parameter.getListUnitKerja());
            } else if (StringUtils.equals(param, "departmentId")) {
                hbm.setParameterList("departmentId", parameter.getListDepartment());
            } else if (StringUtils.equals(param, "empStatus")) {
                hbm.setParameter("empStatus", HRMConstant.EMP_TERMINATION);
            }
        }
        return hbm;
    }

    @Override
    public Long totalEmpBYAppraisalProgram(long AppraisalProgramId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("appraisalProgram"
                + "", "ap", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("ap.id", AppraisalProgramId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public AppraisalProgramEmp getByIdWithDetail(AppraisalProgramEmpId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("appraisalProgram", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
          criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        return (AppraisalProgramEmp) criteria.uniqueResult();
    }

}
