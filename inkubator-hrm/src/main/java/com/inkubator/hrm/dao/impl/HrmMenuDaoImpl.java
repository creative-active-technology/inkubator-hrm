package com.inkubator.hrm.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.HrmMenuDao;
import com.inkubator.hrm.entity.HrmMenu;
import com.inkubator.hrm.web.search.HrmMenuSearchParameter;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;

/**
 *
 * @author rizkykojek
 */
@Repository(value = "hrmMenuDao")
@Lazy
public class HrmMenuDaoImpl extends IDAOImpl<HrmMenu> implements HrmMenuDao {

    @Override
    public Class<HrmMenu> getEntityClass() {
        return HrmMenu.class;

    }

    @Override
    public List<HrmMenu> getByParam(HrmMenuSearchParameter parameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByParam(parameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(HrmMenuSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByParam(parameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchByParam(HrmMenuSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }
        criteria.add(Restrictions.isNotNull("id"));

        return criteria;
    }

    @Override
    public List<HrmMenu> getAllDataByLevel(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("menuLevel", level));
        return criteria.list();

    }

    @Override
    public HrmMenu getEntityByPkWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("hrmMenu", FetchMode.JOIN);
        return (HrmMenu) criteria.uniqueResult();
    }

    @Override
    public List<HrmMenu> getAllDataByLevelAndNotId(int level, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("menuLevel", level));
        if (id != null) {
            criteria.add(Restrictions.ne("id", id));
        }
        return criteria.list();
    }

    @Override
    public List<HrmMenu> getAllDataByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, int firstResult, int maxResults,
            Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByParamAndNotIds(parameter, ids, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByParamAndNotIds(parameter, ids, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchByParamAndNotIds(HrmMenuSearchParameter parameter, List<Long> ids, Criteria criteria) {
        criteria = doSearchByParam(parameter, criteria);
        if (ids != null && ids.size() > 0) {
            criteria.add(Restrictions.not(Restrictions.in("id", ids)));
        }
        return criteria;
    }

    @Override
    public List<HrmMenu> getAllDataByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByUserRolesAndHaveNoChild(criteria, parameter, exceptMenuIds, roles);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);

        return criteria.list();
    }

    @Override
    public Long getTotalByUserRolesAndHaveNoChild(String parameter, List<Long> exceptMenuIds, List<String> roles) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria = doSearchByUserRolesAndHaveNoChild(criteria, parameter, exceptMenuIds, roles);

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchByUserRolesAndHaveNoChild(Criteria criteria, String parameter, List<Long> exceptMenuIds, List<String> roles) {
        criteria.createAlias("hrmMenuRoles", "hrmMenuRoles");
        criteria.createAlias("hrmMenuRoles.hrmRole", "hrmRole");
        criteria.add(Restrictions.in("hrmRole.roleName", roles));
        if (parameter != null) {
            criteria.add(Restrictions.like("name", parameter, MatchMode.ANYWHERE));
        }
        if (exceptMenuIds != null && exceptMenuIds.size() > 0) {
            criteria.add(Restrictions.not(Restrictions.in("id", exceptMenuIds))); //where clause to except menu that already choose
        }
        criteria.add(Restrictions.isEmpty("hrmMenus")); //where clause to identified that this menu have no child

        return criteria;
    }

    /*private Criteria doSearchByParamAndNotRoleId(Long roleId, HrmMenuSearchParameter parameter, Criteria criteria) {
     Criterion orCondition  = Restrictions.disjunction().add(Restrictions.ne("h.hrmRole.id", roleId)).add(Restrictions.isNull("h.hrmRole.id"));
     criteria.createAlias("hrmMenuRoles", "h", JoinType.LEFT_OUTER_JOIN);
     criteria.add(orCondition);
     return doSearchByParam(parameter, criteria);
     }*/
    @Override
    public List<HrmMenu> getMunuByLevelOneAndRoleName(String roleName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("menuLevel", 1));
        criteria.add(Restrictions.eq("menuLevel", 1));
        return criteria.list();
    }

    @Override
    public List<HrmMenu> getlistChildByParentMenu(long parentId, String roleName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("hrmMenu", "hm", JoinType.INNER_JOIN);
        criteria.createAlias("hrmMenuRoles", "hrole", JoinType.INNER_JOIN);
        criteria.createAlias("hrole.hrmRole", "hrmRole", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("hm.id", parentId));
        criteria.add(Restrictions.eq("hrmRole.roleName", roleName));
        criteria.setFetchMode("hrmMenus", FetchMode.JOIN);
        criteria.setFetchMode("hrmMenus.hrmMenu", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();

    }

    @Override
    public HrmMenu getByPathRelative(String Name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("urlName", Name));
        return (HrmMenu) criteria.uniqueResult();
    }
}
