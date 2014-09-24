/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
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
 * @author Deni Husni FR
 */
@Repository(value = "jabatanDao")
@Lazy
public class JabatanDaoImpl extends IDAOImpl<Jabatan> implements JabatanDao {

    @Override
    public Class<Jabatan> getEntityClass() {
        return Jabatan.class;
    }

    @Override
    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("department", FetchMode.JOIN);
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryGrade", FetchMode.JOIN);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(JabatanSearchParameter parameter, Criteria criteria) {
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getCostCenterName())) {
            criteria.createAlias("costCenter", "cc", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("cc.name", parameter.getCostCenterName(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getDepartementName())) {
            criteria.createAlias("department", "dd", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("dd.departmentName", parameter.getDepartementName(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getJabatan())) {
            criteria.createAlias("golonganJabatan", "gj", JoinType.INNER_JOIN);
            criteria.createAlias("gj.pangkat", "pp", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("pp.pangkatName", parameter.getJabatan(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getUnitKerjaName())) {
            criteria.createAlias("unitKerja", "uk", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("uk.name", parameter.getUnitKerjaName(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Jabatan getJabatanByLevelOne(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("levelJabatan", level));
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public List<Jabatan> getJabatanByParentCode(String parentCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatan", "jb", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("jb.code", parentCode));
        criteria.setFetchMode("jabatans", FetchMode.JOIN);
        criteria.setFetchMode("jabatans.jabatan", FetchMode.JOIN);
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public Jabatan getJabatanByIdWithDetail(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("department", FetchMode.JOIN);
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public List<Jabatan> getJabatansByLevel(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("levelJabatan", level));
        return criteria.list();
    }

    @Override
    public Jabatan getByIdWithJobDeskripsi(long id) throws Exception {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("department", FetchMode.JOIN);
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("jabatanDeskripsis", FetchMode.JOIN);
        criteria.setFetchMode("jabatanSpesifikasis", FetchMode.JOIN);
        criteria.setFetchMode("jabatanSpesifikasis.specificationAbility", FetchMode.JOIN);
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public void saveAndMerge(Jabatan jabatan) {
        getCurrentSession().update(jabatan);
        getCurrentSession().flush();
    }

    @Override
    public List<Jabatan> getByDepartementId(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("department", "d", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("d.id", id));
        return criteria.list();

    }

    @Override
    public Jabatan getByIdWithSalaryGrade(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("paySalaryGrade", FetchMode.JOIN);
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public List<Jabatan> getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
        criteria.addOrder(Order.asc("name"));
        criteria.setFetchMode("department", FetchMode.JOIN);
        criteria.setFirstResult(0);
        criteria.setMaxResults(7);
        return criteria.list();
    }

	@Override
	public List<Jabatan> getAllDataByCodeOrName(String param) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("code", param, MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("name", param, MatchMode.ANYWHERE));
		criteria.add(disjunction);
		return criteria.list();
	}

}
