package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BankDao;
import com.inkubator.hrm.entity.Bank;
import com.inkubator.hrm.web.search.BankSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "bankDao")
@Lazy
public class BankDaoImpl extends IDAOImpl<Bank> implements BankDao {

    @Override
    public Class<Bank> getEntityClass() {
        return Bank.class;
    }

    @Override
    public List<Bank> getByParam(BankSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("bank", FetchMode.JOIN);
        criteria.setFetchMode("bankGroup", FetchMode.JOIN);
        doSearchBankByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalBankByParam(BankSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBankByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchBankByParam(BankSearchParameter parameter, Criteria criteria) {
        if (parameter.getBankCode() != null) {
            criteria.add(Restrictions.like("bankCode", parameter.getBankCode(), MatchMode.ANYWHERE));
        }

        if (parameter.getBankName() != null) {
            criteria.add(Restrictions.like("bankName", parameter.getBankName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByCode(String code) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bankCode", code));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByCodeAndNotId(String code, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bankCode", code));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalBySwiftCode(String swiftCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("swiftCode", swiftCode));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalBySwiftCodeAndNotId(String swiftCode, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("swiftCode", swiftCode));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByIdentificationNumber(String identificationNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bankIdentificationNo", identificationNumber));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByIdentificationNumberAndNotId(String identificationNumber, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bankIdentificationNo", identificationNumber));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Bank getEntityWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("bank", FetchMode.JOIN);
        criteria.setFetchMode("bankGroup", FetchMode.JOIN);
        criteria.add(Restrictions.eq("id", id));
        return (Bank) criteria.uniqueResult();
    }

    @Override
    public List<Bank> getAllWithparent() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("bank", FetchMode.JOIN);
        criteria.setFetchMode("bankGroup", FetchMode.JOIN);
        return criteria.list();
    }
}
