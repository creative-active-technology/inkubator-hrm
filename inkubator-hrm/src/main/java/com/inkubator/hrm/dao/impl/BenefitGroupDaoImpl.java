package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.BenefitGroupDao;
import com.inkubator.hrm.entity.BenefitGroup;
import com.inkubator.hrm.entity.PaySalaryComponent;
import com.inkubator.hrm.web.search.BenefitGroupSearchParameter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Taufik Hidayat
 */
@Repository(value = "benefitGroupDao")
@Lazy
public class BenefitGroupDaoImpl extends IDAOImpl<BenefitGroup> implements BenefitGroupDao {

    @Override
    public Class<BenefitGroup> getEntityClass() {
        return BenefitGroup.class;
    }

    @Override
    public List<BenefitGroup> getByParam(BenefitGroupSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBenefitGroupByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalBenefitGroupByParam(BenefitGroupSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchBenefitGroupByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchBenefitGroupByParam(BenefitGroupSearchParameter parameter, Criteria criteria) {
        if (parameter.getCode()!= null) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        
        if (parameter.getName()!= null) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public String getBenefitGroupNameByPk(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        return (String) criteria.setProjection(Projections.property("name")).uniqueResult();
    }

    @Override
    public List<BenefitGroup> getBenefitGroupData(Long id) {
        ProjectionList proList = Projections.projectionList();
        proList.add(Projections.groupProperty("modelReffernsil"));
        DetachedCriteria subQuery = DetachedCriteria.forClass(PaySalaryComponent.class);
        subQuery.createAlias("modelComponent", "mc").add(Restrictions.eq("mc.id", id));
        subQuery.setProjection(proList);
        
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Property.forName("id").notIn(subQuery));
        return criteria.list();
    }

}
