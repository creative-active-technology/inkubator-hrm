/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.entity.LeaveDistribution;
import com.inkubator.hrm.util.HrmUserInfoUtil;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeCandidateViewModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;
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
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
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
    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private void doSearchEmpDataByParam(EmpDataSearchParameter dataSearchParameter, Criteria criteria) {

        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
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
        criteria.setFetchMode("jabatanByJabatanId.departement", FetchMode.JOIN);
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
    public List<EmpData> getAllDataByNameOrNik(String param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));

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
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
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
        return criteria.list();
    }

    @Override
    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model) {
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
//        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        if (model.getOverTimeId() != 0 || model.getOverTimeId() != null) {

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
        }
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

        return criteria;
    }

    @Override
    public List<EmpData> getEmployeeBySearchEmployeePermit(PermitDistributionModel model) {
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
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
        if (model.getPermitId() != 0 || model.getPermitId() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("lv.empData"));
            disjunction.add(Restrictions.not(Restrictions.eq("lv.permitClassification.id", model.getPermitId())));
            criteria.add(disjunction);
        }
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
        
        if(StringUtils.isNotEmpty(nikOrName)) {
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
    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate) {    	
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.le("joinDate", payrollCalculationDate));
        
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
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

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

        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
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
            criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(biodata1_.date_of_birth)), '%Y')+0 in (" + ageList.toString() + ")", listAge.toArray(), typeAge));
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

        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
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
            criteria.add(Restrictions.sqlRestriction("DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(biodata1_.date_of_birth)), '%Y')+0 in (" + ageList.toString() + ")", listAge.toArray(), typeAge));
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
    public Long getTotalKaryawanByJabatanId(Long jabatanId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));

        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getByParam(String nikOrNameSearchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(nikOrNameSearchParameter, criteria);
        criteria.addOrder(order);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
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
    public List<SearchEmployeeCandidateViewModel> getAllDataEmpCandidateByParamWithDetail(List<Long> listJabatanId, List<Long> listReligionId, List<Integer> listAge,
            List<Integer> listJoinDate, Double gpa, Long educationLevelId, String gender, int firstResult, int maxResults, Order order) {

        StringBuffer selectQuery = new StringBuffer(
                "SELECT empData.id AS empDataId, empData.nik AS nik, "
                + " bioData.firstName AS firstName, bioData.lastName AS lastName, "
                + " jabatan.id AS idJabatan, jabatan.name AS jabatanName, "
                + " religion.id AS idReligion, religion.name AS religionName "
                + " FROM EmpData as empData "
                + " INNER JOIN empData.jabatanByJabatanId AS jabatan "
                + " INNER JOIN jabatan.department AS department "
                + " INNER JOIN department.company AS company "
                + " LEFT OUTER JOIN empData.bioData AS  bioData "
                + " INNER JOIN bioData.religion AS  religion "
                + " WHERE company.id = :companyId  "
                + " AND :educationLevelId in ( "
                + " SELECT edu.id FROM BioEducationHistory bioEdu INNER JOIN bioEdu.educationLevel edu "
                + " INNER JOIN bioEdu.biodata bioDataInner "
                + " WHERE bioDataInner.id = bioData.id ) "
                + " AND  (SELECT bioEduGpa.score FROM BioEducationHistory bioEduGpa INNER JOIN bioEduGpa.educationLevel eduGpa "
                + " INNER JOIN bioEduGpa.biodata bioDataInnerGpa  "
                + " WHERE bioDataInnerGpa.id = bioData.id AND eduGpa.id = :educationLevelId ) > :gpa  ");

        //Filter by gender
        if (!StringUtils.equals("Any", gender)) {
            if (StringUtils.equals("Male", gender)) {
                selectQuery.append("AND bioData.gender = 1 ");
            } else if (StringUtils.equals("Female", gender)) {
                selectQuery.append("AND bioData.gender = 0 ");
            }
        }

        //filter by ages range
        if (!listAge.isEmpty()) {
            selectQuery.append("AND umur(bioData.dateOfBirth , NOW()) in (:listAge) ");
        }

        //filter by jabatan range
        if (!listJabatanId.isEmpty()) {
            selectQuery.append("AND jabatan.id IN (:listJabatanId) ");
        }

        //filter by religion range
        if (!listReligionId.isEmpty()) {
            selectQuery.append("AND religion.id IN (:listReligionId) ");
        }

        //filter by joinDate (convert to total working in years) range
        if (listJoinDate.get(0) != 0) {
            selectQuery.append(" AND DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(empData.joinDate)), '%Y')+0 in ( :listJoinDate ) ");

        }

        Query hbm = getCurrentSession().createQuery(selectQuery.toString())
                .setParameter("gpa", gpa)
                .setParameter("educationLevelId", educationLevelId)
                .setParameter("companyId", HrmUserInfoUtil.getCompanyId());

        if (!listJabatanId.isEmpty()) {
            hbm.setParameterList("listJabatanId", listJabatanId);
        }
        if (!listReligionId.isEmpty()) {
            hbm.setParameterList("listReligionId", listReligionId);
        }
        if (!listAge.isEmpty()) {
            hbm.setParameterList("listAge", listAge);
        }

        if (!listJoinDate.isEmpty() && listJoinDate.get(0) != 0) {
            hbm.setParameterList("listJoinDate", listJoinDate);
        }

        return hbm.setMaxResults(maxResults)
                .setFirstResult(firstResult)
                .setResultTransformer(Transformers.aliasToBean(SearchEmployeeCandidateViewModel.class))
                .list();

    }

    @Override
    public Long getTotalEmpCandidateByParamWithDetail(List<Long> listJabatanId, List<Long> listReligionId, List<Integer> listAge, List<Integer> listJoinDate, Double gpa, Long educationLevelId, String gender) {

        StringBuffer selectQuery = new StringBuffer(
                " SELECT COUNT(*) "
                + " FROM EmpData as empData "
                + " INNER JOIN empData.jabatanByJabatanId as jabatan  "
                + " INNER JOIN jabatan.department AS department "
                + " INNER JOIN department.company AS company "
                + " LEFT OUTER JOIN empData.bioData AS  bioData "
                + " INNER JOIN bioData.religion AS  religion "
                + " WHERE company.id = :companyId  "
                + " AND :educationLevelId in ( "
                + " SELECT edu.id FROM BioEducationHistory bioEdu INNER JOIN bioEdu.educationLevel edu "
                + " INNER JOIN bioEdu.biodata bioDataInner "
                + " WHERE bioDataInner.id = bioData.id ) "
                + " AND  (SELECT bioEduGpa.score FROM BioEducationHistory bioEduGpa INNER JOIN bioEduGpa.educationLevel eduGpa "
                + " INNER JOIN bioEduGpa.biodata bioDataInnerGpa  "
                + " WHERE bioDataInnerGpa.id = bioData.id AND eduGpa.id = :educationLevelId ) > :gpa  ");

        //Filter by gender
        if (!StringUtils.equals("Any", gender)) {
            if (StringUtils.equals("Male", gender)) {
                selectQuery.append("AND bioData.gender = 1 ");
            } else if (StringUtils.equals("Female", gender)) {
                selectQuery.append("AND bioData.gender = 0 ");
            }
        }

        //filter by ages range
        if (!listAge.isEmpty()) {
            selectQuery.append("AND umur(bioData.dateOfBirth , NOW()) in (:listAge) ");
        }

        //filter by jabatan range
        if (!listJabatanId.isEmpty()) {
            selectQuery.append("AND jabatan.id IN (:listJabatanId) ");
        }

        //filter by religion range
        if (!listReligionId.isEmpty()) {
            selectQuery.append("AND religion.id IN (:listReligionId) ");
        }

        //filter by joinDate (convert to total working in years) range
        if (listJoinDate.get(0) != 0) {
            selectQuery.append(" AND DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(empData.joinDate)), '%Y')+0 in ( :listJoinDate ) ");

        }

        Query hbm = getCurrentSession().createQuery(selectQuery.toString())
                .setParameter("gpa", gpa)
                .setParameter("educationLevelId", educationLevelId)
                .setParameter("companyId", HrmUserInfoUtil.getCompanyId());

        if (!listJabatanId.isEmpty()) {
            hbm.setParameterList("listJabatanId", listJabatanId);
        }
        if (!listReligionId.isEmpty()) {
            hbm.setParameterList("listReligionId", listReligionId);
        }
        if (!listAge.isEmpty()) {
            hbm.setParameterList("listAge", listAge);
        }

        if (!listJoinDate.isEmpty() && listJoinDate.get(0) != 0) {
            hbm.setParameterList("listJoinDate", listJoinDate);
        }

        return Long.valueOf(hbm.uniqueResult().toString());
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
		return !criteria.list().isEmpty();
	}


    @Override
    public List<EmpData> getAllDataNotTerminatePaging(TempAttendanceRealizationSearchParameter parameter, int firstResult, int maxResult, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        if (parameter.getNik() != null) {
            criteria.add(Restrictions.like("nik", parameter.getNik(), MatchMode.ANYWHERE));
        }
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        if (parameter.getName() != null) {
//            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            criteria.add(Restrictions.ilike("bio.combineName", parameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }

        System.out.println("nilai ordernya " + order);
        String sorting = "bio." + order;
//        if (order==null) {
//            criteria.addOrder(order);
//        } else {
//            if (order.isAscending()) {
//                System.out.println(" asc");
//                criteria.addOrder(Order.asc(sorting));
//            } else {
//                   System.out.println(" desc");
//                criteria.addOrder(Order.desc(sorting));
//            }
//        }

        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResult);
        return criteria.list();
    }

    @Override
    public Long getTotalNotTerminatePaging(TempAttendanceRealizationSearchParameter parameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        /**
         * automatically get relations of jabatanByJabatanId, department,
         * company don't create alias for that entity, or will get error :
         * duplicate association path
         */
        criteria = this.addJoinRelationsOfCompanyId(criteria, HrmUserInfoUtil.getCompanyId());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        if (parameter.getNik() != null) {
            criteria.add(Restrictions.like("nik", parameter.getNik(), MatchMode.ANYWHERE));
        }

        if (parameter.getName() != null) {
            criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
            criteria.add(Restrictions.ilike("bio.combineName", parameter.getName().toLowerCase(), MatchMode.ANYWHERE));
        }
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }
}
