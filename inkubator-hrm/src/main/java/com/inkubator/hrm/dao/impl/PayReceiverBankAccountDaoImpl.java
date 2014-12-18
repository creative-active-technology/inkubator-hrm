/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.PayReceiverBankAccountDao;
import com.inkubator.hrm.entity.PayReceiverBankAccount;
import com.inkubator.hrm.web.model.PayReceiverBankAccountModel;
import com.inkubator.hrm.web.search.PayReceiverBankAccountSearchParameter;

/**
 *
 * @author denifahri
 */
@Repository(value = "payReceiverBankAccountDao")
@Lazy
public class PayReceiverBankAccountDaoImpl extends IDAOImpl<PayReceiverBankAccount> implements PayReceiverBankAccountDao {

    @Override
    public Class<PayReceiverBankAccount> getEntityClass() {
        return PayReceiverBankAccount.class;
    }

    @Override
    public List<PayReceiverBankAccountModel> getByParam(PayReceiverBankAccountSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        StringBuilder hqlQuery = new StringBuilder("Select ep.id as empId,bi.firstName as firstName, bi.lastName as lastName, ep.joinDate as joinDate, ep.nik as nik, go.code as golJab, count(ba.id) as totalAccount  FROM EmpData ep  ");
        hqlQuery.append("JOIN ep.bioData bi ");
        hqlQuery.append("JOIN ep.golonganJabatan go ");
        hqlQuery.append("JOIN bi.bioBankAccounts ba ");
        hqlQuery.append("WHERE ep.status != :empCondistion ");
        if (searchParameter.getEmpName() != null) {
            hqlQuery.append("AND (bi.firstName like :firstName OR bi.lastName like :lastName) ");
        }

        if (searchParameter.getGolJab() != null) {
            hqlQuery.append("AND go.code like :code ");
        }

        if (searchParameter.getNik() != null) {
            hqlQuery.append("AND ep.nik like :nik ");
        }

        hqlQuery.append("GROUP BY ep.id ");
        hqlQuery.append("ORDER BY ep.nik");

        if (searchParameter.getEmpName() != null) {
            return getCurrentSession().createQuery(hqlQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("firstName", '%' + searchParameter.getEmpName() + '%')
                    .setParameter("lastName", '%' + searchParameter.getEmpName() + '%')
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .setResultTransformer(Transformers.aliasToBean(PayReceiverBankAccountModel.class))
                    .list();
        }
        if (searchParameter.getGolJab() != null) {
            return getCurrentSession().createQuery(hqlQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("code", searchParameter.getGolJab())
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .setResultTransformer(Transformers.aliasToBean(PayReceiverBankAccountModel.class))
                    .list();
        }
        if (searchParameter.getNik() != null) {
            return getCurrentSession().createQuery(hqlQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("nik", searchParameter.getNik())
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .setResultTransformer(Transformers.aliasToBean(PayReceiverBankAccountModel.class))
                    .list();
        }

        if (searchParameter.getEmpName() == null || searchParameter.getGolJab() == null || searchParameter.getNik() == null) {
            return getCurrentSession().createQuery(hqlQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setResultTransformer(Transformers.aliasToBean(PayReceiverBankAccountModel.class))
                    .setFirstResult(firstResult)
                    .setMaxResults(maxResults)
                    .list();
        }
        return null;
    }

    @Override
    public Long getTotalByParam(PayReceiverBankAccountSearchParameter searchParameter) {

        final StringBuilder nativeQuery = new StringBuilder("SELECT count(*) FROM(SELECT count(ba.id) FROM hrm.emp_data ep");
        nativeQuery.append(" JOIN hrm.bio_data bi on ep.bio_data_id=bi.id");
        nativeQuery.append(" JOIN hrm.bio_bank_account ba on ba.bio_data_id=bi.id");
        nativeQuery.append(" JOIN hrm.golongan_jabatan go on ep.gol_jab_id=go.id");
        nativeQuery.append(" WHERE ep.status != :empCondistion");
        if (searchParameter.getEmpName() != null) {
            nativeQuery.append(" AND (bi.first_name like :firstName OR bi.last_name like :lastName)");
        }

        if (searchParameter.getGolJab() != null) {
            nativeQuery.append(" AND go.code like :code");
        }

        if (searchParameter.getNik() != null) {
            nativeQuery.append(" AND ep.nik like :nik");
        }

        nativeQuery.append(" GROUP BY ep.id) as jumlah");
        if (searchParameter.getEmpName() != null) {
            return Long.valueOf(getCurrentSession().createSQLQuery(nativeQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("firstName", '%' + searchParameter.getEmpName() + '%')
                    .setParameter("lastName", '%' + searchParameter.getEmpName() + '%')
                    .uniqueResult().toString());
        }
        if (searchParameter.getGolJab() != null) {
            return Long.valueOf(getCurrentSession().createSQLQuery(nativeQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("code", searchParameter.getGolJab())
                    .uniqueResult().toString());
        }
        if (searchParameter.getNik() != null) {
            return Long.valueOf(getCurrentSession().createSQLQuery(nativeQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .setParameter("nik", searchParameter.getNik())
                    .uniqueResult().toString());
        }

        if (searchParameter.getEmpName() == null || searchParameter.getGolJab() == null || searchParameter.getNik() == null) {
            
            return Long.valueOf(getCurrentSession().createSQLQuery(nativeQuery.toString())
                    .setParameter("empCondistion", HRMConstant.EMP_TERMINATION)
                    .uniqueResult().toString());
        }
        return null;
    }

	@Override
	public List<PayReceiverBankAccount> getAllDataWithDetail() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
		criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
		criteria.setFetchMode("bioBankAccount", FetchMode.JOIN);
		criteria.setFetchMode("bioBankAccount.bank", FetchMode.JOIN);
		criteria.addOrder(Order.asc("bioData.firstName"));
		return criteria.list();
	}

}
