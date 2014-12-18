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
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.HrmUser;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import com.inkubator.hrm.web.search.SalaryConfirmationParameter;

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
            criteria.add(Restrictions.like("jb.name", dataSearchParameter.getJabatanName(), MatchMode.START));
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
            System.out.println(" nilai id " + model.getOverTimeId());
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

        System.out.println("goljabs " + param.getGolonganJabatanId());
        System.out.println("departement " + param.getDepartmentId());

        if (param.getDepartmentId() != null && param.getDepartmentId() != 0) {
            criteria.add(Restrictions.eq("jabatanByJabatanId.department.id", param.getDepartmentId()));
        }

        if (param.getGolonganJabatanId() != null) {
            System.out.println("goljabs " + param.getGolonganJabatanId().get(0));
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
        criteria.add(Restrictions.lt("joinDate", payrollCalculationDate));
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

}
