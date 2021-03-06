/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.config.SetFactoryBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import ch.lambdaj.Lambda;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.FingerMatchEmp;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.entity.OverTimeDistribution;
import com.inkubator.hrm.entity.PermitDistribution;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.RecruitAgreementNoticeViewModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.RecruitAgreementNoticeSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;
import com.inkubator.hrm.web.search.SearchEmployeeCandidateParameter;
import com.inkubator.hrm.web.search.TempAttendanceRealizationSearchParameter;

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

    private Criteria addJoinRelationsOfCompanyId(Criteria criteria, Long companyId) {
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("department.company", "company", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("company.id", companyId));

        return criteria;
    }

    @Override
    public Long getTotalByGender(Integer gender) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("bioData.gender", gender));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeBetween(Date startDate, Date endDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", startDate));
        criteria.add(Restrictions.lt("bioData.dateOfBirth", endDate));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeLessThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.lt("bioData.dateOfBirth", date));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeMoreThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", date));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByDepartmentId(Long departmentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.add(Restrictions.eq("department.id", departmentId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByParam(Long companyId, EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(companyId, searchParameter, criteria);
        criteria.addOrder(order);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
//        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);

//        criteria.createAlias("jabatanByJabatanId", "bioData", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("taxFree", "taxFree", JoinType.INNER_JOIN);
//        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
//        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
//        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
//        criteria.setFetchMode("taxFree", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(Long companyId, EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(companyId, searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(Long companyId, EmpDataSearchParameter dataSearchParameter, Criteria criteria) {

        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.createAlias("wtGroupWorking", "wtGroupWorking", JoinType.INNER_JOIN);
        if (dataSearchParameter.getJabatanKode() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.code", dataSearchParameter.getJabatanKode(), MatchMode.START));
        }

        if (dataSearchParameter.getJabatanName() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.START));
        }
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (dataSearchParameter.getName() != null) {
//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.like("bioData.firstName", dataSearchParameter.getName(), MatchMode.START));
//            disjunction.add(Restrictions.like("bioData.lastName", dataSearchParameter.getName(), MatchMode.START));
//            criteria.add(disjunction);
            criteria.add(Restrictions.ilike("bioData.combineName", dataSearchParameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
    }

    @Override
    public EmpData getByEmpIdWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department.company", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.jabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
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
    public EmpData getEmpDataWithBioDataAndMaritalStatusById(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
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
    public Long getTotalByNikandNotId(String nik, Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());

        criteria.add(Restrictions.eq("nik", nik));
        criteria.add(Restrictions.ne("id", id));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByNIK(String nik) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());

        criteria.add(Restrictions.eq("nik", nik));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByNameOrNik(String param, Long companyId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
        //criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        criteria.add(Restrictions.isNotNull("status"));
        criteria.add(Restrictions.not(Restrictions.in("status", Arrays.asList(HRMConstant.EMP_TERMINATION, HRMConstant.EMP_DISCHAGED,
                HRMConstant.EMP_LAID_OFF, HRMConstant.EMP_STOP_CONTRACT, HRMConstant.EMP_PENSION))));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("nik", param, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.setMaxResults(20);
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataByNameOrNik(String param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        //criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        criteria.add(Restrictions.isNotNull("status"));
        criteria.add(Restrictions.not(Restrictions.in("status", Arrays.asList(HRMConstant.EMP_TERMINATION, HRMConstant.EMP_DISCHAGED,
                HRMConstant.EMP_LAID_OFF, HRMConstant.EMP_STOP_CONTRACT, HRMConstant.EMP_PENSION))));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("nik", param, MatchMode.ANYWHERE));
        criteria.add(disjunction);
        criteria.setMaxResults(20);
        return criteria.list();
    }

    @Override
    public EmpData getByIdWithDetail(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
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

    private Criteria doSearchNotExistInUserByParam(String param, Criteria criteria) {

        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (param != null) {
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
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        criteria.addOrder(order);

        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataByGolJabatanIdAndDepartmentId(Long golJabatanId, Long departmentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.add(Restrictions.eq("golonganJabatan.id", golJabatanId));
        criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", departmentId));

        return criteria.list();
    }

    @Override
    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
//        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("wtGroupWorking", "wg", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
        if (model.getWorkingGroupId() != 0 || model.getWorkingGroupId() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("wtGroupWorking"));
            disjunction.add(Restrictions.not(Restrictions.eq("wg.id", model.getWorkingGroupId())));
            criteria.add(disjunction);
        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("department.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("department.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
            }
        }
        //employee type equal or like
        if (model.getEmployeeTypeLikeOrEqual() != 3) {
            if (Objects.equals(model.getEmployeeTypeLikeOrEqual(), HRMConstant.EMPLOYEE_TYPE_EQUAL)) {
                criteria.add(Restrictions.eq("empType.name", model.getEmployeeTypeName()));
            } else {
                criteria.add(Restrictions.like("empType.name", model.getEmployeeTypeName(), MatchMode.ANYWHERE));
            }
        }
        //gender
        criteria.add(Restrictions.eq("bio.gender", model.getGender()));
        //goljab
        if (model.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("goljab.id", model.getGolonganJabatanId()));
        }

        String sortBy;
        if (Objects.equals(model.getSortBy(), HRMConstant.SORT_BY_NIK)) {
            sortBy = "nik";
        } else {
            sortBy = "bio.firstName";
        }

        if (Objects.equals(model.getOrderBy(), HRMConstant.ORDER_BY_ASC)) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }
        return criteria.list();
    }

    @Override
    public List<EmpData> getEmployeeBySearchEmployeeLeave(DistributionLeaveSchemeModel model) {
        DetachedCriteria listEmp = DetachedCriteria.forClass(LeaveDistribution.class)
                .setProjection(Property.forName("empData.id"))
                .createAlias("leave", "lv", JoinType.INNER_JOIN)
                .add(Restrictions.eq("lv.id", model.getLeaveSchemeId()));
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

//        criteria.createAlias("leaveDistributions", "lv", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        criteria.add(Property.forName("id").notIn(listEmp));
        criteria.add(Restrictions.eq("status", HRMConstant.EMP_TERMINATION));
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
//        if (model.getLeaveSchemeId() != 0 || model.getLeaveSchemeId() != null) {
//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.isNull("lv.empData"));
//            disjunction.add(Restrictions.not(Restrictions.eq("lv.leave.id", model.getLeaveSchemeId())));
//            criteria.add(disjunction);
//        }
        //balance
//        if (model.getStartBalance() != 0.0){
//            criteria.add(Restrictions.eq("lv.balance", model.getStartBalance()));
//        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("department.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("department.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
            }
        }
        //employee type equal or likeS
        if (model.getEmployeeTypeLikeOrEqual() != 3) {
            if (Objects.equals(model.getEmployeeTypeLikeOrEqual(), HRMConstant.EMPLOYEE_TYPE_EQUAL)) {
                criteria.add(Restrictions.eq("empType.name", model.getEmployeeTypeName()));
            } else {
                criteria.add(Restrictions.like("empType.name", model.getEmployeeTypeName(), MatchMode.ANYWHERE));
            }
        }
        //gender
        criteria.add(Restrictions.eq("bio.gender", model.getGender()));
        //goljab
        if (model.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("goljab.id", model.getGolonganJabatanId()));
        }

        String sortBy;
        if (Objects.equals(model.getSortBy(), HRMConstant.SORT_BY_NIK)) {
            sortBy = "nik";
        } else {
            sortBy = "bio.firstName";
        }

        if (Objects.equals(model.getOrderBy(), HRMConstant.ORDER_BY_ASC)) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model) {
        DetachedCriteria listEmp = DetachedCriteria.forClass(OverTimeDistribution.class)
                .setProjection(Property.forName("empData.id"))
                .createAlias("wtOverTime", "wtOverTime", JoinType.INNER_JOIN)
                .add(Restrictions.eq("wtOverTime.id", model.getOverTimeId()));
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("overTimeDistributions", "ot", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("ot.wtOverTime", "wt", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.add(Property.forName("id").notIn(listEmp));
//        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        /*if (model.getOverTimeId() != 0 || model.getOverTimeId() != null) {

            Criterion andCondition = Restrictions.conjunction()
                    .add(Restrictions.isNotNull("ot.empData"))
                    .add(Restrictions.not(Restrictions.eq("wt.id", model.getOverTimeId())));

//                   criteria.add(Restrictions.isNull("ot.empData"));
            Criterion completeCondition
                    = Restrictions.disjunction().add(andCondition)
                    .add(Restrictions.isNull("ot.empData"));

//            Disjunction conjunction = Restrictions.conjunction();
//            disjunction.add(Restrictions.isNotNull("ot.empData"));
//            disjunction.add(Restrictions.not(Restrictions.eq("ot.wtOverTime.id", model.getOverTimeId())));
            criteria.add(completeCondition);
        }*/
        //balance
//        if (model.getStartBalance() != 0.0){
//            criteria.add(Restrictions.eq("lv.balance", model.getStartBalance()));
//        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("department.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("department.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
            }
        }
        //employee type equal or likeS
        if (model.getEmployeeTypeLikeOrEqual() != 3) {
            if (Objects.equals(model.getEmployeeTypeLikeOrEqual(), HRMConstant.EMPLOYEE_TYPE_EQUAL)) {
                criteria.add(Restrictions.eq("empType.name", model.getEmployeeTypeName()));
            } else {
                criteria.add(Restrictions.like("empType.name", model.getEmployeeTypeName(), MatchMode.ANYWHERE));
            }
        }
        //gender
        criteria.add(Restrictions.eq("bio.gender", model.getGender()));
        //goljab
        if (model.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("goljab.id", model.getGolonganJabatanId()));
        }

        String sortBy;
        if (Objects.equals(model.getSortBy(), HRMConstant.SORT_BY_NIK)) {
            sortBy = "nik";
        } else {
            sortBy = "bio.firstName";
        }

        if (Objects.equals(model.getOrderBy(), HRMConstant.ORDER_BY_ASC)) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<EmpData> getEmpDataByListId(List<Long> data) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.in("id", data));
        criteria.setFetchMode("jabatanByJabatanGajiId", FetchMode.JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanGajiId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanGajiId.unitKerja", FetchMode.JOIN);
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("wtGroupWorking", "wtGroupWorking", JoinType.LEFT_OUTER_JOIN);
        doSearchReportEmpWorkingGroupByParam(param, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchReportEmpWorkingGroupByParam(param, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, Criteria criteria) {
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        if (param.getDepartmentId() != null && param.getDepartmentId() != 0) {
            criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", param.getDepartmentId()));
        }

        if (StringUtils.isNotEmpty(param.getNikStart())) {
            criteria.add(Restrictions.ge("nik", param.getNikStart()));
        }

        if (StringUtils.isNotEmpty(param.getNikEnd())) {
            criteria.add(Restrictions.le("nik", param.getNikEnd()));
        }

        criteria.add(Restrictions.ne("status", HRMConstant.EMP_TERMINATION));
        return criteria;
    }

    /*    DetachedCriteria listEmp = DetachedCriteria.forClass(LeaveDistribution.class)
     .setProjection(Property.forName("empData.id"))
     .createAlias("leave", "lv", JoinType.INNER_JOIN)
     .add(Restrictions.eq("lv.id", model.getLeaveSchemeId()));
     Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
     */
    /**
     * automatically get relations of jabatanByJabatanId, department, company
     * don't create alias for that entity, or will get error : duplicate
     * association path
     *//*
     criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
     criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

     //    criteria.createAlias("leaveDistributions", "lv", JoinType.LEFT_OUTER_JOIN);
     criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
     criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
     criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
     criteria.add(Property.forName("id").notIn(listEmp));
     //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
     //    if (model.getLeaveSchemeId() != 0 || model.getLeaveSchemeId() != null) {
     //        Disjunction disjunction = Restrictions.disjunction();
     //        disjunction.add(Restrictions.isNull("lv.empData"));
     //        disjunction.add(Restrictions.not(Restrictions.eq("lv.leave.id", model.getLeaveSchemeId())));
     //        criteria.add(disjunction);
     //    }
     */ @Override
    public List<EmpData> getEmployeeBySearchEmployeePermit(PermitDistributionModel model) {
        DetachedCriteria listEmp = DetachedCriteria.forClass(PermitDistribution.class)
                .setProjection(Property.forName("empData.id"))
                .createAlias("permitClassification", "pc", JoinType.INNER_JOIN)
                .add(Restrictions.eq("pc.id", model.getPermitId()));
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("permitDistributions", "lv", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        criteria.add(Property.forName("id").notIn(listEmp));
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
        /*if (model.getPermitId() != 0 || model.getPermitId() != null) {
         Disjunction disjunction = Restrictions.disjunction();
         disjunction.add(Restrictions.isNull("permitDistributions"));
         disjunction.add(Restrictions.not(Restrictions.eq("lv.permitClassification.id", model.getPermitId())));
         //disjunction.add(Restrictions.sqlRestriction(sql, values, types));
         criteria.add(disjunction);
         }*/
        //balance
//        if (model.getStartBalance() != 0.0){
//            criteria.add(Restrictions.eq("lv.balance", model.getStartBalance()));
//        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("department.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("department.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
            }
        }
        //employee type equal or likeS
        if (model.getEmployeeTypeLikeOrEqual() != 3) {
            if (Objects.equals(model.getEmployeeTypeLikeOrEqual(), HRMConstant.EMPLOYEE_TYPE_EQUAL)) {
                criteria.add(Restrictions.eq("empType.name", model.getEmployeeTypeName()));
            } else {
                criteria.add(Restrictions.like("empType.name", model.getEmployeeTypeName(), MatchMode.ANYWHERE));
            }
        }
        //gender
        criteria.add(Restrictions.eq("bio.gender", model.getGender()));
        //goljab
        if (model.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("goljab.id", model.getGolonganJabatanId()));
        }

        String sortBy;
        if (Objects.equals(model.getSortBy(), HRMConstant.SORT_BY_NIK)) {
            sortBy = "nik";
        } else {
            sortBy = "bio.firstName";
        }

        if (Objects.equals(model.getOrderBy(), HRMConstant.ORDER_BY_ASC)) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        doSearchReportEmpDepartmentJabatanByParam(param, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        doSearchReportEmpDepartmentJabatanByParam(param, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, Criteria criteria) {
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());

        if (param.getDepartmentId() != null && param.getDepartmentId() != 0) {
            criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", param.getDepartmentId()));
        }

        if (param.getGolonganJabatanId() != null) {
            criteria.add(Restrictions.in("golonganJabatan.id", param.getGolonganJabatanId()));

        }

        return criteria;
    }

    public List<EmpData> getEmployeeBySearchEmployeeFingerException(WtFingerExceptionModel model) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
//        if (model.getLeaveSchemeId() != 0 || model.getLeaveSchemeId() != null) {
//            Disjunction disjunction = Restrictions.disjunction();
//            disjunction.add(Restrictions.isNull("lv.empData"));
//            disjunction.add(Restrictions.not(Restrictions.eq("lv.leave.id", model.getLeaveSchemeId())));
//            criteria.add(disjunction);
//        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("department.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("department.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
            }
        }
        //employee type equal or likeS
        if (model.getEmployeeTypeLikeOrEqual() != 3) {
            if (Objects.equals(model.getEmployeeTypeLikeOrEqual(), HRMConstant.EMPLOYEE_TYPE_EQUAL)) {
                criteria.add(Restrictions.eq("empType.name", model.getEmployeeTypeName()));
            } else {
                criteria.add(Restrictions.like("empType.name", model.getEmployeeTypeName(), MatchMode.ANYWHERE));
            }
        }
        //gender
        criteria.add(Restrictions.eq("bio.gender", model.getGender()));
        //goljab
        if (model.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("goljab.id", model.getGolonganJabatanId()));
        }

        String sortBy;
        if (Objects.equals(model.getSortBy(), HRMConstant.SORT_BY_NIK)) {
            sortBy = "nik";
        } else {
            sortBy = "bio.firstName";
        }

        if (Objects.equals(model.getOrderBy(), HRMConstant.ORDER_BY_ASC)) {
            criteria.addOrder(Order.asc(sortBy));
        } else {
            criteria.addOrder(Order.desc(sortBy));
        }
        return criteria.list();
    }

    @Override
    public EmpData getEmpDataWithBiodata(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("bioData.maritalStatus", "maritalStatus", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("maritalStatus", FetchMode.JOIN);
        return (EmpData) criteria.uniqueResult();
    }

    @Override
    public List<String> getAllNikBetween(String from, String until) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.ge("nik", from));
        criteria.add(Restrictions.le("nik", until));
        criteria.setProjection(Projections.property("nik"));
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataNotTerminate() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataNotTerminateWithSearchParameter(String nikOrName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (nikOrName != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("bioData.combineName", nikOrName.toLowerCase(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("nik", nikOrName, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllData() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataWithoutJoinCompany(String nikOrName) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        if (StringUtils.isNotEmpty(nikOrName)) {
            criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.ilike("bioData.combineName", nikOrName.toLowerCase(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("nik", nikOrName, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        return criteria.list();
    }

    @Override
    public Long getTotalEmpDataNotTerminate() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByTaxFreeIsNull() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.isNull("taxFree"));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Long companyId, Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.le("joinDate", date));

        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter param, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchSalaryConfirmationByParam(param, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();

    }

    @Override
    public Long getTotalSalaryConfirmationByParam(SalaryConfirmationParameter param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        this.doSearchSalaryConfirmationByParam(param, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchSalaryConfirmationByParam(SalaryConfirmationParameter param, Criteria criteria) {
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.add(Restrictions.isNotEmpty("payTempKalkulasis"));

        if (StringUtils.isNotEmpty(param.getNik())) {
            criteria.add(Restrictions.like("nik", param.getNik(), MatchMode.START));
        }

        if (StringUtils.isNotEmpty(param.getName())) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", param.getName(), MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", param.getName(), MatchMode.START));
            criteria.add(disjunction);
        }

        if (param.getGolonganJabatanId() != null && param.getGolonganJabatanId() != 0) {
            criteria.add(Restrictions.eq("golonganJabatan.id", param.getGolonganJabatanId()));
        }

        return criteria;
    }

    @Override
    public EmpData getByPKBankTransfer(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.bioBankAccounts", FetchMode.JOIN);
        criteria.setFetchMode("bioData.bioBankAccounts.bank", FetchMode.JOIN);
        return (EmpData) criteria.uniqueResult();
    }

    @Override
    public EmpData getByEmpDataByBioDataId(long bioDataid) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department.company", FetchMode.JOIN);
        criteria.setFetchMode("employeeType", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.add(Restrictions.eq("bioData.id", bioDataid));
        return (EmpData) criteria.uniqueResult();
    }

    @Override
    public BioDataModel getEmpNameWithNearestBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT bioData.first_name AS firstName, bioData.last_name AS lastName, bioData.date_of_birth AS dateOfBirth,");
        query.append("DATE_ADD(bioData.date_of_birth, INTERVAL IF (DAYOFYEAR(bioData.date_of_birth) >= DAYOFYEAR(CURDATE()), YEAR(CURDATE())-YEAR(bioData.date_of_birth), YEAR(CURDATE())-YEAR(bioData.date_of_birth)+1) YEAR) AS nextBirthday ");
        query.append("FROM emp_data empData ");
        query.append("INNER JOIN bio_data bioData ON bioData.id = empData.bio_data_id ");
        query.append("INNER JOIN jabatan jabatan ON jabatan.id = empData.jabatan_id ");
        query.append("INNER JOIN department department ON department.id = jabatan.departement_id ");
        query.append("INNER JOIN company company ON company.id = department.company_id ");
        query.append("WHERE company.id = " + HrmUserInfoUtil.getCompanyId() + " ");
        query.append("AND empData.status != '" + HRMConstant.EMP_TERMINATION + "' ");
        query.append("AND bioData.date_of_birth  IS NOT NULL HAVING nextBirthday ");
        query.append("BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY) ORDER BY nextBirthDay LIMIT 1");

        return (BioDataModel) getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(BioDataModel.class))
                .uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByDepartementAndEducation(List<Long> departementId, List<Long> educationId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doCreateAliasByDepartmentAndEducation(departementId, educationId, criteria);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public Long getTotalDataByDepartementAndEducation(List<Long> departementId, List<Long> educationId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doCreateAliasByDepartmentAndEducation(departementId, educationId, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doCreateAliasByDepartmentAndEducation(List<Long> departementId, List<Long> educationId, Criteria criteria) {
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("bioData.educationHistories", "educationHistories", JoinType.INNER_JOIN);
        criteria.createAlias("educationHistories.educationLevel", "educationLevel", JoinType.INNER_JOIN);
        if (departementId.isEmpty() != Boolean.TRUE) {
            criteria.add(Restrictions.in("department.id", departementId));
        }
        if (educationId.isEmpty() != Boolean.TRUE) {
            criteria.add(Restrictions.in("educationLevel.id", educationId));
        }
    }

    @Override
    public List<ReportEmployeeEducationViewModel> getAllDataByDepartementAndEducationWithHql(List<Long> departementId, List<Long> educationId, int firstResult, int maxResults, Order order) {
        final StringBuilder query = new StringBuilder("select department.departmentName as department,");
        query.append(" emp.id as id,");
        query.append(" emp.nik as nik,");
        query.append(" bio.firstName as firstName,");
        query.append(" bio.lastName as lastName,");
        query.append(" jabatanByJabatanId.name as jabatan,");
        query.append(" educationLevel.code as graduated,");
        query.append(" institutionEducation.institutionEducationName as from,");
        query.append(" eduHistory.yearOut as graduatedYear");
        query.append(" FROM EmpData emp");
        query.append(" INNER JOIN emp.bioData bio");
        query.append(" INNER JOIN bio.educationHistories eduHistory");
        query.append(" INNER JOIN eduHistory.educationLevel educationLevel");
        query.append(" INNER JOIN eduHistory.institutionEducation institutionEducation");
        query.append(" INNER JOIN emp.jabatanByJabatanId jabatanByJabatanId");
        query.append(" INNER JOIN jabatanByJabatanId.department department");
        query.append(" INNER JOIN department.company company");
        if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            query.append(" WHERE department.id IN :idDept AND educationLevel.id IN :idEdu");
            query.append(" AND company.id = " + HrmUserInfoUtil.getCompanyId());
        } else if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() == Boolean.TRUE) {
            query.append(" WHERE department.id IN :idDept");
            query.append(" AND company.id = " + HrmUserInfoUtil.getCompanyId());
        } else if (departementId.isEmpty() == Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            query.append(" WHERE educationLevel.id IN :idEdu");
            query.append(" AND company.id = " + HrmUserInfoUtil.getCompanyId());
        } else {
            query.append(" WHERE company.id = " + HrmUserInfoUtil.getCompanyId());
        }
        if (order.toString().contains("firstName") || order.toString().contains("department") || order.toString().contains("jabatan") || order.toString().contains("graduated") || order.toString().contains("graduatedYear")) {
            query.append(" order by " + order);
        } else {
            query.append(" order by bio.firstName");
        }
        if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameterList("idDept", departementId)
                    .setParameterList("idEdu", educationId)
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(ReportEmployeeEducationViewModel.class))
                    .list();
        } else if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() == Boolean.TRUE) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameterList("idDept", departementId)
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(ReportEmployeeEducationViewModel.class))
                    .list();
        } else if (departementId.isEmpty() == Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            return getCurrentSession().createQuery(query.toString())
                    .setParameterList("idEdu", educationId)
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(ReportEmployeeEducationViewModel.class))
                    .list();
        } else {
            return getCurrentSession().createQuery(query.toString())
                    .setMaxResults(maxResults).setFirstResult(firstResult)
                    .setResultTransformer(Transformers.aliasToBean(ReportEmployeeEducationViewModel.class))
                    .list();
        }
    }

    @Override
    public List<EmpData> getReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doCreateAliasByDepartmentAndEmployeeType(listDepartmentId, listEmpTypeId, criteria);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public Long getTotalReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doCreateAliasByDepartmentAndEmployeeType(listDepartmentId, listEmpTypeId, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doCreateAliasByDepartmentAndEmployeeType(List<Long> listDepartmentId, List<Long> listEmployeeTypeId, Criteria criteria) {
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());

        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.INNER_JOIN);
        if (!listDepartmentId.isEmpty()) {
            criteria.add(Restrictions.in("department.id", listDepartmentId));
        }
        if (!listEmployeeTypeId.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", listEmployeeTypeId));
        }
    }

    @Override
    public List<ReportEmpPensionPreparationModel> getReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges, int firstResult, int maxResults, Order order) {

        final StringBuilder query = new StringBuilder("SELECT empData.NIK AS nik, bioData.first_name AS firstName, bioData.last_name AS lastName, "
                + " empData.join_date AS tglMulaiBekerja, golonganJabatan.code AS golJabatan, bioData.date_of_birth AS tglLahir, "
                + " umur(bioData.date_of_birth , NOW()) AS usiaKaryawan, "
                + " jabatan.name AS jabatan, department.department_name AS departmentName, department.id AS departmentId, "
                + " employeeType.id AS empTypeId , employeeType.name AS statusKaryawan FROM emp_data empData "
                + " INNER JOIN golongan_jabatan golonganJabatan ON empData.gol_jab_id = golonganJabatan.id  "
                + " INNER JOIN bio_data bioData ON empData.bio_data_id = bioData.id  "
                + " INNER JOIN jabatan jabatan ON empData.jabatan_id = jabatan.id "
                + " INNER JOIN department department ON jabatan.departement_id = department.id "
                + " INNER JOIN company company ON company.id = department.company_id "
                + " INNER JOIN employee_type employeeType ON empData.emp_type_id = employeeType.id ");

        //Flag Untuk penanda apakah ada filter atau tidak
        //boolean isFiltered = !listDepartmentId.isEmpty() || !listEmpTypeId.isEmpty() || !listEmpAges.isEmpty();
        //Flag untuk penanda jika filter lebih dari satu
        //boolean multipleFilter = Boolean.FALSE;
        //if (isFiltered) {
        query.append(" WHERE company.id = " + HrmUserInfoUtil.getCompanyId() + " ");
        //}

        if (!listDepartmentId.isEmpty()) {
            query.append(" jabatan.departement_id IN( ");

            int size = listDepartmentId.size();
            //karena pakai native query, isi List harus di parsing satu per satu
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listDepartmentId.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listDepartmentId.get(i)));
                }
            }

            query.append(") ");
            //multipleFilter = Boolean.TRUE;
        }

        if (!listEmpTypeId.isEmpty()) {
            //if (multipleFilter) {
            query.append("AND empData.emp_type_id IN( ");
            /*} else {
             query.append(" e.emp_type_id IN( ");
             multipleFilter = Boolean.TRUE;
             }*/

            //karena pakai native query, isi List harus di parsing satu per satu
            int size = listEmpTypeId.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listEmpTypeId.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listEmpTypeId.get(i)));
                }
            }

            query.append(") ");
        }

        if (!listEmpAges.isEmpty()) {
            //if (multipleFilter) {
            query.append(" AND umur(bioData.date_of_birth , NOW()) IN( ");
            /*} else {
             query.append("umur(b.date_of_birth , NOW()) IN( ");
             }*/

            //karena pakai native query, isi List harus di parsing satu per satu
            int size = listEmpAges.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listEmpAges.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listEmpAges.get(i)));
                }
            }

            query.append(") ");
        }

        query.append(" ORDER BY ");

        if (StringUtils.equals("nik", order.getPropertyName())) {
            query.append("empData.nik ");
        } else if (StringUtils.equals("firstName", order.getPropertyName())) {
            query.append("bioData.first_name ");
        } else if (StringUtils.equals("tglMulaiBekerja", order.getPropertyName())) {
            query.append("empData.join_date ");
        } else if (StringUtils.equals("golJabatan", order.getPropertyName())) {
            query.append("golonganJabatan.code ");
        } else if (StringUtils.equals("jabatan", order.getPropertyName())) {
            query.append("jabatan.name ");
        } else if (StringUtils.equals("usiaKaryawan", order.getPropertyName())) {
            query.append("umur(bioData.date_of_birth , NOW()) ");
        }

        query.append(order.isAscending() ? " ASC " : " DESC ");

        //Limit query based on paging parameter
        query.append("LIMIT ").append(firstResult).append(",").append(maxResults).append(" ");

        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(ReportEmpPensionPreparationModel.class))
                .list();

    }

    @Override
    public Long getTotalReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges) {

        final StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM "
                + "(SELECT empData.NIK AS nik, bioData.first_name AS firstName, bioData.last_name AS lastName, "
                + " empData.join_date AS tglMulaiBekerja, golonganJabatan.code AS golJabatan, bioData.date_of_birth AS tglLahir, "
                + " umur(bioData.date_of_birth , NOW()) AS usiaKaryawan, "
                + " jabatan.name AS jabatan, department.department_name AS departmentName, department.id AS departmentId, "
                + " employeeType.id AS empTypeId , employeeType.name AS statusKaryawan FROM emp_data empData "
                + " INNER JOIN golongan_jabatan golonganJabatan ON empData.gol_jab_id = golonganJabatan.id  "
                + " INNER JOIN bio_data bioData ON empData.bio_data_id = bioData.id  "
                + " INNER JOIN jabatan jabatan ON empData.jabatan_id = jabatan.id "
                + " INNER JOIN department department ON jabatan.departement_id = department.id "
                + " INNER JOIN company company ON company.id = department.company_id "
                + " INNER JOIN employee_type employeeType ON empData.emp_type_id = employeeType.id ");

        //Flag Untuk penanda apakah ada filter atau tidak
        //boolean isFiltered = !listDepartmentId.isEmpty() || !listEmpTypeId.isEmpty() || !listEmpAges.isEmpty();
        //Flag untuk penanda jika filter lebih dari satu
        //boolean multipleFilter = Boolean.FALSE;
        //if (isFiltered) {
        query.append(" WHERE company.id = " + HrmUserInfoUtil.getCompanyId() + " ");
        //}

        if (!listDepartmentId.isEmpty()) {
            query.append(" jabatan.departement_id IN( ");

            //karena pakai native query, isi List harus di parsing satu per satu
            int size = listDepartmentId.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listDepartmentId.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listDepartmentId.get(i)));
                }
            }

            query.append(") ");
            // multipleFilter = Boolean.TRUE;
        }

        if (!listEmpTypeId.isEmpty()) {
            //if (multipleFilter) {
            query.append("AND empData.emp_type_id IN( ");
            /*} else {
             query.append(" e.emp_type_id IN( ");
             multipleFilter = Boolean.TRUE;
             }*/

            //karena pakai native query, isi List harus di parsing satu per satu
            int size = listEmpTypeId.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listEmpTypeId.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listEmpTypeId.get(i)));
                }
            }

            query.append(") ");
        }

        if (!listEmpAges.isEmpty()) {
            //if (multipleFilter) {
            query.append(" AND umur(bioData.date_of_birth , NOW()) IN( ");
            /* } else {
             query.append("umur(b.date_of_birth , NOW()) IN( ");
             }*/

            //karena pakai native query, isi List harus di parsing satu per satu
            int size = listEmpAges.size();
            for (int i = 0; i < size; i++) {
                if (i < (size - 1)) {
                    query.append(String.valueOf(listEmpAges.get(i)));
                    query.append(" , ");
                } else {
                    query.append(String.valueOf(listEmpAges.get(i)));
                }
            }

            query.append(") ");
        }

        query.append(" ) AS jumlahRow ");

        return Long.valueOf(getCurrentSession().createSQLQuery(query.toString()).uniqueResult().toString());
    }

    @Override
    public List<EmpData> getAllDataByDepartmentAndReligionAndGolJabAndEmpType(List<Long> departmentIds, List<Long> religionIds, List<Long> golJabIds, List<Long> empTypeIds) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("bioData.religion", "religion", JoinType.INNER_JOIN);

        if (!departmentIds.isEmpty()) {
            criteria.add(Restrictions.in("department.id", departmentIds));
        }
        if (!religionIds.isEmpty()) {
            criteria.add(Restrictions.in("religion.id", religionIds));
        }
        if (!golJabIds.isEmpty()) {
            criteria.add(Restrictions.in("golonganJabatan.id", golJabIds));
        }
        if (!empTypeIds.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", empTypeIds));
        }

        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataByParamWithDetail(List<Department> deptId, List<GolonganJabatan> golJabId, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik, int firstResult, int maxResults, Order order) {
        List<Long> listDepartment = new ArrayList<Long>();
        List<Long> listGolJab = new ArrayList<Long>();
        for (Department department : deptId) {
            listDepartment.add(department.getId());
        }
        for (GolonganJabatan golonganJabatan : golJabId) {
            listGolJab.add(golonganJabatan.getId());
        }
        final org.hibernate.type.Type[] typeJoinDate = new org.hibernate.type.Type[listJoinDate.size()];
        Arrays.fill(typeJoinDate, org.hibernate.type.StandardBasicTypes.INTEGER);
        final org.hibernate.type.Type[] typeAge = new org.hibernate.type.Type[listAge.size()];
        Arrays.fill(typeAge, org.hibernate.type.StandardBasicTypes.INTEGER);

        final StringBuilder joinDateList = new StringBuilder();
        final StringBuilder ageList = new StringBuilder();

        for (int i = 0; i < listJoinDate.size(); i++) {
            if (i > 0) {
                joinDateList.append(",");
            }
            joinDateList.append("?");
        }
        for (int i = 0; i < listAge.size(); i++) {
            if (i > 0) {
                ageList.append(",");
            }
            ageList.append("?");
        }
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

//        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.LEFT_OUTER_JOIN);
//        criteria.createCriteria("bioData", "bio", JoinType.LEFT_OUTER_JOIN);
        if (!deptId.isEmpty()) {
            criteria.add(Restrictions.in("department.id", listDepartment));
        }

        if (!golJabId.isEmpty()) {
            criteria.add(Restrictions.in("golonganJabatan.id", listGolJab));
        }

        if (empTypeName.length != 0) {
            criteria.add(Restrictions.in("employeeType.name", empTypeName));
        }

        if (listJoinDate.get(0) != 0) {
            criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS({alias}.join_date)), '%Y')+0 in (" + joinDateList.toString() + ")", listJoinDate.toArray(), typeJoinDate));

        }
        if (listAge.get(0) != 0) {
//            criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
            Criteria criteriaBiodata = criteria.createCriteria("bioData", JoinType.LEFT_OUTER_JOIN);
//            criteria.createCriteria("bioData", JoinType.LEFT_OUTER_JOIN);
            criteriaBiodata.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS({alias}.date_of_birth)), '%Y')+0 in (" + ageList.toString() + ")", listAge.toArray(), typeAge));
        }

        if (!listNik.isEmpty()) {
            criteria.add(Restrictions.in("nik", listNik));
        }
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamWithDetail(List<Department> deptId, List<GolonganJabatan> golJabId, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik) {
        List<Long> listDepartment = new ArrayList<Long>();
        List<Long> listGolJab = new ArrayList<Long>();
        for (Department department : deptId) {
            listDepartment.add(department.getId());
        }
        for (GolonganJabatan golonganJabatan : golJabId) {
            listGolJab.add(golonganJabatan.getId());
        }
        final org.hibernate.type.Type[] typeJoinDate = new org.hibernate.type.Type[listJoinDate.size()];
        Arrays.fill(typeJoinDate, org.hibernate.type.StandardBasicTypes.INTEGER);
        final org.hibernate.type.Type[] typeAge = new org.hibernate.type.Type[listAge.size()];
        Arrays.fill(typeAge, org.hibernate.type.StandardBasicTypes.INTEGER);

        final StringBuilder joinDateList = new StringBuilder();
        final StringBuilder ageList = new StringBuilder();

        for (int i = 0; i < listJoinDate.size(); i++) {
            if (i > 0) {
                joinDateList.append(",");
            }
            joinDateList.append("?");
        }
        for (int i = 0; i < listAge.size(); i++) {
            if (i > 0) {
                ageList.append(",");
            }
            ageList.append("?");
        }
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

//        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.LEFT_OUTER_JOIN);
//        criteria.createCriteria("bioData", "bio", JoinType.LEFT_OUTER_JOIN);
        if (!deptId.isEmpty()) {
            criteria.add(Restrictions.in("department.id", listDepartment));
        }

        if (!golJabId.isEmpty()) {
            criteria.add(Restrictions.in("golonganJabatan.id", listGolJab));
        }

        if (empTypeName.length != 0) {
            criteria.add(Restrictions.in("employeeType.name", empTypeName));
        }

        if (listJoinDate.get(0) != 0) {
            criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS({alias}.join_date)), '%Y')+0 in (" + joinDateList.toString() + ")", listJoinDate.toArray(), typeJoinDate));

        }
        if (listAge.get(0) != 0) {
            Criteria criteriaBiodata = criteria.createCriteria("bioData", JoinType.LEFT_OUTER_JOIN);
//            criteria.createCriteria("bioData", JoinType.LEFT_OUTER_JOIN);
            criteriaBiodata.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS({alias}.date_of_birth)), '%Y')+0 in (" + ageList.toString() + ")", listAge.toArray(), typeAge));
        }

        if (!listNik.isEmpty()) {
            criteria.add(Restrictions.in("nik", listNik));
        }

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("jabatanByJabatanId.unitKerja", "unitKerja", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId.golonganJabatan", "goljab", JoinType.LEFT_OUTER_JOIN);
//        criteria.createAlias("department.company", "company", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.LEFT_OUTER_JOIN);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        if (!empTypeId.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", empTypeId));
        }
        if (!golJabId.isEmpty()) {
            criteria.add(Restrictions.in("goljab.id", golJabId));
        }
        if (!unitKerjaId.isEmpty()) {
            criteria.add(Restrictions.in("unitKerja.id", unitKerjaId));
        }
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("jabatanByJabatanId.unitKerja", "unitKerja", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId.golonganJabatan", "goljab", JoinType.LEFT_OUTER_JOIN);
//        criteria.createAlias("department.company", "company", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.LEFT_OUTER_JOIN);
        if (!empTypeId.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", empTypeId));
        }
        if (!golJabId.isEmpty()) {
            criteria.add(Restrictions.in("goljab.id", golJabId));
        }
        if (!unitKerjaId.isEmpty()) {
            criteria.add(Restrictions.in("unitKerja.id", unitKerjaId));
        }

        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByCompanyIdAndEmpTypeAndGolJabAndUnitKerja(Long companyId, List<Long> empTypes, List<Long> golJabs, List<Long> unitKerjas) {
        StringBuffer selectQuery = new StringBuffer(
                "SELECT empData "
                + "FROM EmpData as empData "
                + "INNER JOIN empData.jabatanByJabatanId as jabatan "
                + "INNER JOIN jabatan.unitKerja as unitKerja "
                + "INNER JOIN jabatan.department as department "
                + "INNER JOIN department.company as company "
                + "INNER JOIN empData.employeeType as employeeType "
                + "INNER JOIN empData.golonganJabatan as golonganJabatan "
                + "WHERE company.id = :companyId "
                + "AND status != :status ");

        if (!empTypes.isEmpty()) {
            selectQuery.append("AND employeeType.id IN (:employeeTypes) ");
        }
        if (!golJabs.isEmpty()) {
            selectQuery.append("AND golonganJabatan.id IN (:golonganJabatans) ");
        }
        if (!unitKerjas.isEmpty()) {
            selectQuery.append("AND unitKerja.id IN (:unitKerjas) ");
        }

        Query hbm = getCurrentSession().createQuery(selectQuery.toString())
                .setParameter("companyId", companyId)
                .setParameter("status", HRMConstant.EMP_TERMINATION);
        if (!empTypes.isEmpty()) {
            hbm.setParameterList("employeeTypes", empTypes);
        }
        if (!golJabs.isEmpty()) {
            hbm.setParameterList("golonganJabatans", golJabs);
        }
        if (!unitKerjas.isEmpty()) {
            hbm.setParameterList("unitKerjas", unitKerjas);
        }

        return hbm.list();
    }

    @Override
    public Long getTotalKaryawanByJabatanId(Long companyId, Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
    
    @Override
    public Long getTotalKaryawanByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getByParam(String nikOrNameSearchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(nikOrNameSearchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalEmpDataByParam(String nikOrNameSearchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(nikOrNameSearchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchEmpDataByParam(String nikOrNameSearchParameter, Criteria criteria) {

        if (nikOrNameSearchParameter != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("nik", nikOrNameSearchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.firstName", nikOrNameSearchParameter, MatchMode.START));
            disjunction.add(Restrictions.like("bioData.lastName", nikOrNameSearchParameter, MatchMode.START));
            criteria.add(disjunction);
        }
    }

    @Override
    public List<SearchEmployeeCandidateViewModel> getAllDataEmpCandidateByParamWithDetail(SearchEmployeeCandidateParameter searchEmployeeCandidateParameter) {

        String listEducationId = Lambda.join(searchEmployeeCandidateParameter.getListEducationlevelId(), "','");
        StringBuffer selectQuery = new StringBuffer(
                " SELECT empData.id AS empDataId, empData.nik AS nik, "
                + " bioData.first_name AS firstName, bioData.last_name AS lastName, "
                + " jabatan.id AS idJabatan, jabatan.name AS jabatanName, "
                + "  (	SELECT educationLevelInner.id FROM bio_education_history bioEduInner "
                + "  	INNER JOIN education_level educationLevelInner ON bioEduInner.pendidikan_level_id =  educationLevelInner.id "
                + " 	INNER JOIN bio_data bioDataInner ON bioEduInner.biodata_id = bioDataInner.id  "
                + " 	WHERE bioDataInner.id = bioData.id "
                + "		AND educationLevelInner.id IN ('" + listEducationId + "') "
                + " 	AND bioEduInner.score > " + searchEmployeeCandidateParameter.getGpa() + "  LIMIT 1 "
                + "  ) 	AS lastEducationLevelId, "
                + " religion.id AS idReligion, religion.name AS religionName "
                + " FROM emp_data empData "
                + " INNER JOIN  jabatan jabatan ON empData.jabatan_id = jabatan.id "
                + " INNER JOIN department department ON jabatan.departement_id = department.id "
                + " INNER JOIN company company  ON department.company_id = company.id "
                + " LEFT OUTER JOIN bio_data bioData ON empData.bio_data_id = bioData.id "
                + " INNER JOIN religion religion ON bioData.agama_id = religion.id "
                + " WHERE company.id = '" + HrmUserInfoUtil.getCompanyId() + "' ");

        selectQuery.append(setQueryParamForEmpCandidateSearchQuery(searchEmployeeCandidateParameter));
        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
        return hbm.setResultTransformer(Transformers.aliasToBean(SearchEmployeeCandidateViewModel.class)).list();

    }

    @Override
    public Long getTotalEmpCandidateByParamWithDetail(SearchEmployeeCandidateParameter searchEmployeeCandidateParameter) {

        StringBuffer selectQuery = new StringBuffer(
                " SELECT COUNT(*) FROM "
                + " (SELECT empData.id FROM emp_data  empData "
                + " INNER JOIN  jabatan jabatan ON empData.jabatan_id = jabatan.id "
                + " INNER JOIN department department ON jabatan.departement_id = department.id "
                + " INNER JOIN company company  ON department.company_id = company.id "
                + " LEFT OUTER JOIN bio_data bioData ON empData.bio_data_id = bioData.id "
                + " INNER JOIN religion religion ON bioData.agama_id = religion.id "
                + " WHERE company.id = '" + HrmUserInfoUtil.getCompanyId() + "' ");

        selectQuery.append(setQueryParamForEmpCandidateSearchQuery(searchEmployeeCandidateParameter));
        selectQuery.append(" ) as totalRows");
        Query hbm = getCurrentSession().createSQLQuery(selectQuery.toString());
        return Long.valueOf(hbm.uniqueResult().toString());
    }

    private String setQueryParamForEmpCandidateSearchQuery(SearchEmployeeCandidateParameter searchEmployeeCandidateParameter) {

        StringBuffer stringBuffer = new StringBuffer();
        String listEducationlevelId = Lambda.join(searchEmployeeCandidateParameter.getListEducationlevelId(), "','");
        String listAges = Lambda.join(searchEmployeeCandidateParameter.getListAge(), "','");
        String listJabatanId = Lambda.join(searchEmployeeCandidateParameter.getListJabatanId(), "','");
        String listReligionId = Lambda.join(searchEmployeeCandidateParameter.getListReligionId(), "','");
        String listJoinDate = Lambda.join(searchEmployeeCandidateParameter.getListJoinDate(), "','");

        stringBuffer.append(" AND  EXISTS "
                + " 	( SELECT bioEduGpa.score FROM bio_education_history bioEduGpa "
                + " 	  INNER JOIN education_level eduGpa ON bioEduGpa.pendidikan_level_id = eduGpa.id  "
                + " 	  INNER JOIN bio_data bioDataInnerGpa ON bioEduGpa.biodata_id = bioDataInnerGpa.id "
                + " 	  WHERE bioDataInnerGpa.id = bioData.id AND eduGpa.id IN ( '" + listEducationlevelId + "' ) "
                + "       AND bioEduGpa.score > " + searchEmployeeCandidateParameter.getGpa()
                + "     ) ");

        //Filter by gender
        if (!StringUtils.equals("Any", searchEmployeeCandidateParameter.getGender())) {
            if (StringUtils.equals("Male", searchEmployeeCandidateParameter.getGender())) {
                stringBuffer.append("AND bioData.gender =  " + HRMConstant.GLOBAL_MALE);
            } else if (StringUtils.equals("Female", searchEmployeeCandidateParameter.getGender())) {
                stringBuffer.append("AND bioData.gender = " + HRMConstant.GLOBAL_FEMALE);
            }
        }

        //filter by ages range
        if (!searchEmployeeCandidateParameter.getListAge().isEmpty()) {
            stringBuffer.append("AND umur(bioData.date_of_birth , NOW()) in ( '" + listAges + "' ) ");
        }

        //filter by jabatan range
        if (!searchEmployeeCandidateParameter.getListJabatanId().isEmpty()) {
            stringBuffer.append("AND jabatan.id IN ( '" + listJabatanId + "' ) ");
        }

        //filter by religion range
        if (!searchEmployeeCandidateParameter.getListReligionId().isEmpty()) {
            stringBuffer.append("AND religion.id IN ( '" + listReligionId + "' ) ");
        }

        //filter by joinDate (convert to total working in years) range
        if (searchEmployeeCandidateParameter.getListJoinDate().get(0) != 0) {
            stringBuffer.append(" AND DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(empData.join_date)), '%Y')+0 in ( '" + listJoinDate + "' ) ");
        }

        return stringBuffer.toString();
    }

    @Override
    public String getBioDataNameByEmpDataId(Long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        return (String) criteria.setProjection(Projections.property("bioData.firstName")).uniqueResult();
    }

    @Override
    public Boolean isEmpDataWithNullWtGroupWorkingExist() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        criteria.add(Restrictions.isNull("wtGroupWorking"));
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        return !criteria.list().isEmpty();
    }

    @Override
    public List<EmpData> getAllDataNotTerminatePaging(TempAttendanceRealizationSearchParameter parameter, int firstResult, int maxResult, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        if (parameter.getNik() != null) {
            criteria.add(Restrictions.like("nik", parameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        if (parameter.getName() != null) {
//            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        	Disjunction orCondition = Restrictions.disjunction();
        	orCondition.add(Restrictions.ilike("bio.combineName", parameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        	orCondition.add(Restrictions.like("nik", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(orCondition);
        }

        //String sorting = "bio." + order;
        criteria.addOrder(order);
//        if (order==null) {
//            criteria.addOrder(order);
//        } else {
//            if (order.isAscending()) {
//             
//                criteria.addOrder(Order.asc(sorting));
//            } else {
//                
//                criteria.addOrder(Order.desc(sorting));
//            }
//        }

        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    /*
     * Query realisasi kehadiran per departemen dalam range date tertentu, 
     *  lalu di transform ke class DepAttendanceRealizationViewModel
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DepAttendanceRealizationViewModel> getListDepAttendanceByDepartmentIdAndRangeDate(Long departmentId, Date dateFrom, Date dateUntill) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder query = new StringBuilder(" SELECT  jabatan.departement_id AS departmentId, "
                + " WEEK(tempJadwalKaryawan.tanggal_waktu_kerja) AS weekNumber,"
                + " COUNT(tempJadwalKaryawan.working_our_id) as attendanceSchedule ,"
                + " COUNT(tempProcessReadFinger.working_hour_id) as attendanceReal,"
                + " (COUNT(tempProcessReadFinger.working_hour_id) / COUNT(tempJadwalKaryawan.working_our_id)) as attendancePercentage"
                + " FROM temp_jadwal_karyawan tempJadwalKaryawan"
                + " LEFT JOIN temp_process_read_finger tempProcessReadFinger ON tempProcessReadFinger.emp_data_id = tempJadwalKaryawan.emp_id"
                + " AND tempProcessReadFinger.schedule_date = tempJadwalKaryawan.tanggal_waktu_kerja"
                + " INNER JOIN emp_data empData ON tempJadwalKaryawan.emp_id = empData.id"
                + " INNER JOIN jabatan jabatan ON empData.jabatan_id = jabatan.id"
                + " INNER JOIN wt_working_hour wtWorkingHour ON tempJadwalKaryawan.working_our_id = wtWorkingHour.id"
                + " WHERE tempJadwalKaryawan.tanggal_waktu_kerja BETWEEN '" + dateFormat.format(dateFrom) + "' AND '" + dateFormat.format(dateUntill) + "' "
                + " AND wtWorkingHour.code <> 'OFF'"
                + " AND jabatan.departement_id =  " + departmentId
                + " AND empData.status <> '" + HRMConstant.EMP_TERMINATION + "' "
                + " GROUP BY WEEK(tempJadwalKaryawan.tanggal_waktu_kerja) , jabatan.departement_id ; ");

        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(DepAttendanceRealizationViewModel.class))
                .list();
    }

    @Override
    public Long getTotalNotTerminatePaging(TempAttendanceRealizationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        if (parameter.getNik() != null) {
            criteria.add(Restrictions.like("nik", parameter.getNik(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            Disjunction orCondition = Restrictions.disjunction();
        	orCondition.add(Restrictions.ilike("bio.combineName", parameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        	orCondition.add(Restrictions.like("nik", parameter.getName(), MatchMode.ANYWHERE));
            criteria.add(orCondition);
        }
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public EmpData getByIdWithBioData(long id) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("id", id));
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        return (EmpData) criteria.uniqueResult();
    }

    /*
     * Get Id Departemen dengan root parent idDepartment, 
     * misal idDepartment = 7,
     * maka dia akan mencari id department dengan parent_id = 7, 
     * dan begitu seterusnya, masing - masing department tersebut akan di query lagi secara recursive sampai level terbawah.
     * return dalam bentuk string : idDep, idDep, idDep (ex : 5,6,8)
     * Reference : http://stackoverflow.com/questions/28363893/mysql-select-recursive-get-all-child-with-multiple-level/28366310
     */
    @Override
    public String getIdChildDepRecursiveByDepartmentId(Long idDepartment) {
        StringBuilder query = new StringBuilder(" SELECT GROUP_CONCAT(lv SEPARATOR ',') FROM ("
                + "	SELECT @parameter \\:=(SELECT GROUP_CONCAT(id SEPARATOR ',') FROM department "
                + " WHERE FIND_IN_SET(parent_id, @parameter) ) AS lv FROM department JOIN "
                + "	(SELECT @parameter \\:= " + idDepartment + ")tmp"
                + "	WHERE parent_id IN (@parameter)) a; ");

        return (String) getCurrentSession().createSQLQuery(query.toString())
                .uniqueResult();
    }

    @Override
    public List<DepAttendanceRealizationViewModel> getListDepAttendanceByListRangeDepIdAndRangeDate(
            String rangeDepId, Date dateFrom, Date dateUntill) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        StringBuilder query = new StringBuilder(" SELECT  jabatan.departement_id AS departmentId, "
                + " WEEK(tempJadwalKaryawan.tanggal_waktu_kerja) AS weekNumber,"
                + " COUNT(tempJadwalKaryawan.working_our_id) as attendanceSchedule ,"
                + " COUNT(tempProcessReadFinger.finger_in) as attendanceReal,"
                + " (COUNT(tempProcessReadFinger.finger_in) / COUNT(tempJadwalKaryawan.working_our_id)) as attendancePercentage"
                + " FROM temp_jadwal_karyawan tempJadwalKaryawan"
                + " LEFT JOIN temp_process_read_finger tempProcessReadFinger ON tempProcessReadFinger.emp_data_id = tempJadwalKaryawan.emp_id"
                + " AND tempProcessReadFinger.schedule_date = tempJadwalKaryawan.tanggal_waktu_kerja"
                + " INNER JOIN emp_data empData ON tempJadwalKaryawan.emp_id = empData.id"
                + " INNER JOIN jabatan jabatan ON empData.jabatan_id = jabatan.id"
                + " INNER JOIN department department ON jabatan.departement_id = department.id"
                + " INNER JOIN wt_working_hour wtWorkingHour ON tempJadwalKaryawan.working_our_id = wtWorkingHour.id"
                + " WHERE tempJadwalKaryawan.tanggal_waktu_kerja BETWEEN '" + dateFormat.format(dateFrom) + "' AND '" + dateFormat.format(dateUntill) + "' "
                + " AND wtWorkingHour.code <> 'OFF'"
                + " AND department.id in("
                + rangeDepId
                + " )"
                + " AND empData.status <> '" + HRMConstant.EMP_TERMINATION + "' "
                + " GROUP BY WEEK(tempJadwalKaryawan.tanggal_waktu_kerja) , jabatan.departement_id ; ");

        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(DepAttendanceRealizationViewModel.class))
                .list();
    }

    @Override
    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.add(Restrictions.le("joinDate", date));

        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
//        criteria.createAlias("taxFree", "taxFree", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        doSearchByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParam(EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParam(EmpDataSearchParameter dataSearchParameter, Criteria criteria) {
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("wtGroupWorking", "wtGroupWorking", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);

        if (dataSearchParameter.getJabatanKode() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.code", dataSearchParameter.getJabatanKode(), MatchMode.START));
        }
        if (dataSearchParameter.getJabatanName() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }
        if (dataSearchParameter.getWorkingGroupName() != null) {
            criteria.add(Restrictions.like("wtGroupWorking.name", dataSearchParameter.getWorkingGroupName(), MatchMode.ANYWHERE));
        }
        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.START));
        }
        if (dataSearchParameter.getName() != null) {
            criteria.add(Restrictions.ilike("bioData.combineName", dataSearchParameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
    }

    @Override
    public List<EmpData> getAllDataByParamForOnlyEmployee(Long companyId, EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamOnlyEmployee(companyId, searchParameter, criteria);
        criteria.addOrder(order);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
//        criteria.createAlias("taxFree", "taxFree", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
//        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    private void doSearchByParamOnlyEmployee(Long companyId, EmpDataSearchParameter dataSearchParameter, Criteria criteria) {
        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        if (dataSearchParameter.getJabatanKode() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.code", dataSearchParameter.getJabatanKode(), MatchMode.START));
        }

        if (dataSearchParameter.getJabatanName() != null) {
            criteria.add(Restrictions.like("jabatanByJabatanId.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.START));
        }
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (dataSearchParameter.getName() != null) {
            criteria.add(Restrictions.ilike("bioData.combineName", dataSearchParameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
    }

    @Override
    public Long getTotalByParamForOnlyEmployee(Long companyId, EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamOnlyEmployee(companyId, searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataByParamForOnlyEmployeeNotIncludeCompany(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamOnlyEmployeeNotIncludeCompany(searchParameter, criteria);
        criteria.addOrder(order);
//        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);

        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("taxFree", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
//        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalByParamForOnlyEmployeeNotIncludeCompany(EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchByParamOnlyEmployeeNotIncludeCompany(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchByParamOnlyEmployeeNotIncludeCompany(EmpDataSearchParameter dataSearchParameter, Criteria criteria) {
//        criteria = this.addJoinRelationsOfCompanyId(criteria, companyId);
//        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
//        if (dataSearchParameter.getJabatanKode() != null) {
//            criteria.add(Restrictions.like("jabatanByJabatanId.code", dataSearchParameter.getJabatanKode(), MatchMode.START));
//        }
//
//        if (dataSearchParameter.getJabatanName() != null) {
//            criteria.add(Restrictions.like("jabatanByJabatanId.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
//        }
        criteria.createAlias("taxFree", "taxFree", JoinType.LEFT_OUTER_JOIN);
        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.START));
        }
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (dataSearchParameter.getName() != null) {
            criteria.add(Restrictions.ilike("bioData.combineName", dataSearchParameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
    }

    @Override
    public Long getTotalKaryawanByJabatanIdWithJoinDateBeforeOrEqualDate(Long jabatanId, Date joinDateLimit) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.add(Restrictions.le("joinDate", joinDateLimit));

        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllByJabatanAndCompanyAndStatus(long jabataId, String status) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bi", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("jb.id", jabataId));
        criteria.add(Restrictions.not(Restrictions.eq("status", status)));
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.addOrder(Order.asc("bi.firstName"));
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllEmployeeForRecruitAggrementNotice(RecruitAgreementNoticeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmployeeForRecruitAggrementNotice(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalAllEmployeeForRecruitAggrementNotice(RecruitAgreementNoticeSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmployeeForRecruitAggrementNotice(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    public void doSearchEmployeeForRecruitAggrementNotice(RecruitAgreementNoticeSearchParameter searchParameter, Criteria criteria) {
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);

        if (searchParameter.getEmpDataName() != null) {
            criteria.add(Restrictions.ilike("bioData.combineName", searchParameter.getEmpDataName().toLowerCase(), MatchMode.ANYWHERE));
        }
    }

    public List<RecruitAgreementNoticeViewModel> getAllEmployeeForRecruitAggrementNoticeWithNativeQuery(RecruitAgreementNoticeSearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        final StringBuilder query = new StringBuilder("SELECT emp.id as employeeId, bio.id as bioDataId, bio.first_name as firstName, bio.last_name as lastName, jabatan.name as jabatanName, pangkat.pangkat_name as pangkatName, bio.date_of_birth as birthOfDate,");
        query.append(" (SELECT pangkat2.pangkat_name FROM pangkat pangkat2");
        query.append(" WHERE pangkat2.level < pangkat.level ORDER BY LEVEL DESC LIMIT 1) as jabatanDituju,");
        query.append(" pangkat.level,");
        query.append(" (SELECT el.name FROM education_level el ");
        query.append(" INNER JOIN bio_education_history beh ON el.id = beh.pendidikan_level_id");
        query.append(" INNER JOIN bio_data bio2 ON bio2.id = beh.biodata_id");
        query.append(" WHERE beh.biodata_id = bio.id ORDER by el.name DESC LIMIT 1 ) as lastEducationLevel");
        query.append(" FROM bio_data bio ");
        query.append(" LEFT JOIN emp_data emp ON bio.id = emp.bio_data_id");
        /*			query.append(" INNER JOIN bio_education_history beh ON beh.biodata_id = bio.id");
			query.append(" INNER JOIN education_level el ON el.id = beh.pendidikan_level_id");*/
        query.append(" LEFT JOIN jabatan jabatan ON emp.jabatan_id = jabatan.id");
        query.append(" LEFT JOIN golongan_jabatan goljab ON goljab.id = emp.gol_jab_id");
        query.append(" LEFT JOIN pangkat pangkat ON goljab.pangkat_id = pangkat.id");
        //query for action searching
        doSearchEmployeeForRecruitAgreementNoticeWithNativeQuery(searchParameter, query);
        //order query base on orderable
        query.append(" ORDER BY ");

        if (StringUtils.equals("firstName", orderable.getPropertyName())) {
            query.append("firstName ");
        } else if (StringUtils.equals("jabatanName", orderable.getPropertyName())) {
            query.append("jabatanName ");
        } else if (StringUtils.equals("jabatanDituju", orderable.getPropertyName())) {
            query.append("jabatanDituju ");
        } else if (StringUtils.equals("lastEducationLevel", orderable.getPropertyName())) {
            query.append("lastEducationLevel ");
        } else if (StringUtils.equals("birthOfDate", orderable.getPropertyName())) {
            query.append("birthOfDate ");
        }

        query.append(orderable.isAscending() ? " ASC " : " DESC ");

        //Limit query based on paging parameter
        query.append(" LIMIT ").append(firstResult).append(",").append(maxResults).append(" ");
        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(RecruitAgreementNoticeViewModel.class))
                .list();
    }

    public Long getTotalAllEmployeeForRecruitAggrementNoticeWithNativeQuery(RecruitAgreementNoticeSearchParameter searchParameter) {

        final StringBuilder query = new StringBuilder("SELECT count(*) FROM (SELECT emp.id as employeeId, bio.id as bioDataId, bio.first_name as firstName, bio.last_name as lastName, jabatan.name as jabatanName, pangkat.pangkat_name as pangkatName, ");
        query.append(" (SELECT pangkat2.pangkat_name FROM pangkat pangkat2");
        query.append(" WHERE pangkat2.level < pangkat.level ORDER BY LEVEL DESC LIMIT 1) as jabatanDituju,");
        query.append(" pangkat.level,");
        query.append(" (SELECT pangkat2.level FROM pangkat pangkat2");
        query.append(" WHERE pangkat2.level < pangkat.level ORDER BY LEVEL DESC LIMIT 1) as levelDituju,");
        query.append(" (SELECT el.name FROM education_level el ");
        query.append(" INNER JOIN bio_education_history beh ON el.id = beh.pendidikan_level_id");
        query.append(" INNER JOIN bio_data bio2 ON bio2.id = beh.biodata_id");
        query.append(" WHERE beh.biodata_id = bio.id ORDER by el.name DESC LIMIT 1 ) as lastEducationLevel");
        query.append(" FROM bio_data bio ");
        query.append(" LEFT JOIN emp_data emp ON bio.id = emp.bio_data_id");
        query.append(" LEFT JOIN jabatan jabatan ON emp.jabatan_id = jabatan.id");
        query.append(" LEFT JOIN golongan_jabatan goljab ON goljab.id = emp.gol_jab_id");
        query.append(" LEFT JOIN pangkat pangkat ON goljab.pangkat_id = pangkat.id");
        //query for action searching
        doSearchEmployeeForRecruitAgreementNoticeWithNativeQuery(searchParameter, query);
        query.append(" ) as totalRows");
        return Long.valueOf(getCurrentSession().createSQLQuery(query.toString()).uniqueResult().toString());
    }

    public void doSearchEmployeeForRecruitAgreementNoticeWithNativeQuery(RecruitAgreementNoticeSearchParameter searchParameter, StringBuilder query) {
        /*StringBuilder query = new StringBuilder();*/
        //query for action searching
        if (searchParameter.getEmpDataName() != null) {
            query.append(" WHERE bio.first_name like '%" + searchParameter.getEmpDataName() + "%'");
        } else if (searchParameter.getJabatan() != null) {
            query.append(" WHERE jabatan.name like '%" + searchParameter.getJabatan() + "%'");
        }
    }

    @Override
    public List<EmpData> getListEmpDataWhichNotExistOnFingerEmpMatch() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());

        DetachedCriteria fingerMatchEmpCriteria = DetachedCriteria.forClass(FingerMatchEmp.class)
                .createAlias("empData", "empData", JoinType.INNER_JOIN)
                .setProjection(Projections.property("empData.id"));

        String[] propertyEmpDataId = {"id"};
        criteria.add(Subqueries.propertiesNotIn(propertyEmpDataId, fingerMatchEmpCriteria))
                .setFetchMode("bioData", FetchMode.JOIN)
                .setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        return criteria.list();
    }

}
