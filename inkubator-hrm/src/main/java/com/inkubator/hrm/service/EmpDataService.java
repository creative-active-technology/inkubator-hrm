/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
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
import java.util.Map;

import org.hibernate.criterion.Order;

/**
 *
 * @author Deni Husni FR
 */
public interface EmpDataService extends IService<EmpData> {

    public Map<String, Long> getTotalByGender() throws Exception;

    public Map<String, Long> getTotalByAge() throws Exception;

    public Map<String, Long> getTotalByDepartment() throws Exception;

    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter) throws Exception;

    public EmpData getByEmpIdWithDetail(long id) throws Exception;

    public EmpData getByBioDataIdWithDepartment(long id) throws Exception;

    public List<EmpData> getAllDataWithRelation() throws Exception;

    public List<EmpData> getAllDataByNameOrNik(String param) throws Exception;

    public EmpData getByIdWithDetail(long id) throws Exception;

    public EmpData getEntityByNik(String nik) throws Exception;

    public void doSaveRotasi(EmpData empData) throws Exception;

    public List<EmpData> getAllDataNotExistInUserByParam(String param, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalNotExistInUserByParam(String param) throws Exception;

//<<<<<<< HEAD
//    public List<EmpData> getTotalBySearchEmployee(Long workingGroupId, Integer deptLikeOrEqual, String deptName, Integer empTypeLikeOrEqual, String empTypeName, Integer gender, Long golJabId, Integer sortBy, Integer orderBy) throws Exception;
//        
    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model) throws Exception;

    public List<EmpData> getEmployeelBySearchEmployeeLeave(DistributionLeaveSchemeModel model) throws Exception;

    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model) throws Exception;

    public List<EmpData> getEmpDataByListId(List<Long> data) throws Exception;

    public List<EmpData> getAllDataReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param) throws Exception;

    public List<EmpData> getEmployeelBySearchEmployeePermit(PermitDistributionModel model) throws Exception;

    public List<EmpData> getAllDataReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReportOfEmployeesFamilyByParam(ReportOfEmployeesFamilySearchParameter searchParameter) throws Exception;

    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable) throws Exception;

    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param) throws Exception;

    public List<EmpData> getEmployeeBySearchEmployeeFingerException(WtFingerExceptionModel model) throws Exception;

    public void saveForPtkp(EmpData empData) throws Exception;

    public EmpData getEmpDataWithBiodata(Long id) throws Exception;

    public List<EmpData> getAllDataNotTerminate() throws Exception;

    public Long getTotalEmpDataNotTerminate() throws Exception;

    public Long getTotalByTaxFreeIsNull() throws Exception;

    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate) throws Exception;

    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter parameter, int first, int pageSize, Order orderable) throws Exception;

    public Long getTotalSalaryConfirmationByParam(SalaryConfirmationParameter parameter) throws Exception;
    
    public EmpData getByPKBankTransfer(long  id) throws Exception;
    
     public EmpData getByEmpDataByBioDataId(long bioDataid);

}
