/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.PersonalDisciplineDao;
import com.inkubator.hrm.entity.PersonalDiscipline;
import com.inkubator.hrm.web.search.PersonalDisciplineSearchParameter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni
 */
@Repository(value = "personalDisciplineDao")
@Lazy
public class PersonalDisciplineDaoImpl extends IDAOImpl<PersonalDiscipline> implements PersonalDisciplineDao{

    @Override
    public Class<PersonalDiscipline> getEntityClass() {
        return PersonalDiscipline.class;
    }

    @Override
    public List<PersonalDiscipline> getAllDataWithAllRelation(PersonalDisciplineSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPersonalDisciplineByParam(searchParameter, criteria);
//        criteria.setFetchMode("empData", FetchMode.JOIN);
//        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("admonitionType", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalPersonalDisciplineByParam(PersonalDisciplineSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchPersonalDisciplineByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public PersonalDiscipline getEntityByPkWithAllRelation(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("empData", FetchMode.JOIN);
        criteria.setFetchMode("empData.bioData", FetchMode.JOIN);
//        criteria.setFetchMode("admonitionType", FetchMode.JOIN);
        criteria.setFetchMode("careerDisciplineType", FetchMode.JOIN);
        return (PersonalDiscipline) criteria.uniqueResult();
    }
    
    private void doSearchPersonalDisciplineByParam(PersonalDisciplineSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("empData", "empData", JoinType.INNER_JOIN);
        criteria.createAlias("empData.bioData", "bioData", JoinType.INNER_JOIN);
//        criteria.createAlias("admonitionType", "admonitionType", JoinType.INNER_JOIN);
        criteria.createAlias("careerDisciplineType", "careerDisciplineType", JoinType.INNER_JOIN);
        if (StringUtils.isNotEmpty(searchParameter.getEmpData())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", searchParameter.getEmpData(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", searchParameter.getEmpData(), MatchMode.START));
            criteria.add(disjunction);
        }
        if (StringUtils.isNotEmpty(searchParameter.getAdmonitionType())) {
            criteria.add(Restrictions.like("admonitionType.name", searchParameter.getAdmonitionType(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public List<PersonalDiscipline> getAllDataByEmployeeId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("empData.id", id));
//        criteria.setFetchMode("admonitionType", FetchMode.JOIN);
        criteria.setFetchMode("careerDisciplineType", FetchMode.JOIN);
        return criteria.list();
    }
}
