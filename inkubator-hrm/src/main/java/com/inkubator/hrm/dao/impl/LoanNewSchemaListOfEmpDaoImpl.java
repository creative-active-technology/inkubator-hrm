/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.LoanNewSchemaListOfEmpDao;
import com.inkubator.hrm.entity.LoanNewSchemaListOfEmp;
import com.inkubator.hrm.web.model.LoanNewSchemaListOfEmpViewModel;
import com.inkubator.hrm.web.search.LoanNewSchemaListOfEmpSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
public class LoanNewSchemaListOfEmpDaoImpl extends IDAOImpl<LoanNewSchemaListOfEmp> implements LoanNewSchemaListOfEmpDao{

    @Override
    public Class<LoanNewSchemaListOfEmp> getEntityClass() {
        return LoanNewSchemaListOfEmp.class;
    }

    @Override
    public List<LoanNewSchemaListOfEmpViewModel> getByParam(LoanNewSchemaListOfEmpSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        final StringBuilder query = new StringBuilder("SELECT bio.first_name as firstName, bio.last_name as lastNameemp.nik as nik, newSchema.loan_schema_code as loanSchemaCode, goljab.code as golJabCode, newSchema.nomor_sk as nomorSk FROM hrm.emp_data emp");
        query.append(" FROM hrm.unreg_salary A");
        query.append(" LEFT JOIN hrm.loan_new_schema_list_of_emp listEmp ON listEmp.emp_id = emp.id");
        query.append(" LEFT JOIN hrm.bio_data bio ON bio.id = emp.bio_data_id");
        query.append(" LEFT JOIN hrm.golongan_jabatan goljab ON goljab.id = emp.gol_jab_id");
        query.append(" LEFT JOIN hrm.loan_new_schema newSchema ON newSchema.id = listEmp.loan_new_schema_id");
        query.append(" LIMIT " + firstResult + ", " + maxResults);
       
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(LoanNewSchemaListOfEmpViewModel.class))
                .list();
    }

    @Override
    public Long getTotalByParam(LoanNewSchemaListOfEmpSearchParameter parameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoanNewSchemaListOfEmp getEntityWithDetailByEmpDataId(Long empId) throws Exception {
         Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
         criteria.setFetchMode("empData", FetchMode.JOIN);
          criteria.setFetchMode("loanNewSchema", FetchMode.JOIN);
         criteria.add(Restrictions.eq("empData.id", empId));        
        return (LoanNewSchemaListOfEmp) criteria.uniqueResult();
    }
    
}
