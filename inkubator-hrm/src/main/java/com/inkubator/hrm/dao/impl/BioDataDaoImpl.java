/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BioDataDao;
import com.inkubator.hrm.entity.BioData;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.search.BioDataSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "bioDataDao")
@Lazy
public class BioDataDaoImpl extends IDAOImpl<BioData> implements BioDataDao {

    @Override
    public Class<BioData> getEntityClass() {
        return BioData.class;
    }

    @Override
    public List<BioData> getByParam(BioDataSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBioDataByParam(parameter, criteria);
        criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.setFetchMode("maritalStatus", FetchMode.JOIN);
        
        criteria.createAlias("religion", "religion", JoinType.INNER_JOIN);
        criteria.createAlias("maritalStatus", "maritalStatus", JoinType.INNER_JOIN);
        
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(BioDataSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBioDataByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchBioDataByParam(BioDataSearchParameter parameter, Criteria criteria) {
        if (parameter.getName() != null) {
//            criteria.add(Restrictions.like("firstName", parameter.getParameter(), MatchMode.ANYWHERE));
//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.like("firstName", parameter.getName(), MatchMode.ANYWHERE));
//            disjunction.add(Restrictions.like("lastName", parameter.getName(), MatchMode.ANYWHERE));
//            criteria.add(disjunction);
            criteria.add(Restrictions.ilike("combineName", parameter.getName().toLowerCase(),MatchMode.ANYWHERE));
        }

        if (parameter.getEmailAddress() != null) {
            criteria.add(Restrictions.like("personalEmail", parameter.getEmailAddress(), MatchMode.ANYWHERE));
        }
        if (parameter.getNickName() != null) {
            criteria.add(Restrictions.like("nickname", parameter.getNickName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));

    }

    @Override
    public BioData getEntityByPKWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("city", FetchMode.JOIN);
        criteria.setFetchMode("race", FetchMode.JOIN);
        criteria.setFetchMode("religion", FetchMode.JOIN);
        criteria.setFetchMode("nationality", FetchMode.JOIN);
        criteria.setFetchMode("maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("dialect", FetchMode.JOIN);
        return (BioData) criteria.uniqueResult();
    }

    @Override
    public List<BioData> getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//        criteria.createAlias("empDatas", "emp",JoinType.INNER_JOIN);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("firstName", name, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("lastName", name, MatchMode.ANYWHERE));

        criteria.add(disjunction);
        criteria.add(Restrictions.isEmpty("empDatas"));
        criteria.addOrder(Order.asc("firstName"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(7);
        return criteria.list();
    }

    @Override
    public List<EmpDataMatrixModel> getAllAgeFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT DISTINCT DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0 AS ages2");
        query.append(" FROM hrm.bio_data GROUP BY ages2 ORDER BY ages2 ASC");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(EmpDataMatrixModel.class))
                .list();
    }

    @Override
    public Integer getTotalAgeByGenderMaleFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM(SELECT COUNT(*) AS banyak_data,");
        query.append(" DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0 AS age");
        query.append(" FROM bio_data WHERE gender=1");
        query.append(" GROUP BY age ORDER BY age ASC) as jumlahData");
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Integer.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public Integer getTotalAgeByGenderFemaleFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT count(*) FROM(SELECT COUNT(*) AS banyak_data,");
        query.append(" DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+1 AS age");
        query.append(" FROM bio_data WHERE gender=0");
        query.append(" GROUP BY age ORDER BY age ASC) as jumlahData");
        Query hbm = getCurrentSession().createSQLQuery(query.toString());
        return Integer.valueOf(hbm.uniqueResult().toString());
    }

    @Override
    public List<EmpDataMatrixModel> getTotalByAgeAndGenderMaleFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) AS banyakDatas,");
        query.append(" DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0 AS ages2 FROM bio_data WHERE gender = 1");
        query.append(" GROUP BY ages2 ORDER BY ages2 ASC");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(EmpDataMatrixModel.class))
                .list();
    }

    @Override
    public List<EmpDataMatrixModel> getTotalByAgeAndGenderFemaleFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) AS banyakDatas,");
        query.append(" DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0 AS ages2 FROM bio_data WHERE gender = 0");
        query.append(" GROUP BY ages2 ORDER BY ages2 ASC");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(EmpDataMatrixModel.class))
                .list();
    }

    @Override
    public List<EmpDataMatrixModel> getAllAgeByGenderFromBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT COUNT(*) AS banyakDatas, gender AS genders,");
        query.append(" DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0 AS ages2 FROM bio_data");
        query.append(" GROUP BY ages2,genders ORDER BY ages2 ASC");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(EmpDataMatrixModel.class))
                .list();
    }

 
}
