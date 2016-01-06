/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.JabatanDao;
import com.inkubator.hrm.entity.Jabatan;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.EmpEliminationViewModel;
import com.inkubator.hrm.web.model.KompetensiJabatanViewModel;
import com.inkubator.hrm.web.search.EmpEliminationSearchParameter;
import com.inkubator.hrm.web.search.JabatanSearchParameter;
import com.inkubator.hrm.web.search.KompetensiJabatanSearchParameter;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
@Repository(value = "jabatanDao")
@Lazy
public class JabatanDaoImpl extends IDAOImpl<Jabatan> implements JabatanDao {

    @Override
    public Class<Jabatan> getEntityClass() {
        return Jabatan.class;
    }
    
    private Criteria addJoinRelationsOfCompanyId(Criteria criteria, Long companyId){
    	criteria.createAlias("department", "department", JoinType.INNER_JOIN);
    	criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
    	criteria.add(Restrictions.eq("company.id", companyId));
    	
    	return criteria;
    }

    @Override
    public List<Jabatan> getByParam(JabatanSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        
//        criteria.setFetchMode("costCenter", FetchMode.JOIN);
//        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
//        criteria.setFetchMode("department", FetchMode.JOIN);
//        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
//        criteria.setFetchMode("jabatan", FetchMode.JOIN);
//        criteria.setFetchMode("paySalaryGrade", FetchMode.JOIN);
        
        criteria.createAlias("costCenter", "costCenter", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan.pangkat", "pangkat", JoinType.INNER_JOIN);
        criteria.createAlias("department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("unitKerja", "unitKerja", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan", "jabatan", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("paySalaryGrade", "paySalaryGrade", JoinType.INNER_JOIN);
        
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalJabatanByParam(JabatanSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("costCenter", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("department", FetchMode.JOIN);
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryGrade", FetchMode.JOIN);
        
        criteria.createAlias("costCenter", "costCenter", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan.pangkat", "pangkat", JoinType.INNER_JOIN);
        criteria.createAlias("department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("unitKerja", "unitKerja", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("paySalaryGrade", "paySalaryGrade", JoinType.INNER_JOIN);
        
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(JabatanSearchParameter parameter, Criteria criteria) {
    	/** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
       // criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
    	criteria.add(Restrictions.eq("company.id", HrmUserInfoUtil.getCompanyId()));
        if (StringUtils.isNotEmpty(parameter.getCode())) {
            criteria.add(Restrictions.like("code", parameter.getCode(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotEmpty(parameter.getName())) {
            criteria.add(Restrictions.like("name", parameter.getName(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getCostCenterName())) {            
            criteria.add(Restrictions.like("costCenter.name", parameter.getCostCenterName(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getDepartementName())) {
            criteria.add(Restrictions.like("department.departmentName", parameter.getDepartementName(), MatchMode.ANYWHERE));
        }
            
        if (StringUtils.isNotEmpty(parameter.getJabatan())) {          
            criteria.add(Restrictions.like("pangkat.pangkatName", parameter.getJabatan(), MatchMode.ANYWHERE));
        }

        if (StringUtils.isNotEmpty(parameter.getUnitKerjaName())) {           
            criteria.add(Restrictions.like("unitKerja.name", parameter.getUnitKerjaName(), MatchMode.ANYWHERE));
        }

        criteria.add(Restrictions.isNotNull("id"));
    }

    @Override
    public Jabatan getJabatanByLevelOne(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
//        criteria.add(Restrictions.eq("levelJabatan", level));
        // kasih restric terhadap company yang active......        
        criteria.add(Restrictions.isNull("jabatan"));
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public List<Jabatan> getJabatanByParentCode(String parentCode) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        
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
        criteria.setFetchMode("department.company", FetchMode.JOIN);
        criteria.setFetchMode("unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("jabatan", FetchMode.JOIN);
        return (Jabatan) criteria.uniqueResult();
    }

    @Override
    public List<Jabatan> getJabatansByLevel(Integer level) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        
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
        /** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        
        criteria.add(Restrictions.eq("department.id", id));
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
        /** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        
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
		/** automatically get relations of department, company 
         *  don't create alias for that entity, or will get error : duplicate association path */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("code", param, MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("name", param, MatchMode.ANYWHERE));
		criteria.add(disjunction);
		return criteria.list();
	}

	@Override
	public Jabatan getJabatanByCode(String code) {
		 Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
	     criteria.add(Restrictions.eq("code", code));
	     criteria.setFetchMode("costCenter", FetchMode.JOIN);
	     criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
	     criteria.setFetchMode("department", FetchMode.JOIN);
	     criteria.setFetchMode("department.company", FetchMode.JOIN);
	     criteria.setFetchMode("unitKerja", FetchMode.JOIN);
	     criteria.setFetchMode("jabatan", FetchMode.JOIN);
	     return (Jabatan) criteria.uniqueResult();
	}

	@Override
	public List<KompetensiJabatanViewModel> getByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter,
			int firstResult, int maxResults, Order order) {
		final StringBuilder query = new StringBuilder("SELECT jabatan.code AS jabatanCode,");
		query.append(" jabatan.id AS id,");
		query.append(" jabatan.name AS jabatanName,");
		query.append(" golonganJabatan.code AS golonganJabatanCode ");
		query.append(" FROM Jabatan jabatan ");
		query.append(" INNER JOIN jabatan.golonganJabatan golonganJabatan ");
		
		//filter by search param
        query.append(doSearchByParamForKompetensiJabatan(searchParameter));
        query.append(" ORDER BY " + order);
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryKompetensiJabatanViewModelByParam(hbm, searchParameter);
        
		return hbm.setMaxResults(maxResults).setFirstResult(firstResult)
				.setResultTransformer(Transformers.aliasToBean(KompetensiJabatanViewModel.class)).list();
	}

	@Override
	public Long getTotalByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter) {
		final StringBuilder query = new StringBuilder("SELECT COUNT(*) ");
		query.append(" FROM Jabatan jabatan  ");
		query.append(" INNER JOIN jabatan.golonganJabatan golonganJabatan");
		
		
		//filter by search param
        query.append(doSearchByParamForKompetensiJabatan(searchParameter));
        
        Query hbm = getCurrentSession().createQuery(query.toString());
        hbm = this.setValueQueryKompetensiJabatanViewModelByParam(hbm, searchParameter);
        return Long.valueOf(hbm.uniqueResult().toString());
		
	}
	
	private String doSearchByParamForKompetensiJabatan(KompetensiJabatanSearchParameter searchParameter){
		StringBuilder query = new StringBuilder();
		
		if(searchParameter.getCode() != null){
			query.append(" WHERE jabatan.code LIKE :code ");
		}
		if(searchParameter.getName() != null){
			query.append(" WHERE jabatan.name LIKE :name ");
		}
		if(searchParameter.getGolonganJabatan() != null){
			query.append(" WHERE golonganJabatan.code LIKE :golJabCode ");
		}
		return query.toString();
	}
	
	private Query setValueQueryKompetensiJabatanViewModelByParam(Query hbm, KompetensiJabatanSearchParameter searchParameter) {
        for (String param : hbm.getNamedParameters()) {
            if (StringUtils.equals(param, "code")) {
                hbm.setParameter("code", "%" + searchParameter.getCode() + "%");
            } else if (StringUtils.equals(param, "name")) {
                hbm.setParameter("name", "%" + searchParameter.getName() + "%");
            } else if (StringUtils.equals(param, "golJabCode")) {
                hbm.setParameter("golJabCode", "%" + searchParameter.getGolonganJabatan() + "%");
            }
        }
        return hbm;
    }
}
