/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.model.BioDataModel;
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

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpDataDao extends IDAO<EmpData> {

    public Long getTotalByGender(Integer gender);

    public Long getTotalByAgeBetween(Date startDate, Date endDate);

    public Long getTotalByAgeLessThan(Date date);

    public Long getTotalByAgeMoreThan(Date date);

    public Long getTotalByDepartmentId(Long departmentId);

    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter);

    public EmpData getByEmpIdWithDetail(long id);

    public EmpData getByBioDataWithDepartment(long id);

    public Long getTotalByNikandNotId(String nik, Long id);

    public Long getTotalByNIK(String nik);

    public List<EmpData> getAllDataWithRelation();

    public EmpData getEntityByNik(String nik);

    public List<EmpData> getAllDataByNameOrNik(String param);

    public EmpData getByIdWithDetail(long id);

    public List<EmpData> getAllDataNotExistInUserByParam(String param, int firstResult, int maxResults, Order order);

    public Long getTotalNotExistInUserByParam(String param);

    public List<EmpData> getAllDataByJabatanId(Long jabatanId, Order order);

    public List<EmpData> getAllDataByGolJabatanIdAndDepartmentId(Long jabatanId, Long departmentId);

    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model);

    public List<EmpData> getEmployeeBySearchEmployeeLeave(DistributionLeaveSchemeModel model);

    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model);

    public List<EmpData> getEmpDataByListId(List<Long> data);

    public List<EmpData> getAllDataReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, int firstResult, int maxResults, Order orderable);

    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param);

    public List<EmpData> getEmployeeBySearchEmployeePermit(PermitDistributionModel model);

    public List<EmpData> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable);

    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter);

    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable);

    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param);

    public List<EmpData> getEmployeeBySearchEmployeeFingerException(WtFingerExceptionModel model);

    public EmpData getEmpDataWithBiodata(Long id);

    public List<EmpData> getAllDataNotTerminate();

    public Long getTotalEmpDataNotTerminate();

    public Long getTotalByTaxFreeIsNull();

    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate);

    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter param, int firstResult, int maxResults, Order orderable);

    public Long getTotalSalaryConfirmationByParam(SalaryConfirmationParameter param);

    public EmpData getByPKBankTransfer(long id);
    
    public EmpData getByEmpDataByBioDataId(long bioDataid);
    
    public List<EmpData> getAllDataByAbsisAndOrdinateAndGoljab(String absis, String ordinate, long golJabId);
    
    public BioDataModel getEmpNameWithNearestBirthDate();

    public List<EmpData> getAllDataByDepartementAndEducation(Long departementId, Long educationId, int firstResult, int maxResults, Order order);
}
