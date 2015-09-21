package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.BioBankAccountDao;
import com.inkubator.hrm.entity.BioBankAccount;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "bioBankAccountDao")
@Lazy
public class BioBankAccountDaoImpl extends IDAOImpl<BioBankAccount> implements BioBankAccountDao {

    @Override
    public Class<BioBankAccount> getEntityClass() {
        return BioBankAccount.class;
    }

    @Override
    public List<BioBankAccount> getAllDataByBioDataId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        criteria.setFetchMode("bank", FetchMode.JOIN);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        criteria.setFetchMode("bank.bank", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public BioBankAccount getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("bank", FetchMode.JOIN);
        criteria.setFetchMode("currency", FetchMode.JOIN);
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        return (BioBankAccount) criteria.uniqueResult();
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
    public Long getTotalByAccountNumber(String accountNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("accountNumber", accountNumber));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAccountNumberAndNotId(String accountNumber, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("accountNumber", accountNumber));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByDefaultAndId(Long bioDataId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("defaultAccount", HRMConstant.BANK_DEFAULT_ACCOUNT_YES));
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByDefaultAndNotId(Long bioDataId, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("defaultAccount", HRMConstant.BANK_DEFAULT_ACCOUNT_YES));
        criteria.add(Restrictions.eq("bioData.id", bioDataId));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
