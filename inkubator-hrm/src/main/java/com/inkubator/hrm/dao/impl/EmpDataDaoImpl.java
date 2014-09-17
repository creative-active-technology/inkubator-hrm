/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Deni Husni FR
 */
@Repository(value = "empDataDao")
@Lazy
public class EmpDataDaoImpl extends IDAOImpl<EmpData> implements EmpDataDao {

    @Override
    public Class<EmpData> getEntityClass() {
        return EmpData.class;
    }

    @Override
    public Long getTotalByGender(Integer gender) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bioData.gender", gender));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeBetween(Date startDate, Date endDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", startDate));
        criteria.add(Restrictions.lt("bioData.dateOfBirth", endDate));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeLessThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.lt("bioData.dateOfBirth", date));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeMoreThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", date));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByDepartmentId(Long departmentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("jabatan.department.id", departmentId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchEmpDataByParam(EmpDataSearchParameter dataSearchParameter, Criteria criteria) {
        if (dataSearchParameter.getJabatanKode() != null) {
            criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.code", dataSearchParameter.getJabatanKode(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getJabatanName() != null) {
            criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getName() != null) {
            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", dataSearchParameter.getName(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bio.lastName", dataSearchParameter.getName(), MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        
    }

    @Override
    public EmpData getByEmpIdWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.departement", FetchMode.JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("paySalaryGrade", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.jabatanDeskripsis", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.jabatanSpesifikasis", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.jabatanSpesifikasis.specificationAbility", FetchMode.JOIN);
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        return (EmpData) criteria.uniqueResult();
    }

    @Override
    public EmpData getByBioDataWithDepartment(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("bioData.id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanGajiId", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanGajiId.department", FetchMode.JOIN);
        return (EmpData) criteria.uniqueResult();
    }

    @Override
    public Long getTotalByNIKandId(String nik, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("nik", nik));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNIK(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("nik", nik));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataWithRelation() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        return criteria.list();
    }

	@Override
	public List<EmpData> getAllDataByNameOrNik(String param) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
		Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("nik", param, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        return criteria.list();
	}
	
	@Override
    public EmpData getByIdWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        return (EmpData) criteria.uniqueResult();
	}
    
    @Override
    public EmpData getEntityByNik(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("nik", nik));
        return (EmpData) criteria.uniqueResult();
    }

	@Override
	public List<EmpData> getAllDataNotExistInUserByParam(String param, int firstResult, int maxResults, Order order) {
		DetachedCriteria subQuery = DetachedCriteria.forClass(HrmUser.class, "user").setProjection(Projections.property("user.id"));
		subQuery.add(Property.forName("employee.id").eqProperty("user.empData.id"));		
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass(), "employee");
		criteria.add(Subqueries.notExists(subQuery));
		criteria = this.doSearchNotExistInUserByParam(param, criteria);
        
		criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.addOrder(order);
		
		return criteria.list();
	}
	
	@Override
	public Long getTotalNotExistInUserByParam(String param) {
		DetachedCriteria subQuery = DetachedCriteria.forClass(HrmUser.class, "user").setProjection(Projections.property("user.id"));
		subQuery.add(Property.forName("employee.id").eqProperty("user.empData.id"));		
		
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass(), "employee");
		criteria.add(Subqueries.notExists(subQuery));
		criteria = this.doSearchNotExistInUserByParam(param, criteria);
		
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}
	
	private Criteria doSearchNotExistInUserByParam(String param, Criteria criteria){
		criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
		if(param != null){
			Disjunction disjunction = Restrictions.disjunction();
	        disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
	        disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
	        disjunction.add(Restrictions.like("nik", param, MatchMode.ANYWHERE));
	        criteria.add(disjunction);
		}
        return criteria;
	}

	@Override
	public List<EmpData> getAllDataByJabatanId(Long jabatanId, Order order) {
		Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
		criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
		criteria.addOrder(order);
		
		return criteria.list();
	}
}
