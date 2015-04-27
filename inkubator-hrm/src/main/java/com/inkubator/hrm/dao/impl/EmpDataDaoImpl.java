/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao.impl;

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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.inkubator.datacore.dao.impl.IDAOImpl;
import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.dao.EmpDataDao;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.hrm.web.model.SearchEmployeeModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;
import java.util.ArrayList;
import java.util.Arrays;
import org.hibernate.transform.Transformers;
import static org.springframework.data.jpa.repository.support.PersistenceProvider.HIBERNATE;

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
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeBetween(Date startDate, Date endDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", startDate));
        criteria.add(Restrictions.lt("bioData.dateOfBirth", endDate));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeLessThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.lt("bioData.dateOfBirth", date));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByAgeMoreThan(Date date) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.add(Restrictions.gt("bioData.dateOfBirth", date));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByDepartmentId(Long departmentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("jabatan.department.id", departmentId));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        doSearchEmpDataByParam(searchParameter, criteria);
        criteria.addOrder(order);
        criteria.setFetchMode("bioData", FetchMode.JOIN);
        criteria.setFetchMode("bioData.city", FetchMode.JOIN);
        criteria.setFetchMode("bioData.maritalStatus", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan", FetchMode.JOIN);
        criteria.setFetchMode("golonganJabatan.pangkat", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.department", FetchMode.JOIN);
        criteria.setFetchMode("jabatanByJabatanId.unitKerja", FetchMode.JOIN);
        criteria.setFetchMode("wtGroupWorking", FetchMode.JOIN);
        criteria.setFetchMode("taxFree", FetchMode.JOIN);
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
            criteria.add(Restrictions.like("jb.code", dataSearchParameter.getJabatanKode(), MatchMode.START));
        }

        if (dataSearchParameter.getJabatanName() != null) {
            criteria.createAlias("jabatanByJabatanId", "jb", JoinType.INNER_JOIN);
            criteria.add(Restrictions.like("jb.name", dataSearchParameter.getJabatanName(), MatchMode.ANYWHERE));
        }

        if (dataSearchParameter.getNIK() != null) {
            criteria.add(Restrictions.like("nik", dataSearchParameter.getNIK(), MatchMode.START));
        }
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        if (dataSearchParameter.getName() != null) {

            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bio.firstName", dataSearchParameter.getName(), MatchMode.START));
            disjunction.add(Restrictions.like("bio.lastName", dataSearchParameter.getName(), MatchMode.START));
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
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
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
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        if (param != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.like("bioData.firstName", param, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("bioData.lastName", param, MatchMode.ANYWHERE));
            disjunction.add(Restrictions.like("nik", param, MatchMode.ANYWHERE));
            criteria.add(disjunction);
        }
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return criteria;
    }

    @Override
    public List<EmpData> getAllDataByJabatanId(Long jabatanId, Order order) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.eq("jabatanByJabatanId.id", jabatanId));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        criteria.addOrder(order);

        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataByGolJabatanIdAndDepartmentId(Long golJabatanId, Long departmentId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.add(Restrictions.eq("golonganJabatan.id", golJabatanId));
        criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", departmentId));
        criteria.add(Restrictions.neOrIsNotNull("status", HRMConstant.EMP_TERMINATION));
        return criteria.list();
    }

    @Override
    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("wtGroupWorking", "wg", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan.department", "dept", JoinType.INNER_JOIN);
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
                criteria.add(Restrictions.eq("dept.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("dept.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
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
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("leaveDistributions", "lv", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan.department", "dept", JoinType.INNER_JOIN);
        criteria.createAlias("employeeType", "empType", JoinType.INNER_JOIN);
        criteria.createAlias("bioData", "bio", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        //ambil yg working groupnya bukan yg dipilih, dan belum punya working group
        if (model.getLeaveSchemeId() != 0 || model.getLeaveSchemeId() != null) {
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(Restrictions.isNull("lv.empData"));
            disjunction.add(Restrictions.not(Restrictions.eq("lv.leave.id", model.getLeaveSchemeId())));
            criteria.add(disjunction);
        }
        //balance
//        if (model.getStartBalance() != 0.0){
//            criteria.add(Restrictions.eq("lv.balance", model.getStartBalance()));
//        }
        //departermen equal or like
        if (model.getDepartmentLikeOrEqual() != 3) {
            if (Objects.equals(model.getDepartmentLikeOrEqual(), HRMConstant.DEPARTMENT_EQUAL)) {
                criteria.add(Restrictions.eq("dept.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("dept.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
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
        criteria.createAlias("overTimeDistributions", "ot", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("ot.wtOverTime", "wt", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan.department", "dept", JoinType.INNER_JOIN);
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
                criteria.add(Restrictions.eq("dept.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("dept.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
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
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        doSearchReportEmpWorkingGroupByParam(param, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        doSearchReportEmpWorkingGroupByParam(param, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, Criteria criteria) {
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
        criteria.createAlias("permitDistributions", "lv", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan.department", "dept", JoinType.INNER_JOIN);
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
                criteria.add(Restrictions.eq("dept.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("dept.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
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
    public List<EmpData> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
//        criteria.createAlias("bioData.bioFamilyRelationships", "familyRelationShip", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        criteria.setFetchMode("bioData.bioFamilyRelationships", FetchMode.JOIN);
//        criteria.setFetchMode("jabatanByJabatanId.jabatanSpesifikasis.specificationAbility", FetchMode.JOIN);

        doSearchReportOfEmployeesFamilyByParam(searchParameter, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        doSearchReportOfEmployeesFamilyByParam(searchParameter, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter param, Criteria criteria) {
        if (param.getDepartmentId() != null && param.getDepartmentId() != 0) {
            criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", param.getDepartmentId()));
        }

        return criteria;
    }

    @Override
    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        doSearchReportEmpDepartmentJabatanByParam(param, criteria);
        criteria.addOrder(orderable);
        criteria.setFirstResult(firstResult);
        criteria.setMaxResults(maxResults);
        return criteria.list();
    }

    @Override
    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "goljab", JoinType.INNER_JOIN);
        doSearchReportEmpDepartmentJabatanByParam(param, criteria);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    private Criteria doSearchReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, Criteria criteria) {

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
        criteria.createAlias("jabatanByJabatanId", "jabatan", JoinType.INNER_JOIN);
        criteria.createAlias("jabatan.department", "dept", JoinType.INNER_JOIN);
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
                criteria.add(Restrictions.eq("dept.departmentName", model.getDepartmentName()));
            } else {
                criteria.add(Restrictions.like("dept.departmentName", model.getDepartmentName(), MatchMode.ANYWHERE));
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
    public List<EmpData> getAllDataNotTerminate() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        return criteria.list();
    }

    @Override
    public Long getTotalEmpDataNotTerminate() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public Long getTotalByTaxFreeIsNull() {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.isNull("taxFree"));
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.not(Restrictions.eq("status", HRMConstant.EMP_TERMINATION)));
        criteria.add(Restrictions.le("joinDate", payrollCalculationDate));
        return criteria.list();
    }

    @Override
    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter param, int firstResult, int maxResults, Order orderable) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
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
    public List<EmpData> getAllDataByAbsisAndOrdinateAndGoljab(String absis, String ordinate, long golJabId) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        return criteria.list();

    }

    @Override
    public BioDataModel getEmpNameWithNearestBirthDate() {
        final StringBuilder query = new StringBuilder("SELECT a.first_name AS firstName, a.last_name AS lastName, a.date_of_birth AS dateOfBirth,");
        query.append("DATE_ADD(a.date_of_birth, INTERVAL IF (DAYOFYEAR(a.date_of_birth) >= DAYOFYEAR(CURDATE()), YEAR(CURDATE())-YEAR(a.date_of_birth), YEAR(CURDATE())-YEAR(a.date_of_birth)+1) YEAR) AS nextBirthday ");
        query.append("FROM `bio_data` a INNER JOIN emp_data b ");
        query.append("ON a.id = b.bio_data_id ");
        query.append("WHERE a.date_of_birth  IS NOT NULL HAVING nextBirthday ");
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
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("bioData.educationHistories", "educationHistories", JoinType.INNER_JOIN);
        criteria.createAlias("educationHistories.educationLevel", "educationLevel", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
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
        if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            query.append(" WHERE department.id IN :idDept AND educationLevel.id IN :idEdu");
        } else if (departementId.isEmpty() != Boolean.TRUE && educationId.isEmpty() == Boolean.TRUE) {
            query.append(" WHERE department.id IN :idDept");
        } else if (departementId.isEmpty() == Boolean.TRUE && educationId.isEmpty() != Boolean.TRUE) {
            query.append(" WHERE educationLevel.id IN :idEdu");
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
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        if (!listDepartmentId.isEmpty()) {
            criteria.add(Restrictions.in("department.id", listDepartmentId));
        }
        if (!listEmployeeTypeId.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", listEmployeeTypeId));
        }
    }

    @Override
    public List<ReportEmpPensionPreparationModel> getReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges, int firstResult, int maxResults, Order order) {

        final StringBuilder query = new StringBuilder("SELECT e.NIK AS nik, b.first_name AS firstName, b.last_name AS lastName, "
                + " e.join_date AS tglMulaiBekerja, g.code AS golJabatan, b.date_of_birth AS tglLahir, "
                + " umur(b.date_of_birth , NOW()) AS usiaKaryawan, "
                + " j.name AS jabatan, d.department_name AS departmentName, d.id AS departmentId, "
                + " s.id AS empTypeId , s.name AS statusKaryawan FROM emp_data e "
                + " INNER JOIN golongan_jabatan g ON e.gol_jab_id = g.id  "
                + " INNER JOIN bio_data b ON e.bio_data_id = b.id  "
                + " INNER JOIN jabatan j ON e.jabatan_id = j.id "
                + " INNER JOIN department d ON j.departement_id = d.id "
                + " INNER JOIN employee_type s ON e.emp_type_id = s.id ");

        //Flag Untuk penanda apakah ada filter atau tidak
        boolean isFiltered = !listDepartmentId.isEmpty() || !listEmpTypeId.isEmpty() || !listEmpAges.isEmpty();

        //Flag untuk penanda jika filter lebih dari satu
        boolean multipleFilter = Boolean.FALSE;

        if (isFiltered) {
            query.append(" WHERE ");
        }

        if (!listDepartmentId.isEmpty()) {
            query.append(" j.departement_id IN( ");

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
            multipleFilter = Boolean.TRUE;
        }

        if (!listEmpTypeId.isEmpty()) {
            if (multipleFilter) {
                query.append("AND e.emp_type_id IN( ");
            } else {
                query.append(" e.emp_type_id IN( ");
                multipleFilter = Boolean.TRUE;
            }

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
            if (multipleFilter) {
                query.append(" AND umur(b.date_of_birth , NOW()) IN( ");
            } else {
                query.append("umur(b.date_of_birth , NOW()) IN( ");
            }

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

        return getCurrentSession().createSQLQuery(query.toString())
                .setResultTransformer(Transformers.aliasToBean(ReportEmpPensionPreparationModel.class))
                .list();

    }

    @Override
    public Long getTotalReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges) {

        final StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM (SELECT e.NIK AS nik, b.first_name AS firstName, b.last_name AS lastName, "
                + " e.join_date AS tglMulaiBekerja, g.code AS golJabatan, b.date_of_birth AS tglLahir, "
                + " umur(b.date_of_birth , NOW()) AS usiaKaryawan, "
                + " j.name AS jabatan, d.department_name AS departmentName, d.id AS departmentId, "
                + " s.id AS empTypeId , s.name AS statusKaryawan FROM emp_data e "
                + " INNER JOIN golongan_jabatan g ON e.gol_jab_id = g.id  "
                + " INNER JOIN bio_data b ON e.bio_data_id = b.id  "
                + " INNER JOIN jabatan j ON e.jabatan_id = j.id "
                + " INNER JOIN department d ON j.departement_id = d.id "
                + " INNER JOIN employee_type s ON e.emp_type_id = s.id ");

        //Flag Untuk penanda apakah ada filter atau tidak
        boolean isFiltered = !listDepartmentId.isEmpty() || !listEmpTypeId.isEmpty() || !listEmpAges.isEmpty();

        //Flag untuk penanda jika filter lebih dari satu
        boolean multipleFilter = Boolean.FALSE;

        if (isFiltered) {
            query.append(" WHERE ");
        }

        if (!listDepartmentId.isEmpty()) {
            query.append(" j.departement_id IN( ");

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
            multipleFilter = Boolean.TRUE;
        }

        if (!listEmpTypeId.isEmpty()) {
            if (multipleFilter) {
                query.append("AND e.emp_type_id IN( ");
            } else {
                query.append(" e.emp_type_id IN( ");
                multipleFilter = Boolean.TRUE;
            }

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
            if (multipleFilter) {
                query.append(" AND umur(b.date_of_birth , NOW()) IN( ");
            } else {
                query.append("umur(b.date_of_birth , NOW()) IN( ");
            }

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

    public void doCreateAliasByDepartmentAndEmployeeTypeAndAges(List<Long> listDepartmentId, List<Long> listEmployeeTypeId, List<Integer> listEmpAges, Criteria criteria) {
        criteria.createAlias("bioData", "bioData", JoinType.INNER_JOIN);
        criteria.createAlias("golonganJabatan", "golonganJabatan", JoinType.INNER_JOIN);
        criteria.createAlias("employeeType", "employeeType", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.INNER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.INNER_JOIN);
        if (!listDepartmentId.isEmpty()) {
            criteria.add(Restrictions.in("department.id", listDepartmentId));
        }
        if (!listEmployeeTypeId.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", listEmployeeTypeId));
        }

        if (!listEmpAges.isEmpty()) {
            criteria.add(Restrictions.in("employeeType.id", listEmployeeTypeId));
        }
    }

    @Override
    public List<EmpData> getAllDataByDepartmentAndReligionAndGolJabAndEmpType(List<Long> departmentIds, List<Long> religionIds, List<Long> golJabIds, List<Long> empTypeIds) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
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
        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.LEFT_OUTER_JOIN);
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
    public List<String> getAllNikBetween(String from, String until) {
        Criteria criteria = getCurrentSession().createCriteria(getEntityClass());
        criteria.add(Restrictions.ge("nik", from));
        criteria.add(Restrictions.le("nik", until));
        criteria.setProjection(Projections.property("nik"));
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
        criteria.createAlias("bioData", "bioData", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("jabatanByJabatanId.department", "department", JoinType.LEFT_OUTER_JOIN);
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
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.LEFT_OUTER_JOIN);
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
        criteria.createAlias("jabatanByJabatanId", "jabatanByJabatanId", JoinType.LEFT_OUTER_JOIN);
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
    			"SELECT empData " +
    			"FROM EmpData as empData " +
    			"INNER JOIN empData.jabatanByJabatanId as jabatan " +
    			"INNER JOIN jabatan.unitKerja as unitKerja " +
    			"INNER JOIN jabatan.department as department " +
    			"INNER JOIN department.company as company " +
    			"INNER JOIN empData.employeeType as employeeType " +
    			"INNER JOIN empData.golonganJabatan as golonganJabatan " +
    			"WHERE company.id = :companyId " +
    			"AND status != :status ");
		
		if(!empTypes.isEmpty()){
			selectQuery.append("AND employeeType.id IN (:employeeTypes) ");
		}
		if(!golJabs.isEmpty()){
			selectQuery.append("AND golonganJabatan.id IN (:golonganJabatans) ");
		}
		if(!unitKerjas.isEmpty()){
			selectQuery.append("AND unitKerja.id IN (:unitKerjas) ");
		}
		
    	Query hbm = getCurrentSession().createQuery(selectQuery.toString())
    			.setParameter("companyId", companyId)
    			.setParameter("status", HRMConstant.EMP_TERMINATION);
    	if(!empTypes.isEmpty()){
    		hbm.setParameterList("employeeTypes", empTypes);
    	}
    	if(!golJabs.isEmpty()){
    		hbm.setParameterList("golonganJabatans", golJabs);
    	}
    	if(!unitKerjas.isEmpty()){
    		hbm.setParameterList("unitKerjas", unitKerjas);
    	}
		
    	return hbm.list();
	}

}
