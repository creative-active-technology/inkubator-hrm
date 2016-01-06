/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewSchemaListOfEmpDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmpId;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "loanNewSchemaListOfEmpDao")
@Lazy
public class LoanNewSchemaListOfEmpDaoImpl extends IDAOImpl<LoanNewSchemaListOfEmp> implements LoanNewSchemaListOfEmpDao {

    @Override
    public Class<LoanNewSchemaListOfEmp> getEntityClass() {
        return LoanNewSchemaListOfEmp.class;
    }

    @Override
    public List<LoanNewSchemaListOfEmpViewModel> getByParam(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        final StringBuilder query = new StringBuilder("SELECT emp.id as idEmp, bio.first_name as firstName, bio.last_name as lastName, emp.nik as nik, goljab.code as code, listEmp.nomor_sk as noSk, newSchema.loan_schema_code as schemaCode FROM emp_data emp");
//        query.append(" FROM unreg_salary A");
        query.append(" LEFT JOIN loan_new_schema_list_of_emp listEmp ON listEmp.emp_id = emp.id");
        query.append(" LEFT JOIN bio_data bio ON bio.id = emp.bio_data_id");
        query.append(" LEFT JOIN golongan_jabatan goljab ON goljab.id = emp.gol_jab_id");
        query.append(" LEFT JOIN loan_new_schema newSchema ON newSchema.id = listEmp.loan_new_schema_id");
        if(parameter.getName() != null){
            query.append(" WHERE bio.first_name like '%" + parameter.getName() + "%'");
            query.append(" OR bio.last_name like '%" + parameter.getName() + "%'");
        }else if(parameter.getCode()!= null){
            query.append(" WHERE goljab.code like '%" + parameter.getCode()+ "%'");
        }else if(parameter.getNoSk()!= null){
            query.append(" WHERE listEmp.nomor_sk like '%" + parameter.getNoSk()+ "%'");
        }
        query.append(" LIMIT " + firstResult + ", " + maxResults);
        System.out.println(" Querynya "+query.toString());
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(LoanNewSchemaListOfEmpViewModel.class))
                .list();
    }

    @Override
    public List<LoanNewSchemaListOfEmpViewModel> getByParamHQL(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT bio.firstName as firstName "
                + " FROM empData emp"
                + " LEFT JOIN emp.bioData bio");
        Query hbm = getCurrentSession().createQuery(selectQuery.toString()).setMaxResults(maxResults).setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(LoanNewSchemaListOfEmpViewModel.class));

        return hbm.list();
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityWithDetailByEmpDataId(Long empId) throws Exception {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
         criteria.setFetchMode("empData", FetchMode.JOIN);
          criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
         criteria.add(Restrictions.eq("empData.id", empId));        
        return (LoanNewSchemaListOfEmp) criteria.uniqueResult();
    }
    
    public Long getTotalByParam(LoanNewSchemaListOfEmpSearchParameter parameter) {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT bio.first_name FROM emp_data emp");
//        query.append(" FROM unreg_salary A");
        query.append(" LEFT JOIN loan_new_schema_list_of_emp listEmp ON listEmp.emp_id = emp.id");
        query.append(" LEFT JOIN bio_data bio ON bio.id = emp.bio_data_id");
        query.append(" LEFT JOIN golongan_jabatan goljab ON goljab.id = emp.gol_jab_id");
        query.append(" LEFT JOIN loan_new_schema newSchema ON newSchema.id = listEmp.loan_new_schema_id");
        if(parameter.getName() != null){
            query.append(" WHERE bio.first_name like '%" + parameter.getName() + "%'");
            query.append(" OR bio.last_name like '%" + parameter.getName() + "%'");
        }else if(parameter.getCode()!= null){
            query.append(" WHERE goljab.code like '%" + parameter.getCode()+ "%'");
        }else if(parameter.getNoSk()!= null){
            query.append(" WHERE listEmp.nomor_sk like '%" + parameter.getNoSk()+ "%'");
        }
        query.append(" ) as jumlahData");
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByEmpDataId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", id));
        criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
        criteria.setFetchMode("empData.jabatanByJabatanId", FetchMode.JOIN);
        return (LoanNewSchemaListOfEmp) criteria.uniqueResult();
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityByPk(LoanNewSchemaListOfEmpId id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (LoanNewSchemaListOfEmp) criteria.uniqueResult();
    }

}
