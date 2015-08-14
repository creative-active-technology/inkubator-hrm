package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.EducationLevelDao;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.PaySalaryGrade;
import com.inkubator.hrm.web.search.EducationLevelSearchParameter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "educationLevelDao")
@Lazy
public class EducationLevelDaoImpl extends IDAOImpl<EducationLevel> implements EducationLevelDao {

    @Override
    public Class<EducationLevel> getEntityClass() {
        return EducationLevel.class;
    }

    @Override
    public List<EducationLevel> getByParam(EducationLevelSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(EducationLevelSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(EducationLevelSearchParameter searchParameter, Criteria criteria) {
        if(searchParameter.getName() != null){
            criteria.add(Restrictions.like("name", searchParameter.getName(), MatchMode.START));
        }
        if(searchParameter.getCode() != null){
            criteria.add(Restrictions.like("code", searchParameter.getCode(), MatchMode.START));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Long getTotalByNameAndNotId(String name, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLevelAndNotId(Integer level, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("level", level));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("name", name));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByLevel(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("level", level));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EducationLevel> getAllDataOrderByLevel() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.addOrder(Order.desc("level"));
        return criteria.list();
    }

    @Override
    public List<EducationLevel> getAllNameOrderByLevel() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.addOrder(Order.asc("level"));
        return criteria.list();

    }

	@Override
	public EducationLevel getByGradeNumber(int number) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("level", number));
        return (EducationLevel) criteria.uniqueResult();
	}
	
	@Override
    public void saveAndMarge(EducationLevel entity) {
        getCurrentSession().update(entity);
        getCurrentSession().flush();
    }
	
	@Override
	public Integer getCurrentMaxLevel() {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());        
        return (Integer) criteria.setProjection(Projections.max("level")).uniqueResult();
	}

}
