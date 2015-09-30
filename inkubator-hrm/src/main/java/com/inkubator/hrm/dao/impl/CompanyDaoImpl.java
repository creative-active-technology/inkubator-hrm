package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.CompanyDao;
import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.search.CompanySearchParameter;
import org.hibernate.sql.JoinType;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "companyDao")
@Lazy
public class CompanyDaoImpl extends IDAOImpl<Company> implements CompanyDao {

    @Override
    public Class<Company> getEntityClass() {
        return Company.class;
    }

    @Override
    public List<Company> getByParam(CompanySearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        
        criteria.createAlias("city", "city", JoinType.INNER_JOIN);
        criteria.createAlias("city.province", "province", JoinType.INNER_JOIN);
        criteria.createAlias("city.province.country", "country", JoinType.INNER_JOIN);
        doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(CompanySearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(CompanySearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getCountry())) {
            //criteria.createAlias("city.province.country", "country");
            criteria.add(Restrictions.like("country.countryName", parameter.getCountry(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Company getEntityByPKWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("city.province", FetchMode.JOIN);
        criteria.setFetchMode("city.province.country", FetchMode.JOIN);
        criteria.setFetchMode("taxCountry", FetchMode.JOIN);
        criteria.setFetchMode("businessType", FetchMode.JOIN);
        return (Company) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByLegalNo(String legalNo) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("legalNo", legalNo));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLegalNoAndNotId(String legalNo, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("legalNo", legalNo));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPhone(String phone) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("phone", phone));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByPhoneAndNotId(String phone, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("phone", phone));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByTaxAccountNumber(String taxAccountNumber) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("taxAccountNumber", taxAccountNumber));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByTaxAccountNumberAndNotId(String taxAccountNumber, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("taxAccountNumber", taxAccountNumber));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<Company> getAllData() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.isNotNull("id"));
        if (HrmUserInfoUtil.getCompanyId() != null) {
            criteria.add(Restrictions.eq("id", HrmUserInfoUtil.getCompanyId()));
        }
        return criteria. list();
    }

}
