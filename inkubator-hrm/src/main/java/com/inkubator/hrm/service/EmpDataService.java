/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EducationLevel;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DepAttendanceRealizationViewModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.EmpDataMatrixModel;
import com.inkubator.hrm.web.model.EmployeeRestModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
import com.inkubator.hrm.web.model.WtFingerExceptionModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportEmpDepartmentJabatanParameter;
import com.inkubator.hrm.web.search.ReportEmpWorkingGroupParameter;
import com.inkubator.hrm.web.search.ReportOfEmployeesFamilySearchParameter;
import com.inkubator.hrm.web.search.ReportRekapJabatanEmpSearchParameter;
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

    public EmpData getByPKBankTransfer(long id) throws Exception;

    public EmpData getByEmpDataByBioDataId(long bioDataid);

    public List<EmpDataMatrixModel> getAllDataByAbsisAndOrdinateAndGoljab(String absis, String ordinate, long golJabId) throws Exception;
    
    public BioDataModel getEmpNameWithNearestBirthDate();

    public List<EmpData> getAllDataByDepartementAndEducation(List<Department> listDepartement, List<EducationLevel> listEducation, int firstResult, int maxResults, Order order) throws Exception;
    
    public List<ReportEmployeeEducationViewModel> getAllDataByDepartementAndEducationWithHql(List<Department> departementId, List<EducationLevel> educationId, int firstResult, int maxResults, Order order);

    public Long getTotalDataByDepartementAndEducation(List<Department> listDepartement, List<EducationLevel> listEducation) throws Exception;
    
    public List<EmpData> getReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, int firstResult, int maxResults, Order order);

    public Long getTotalReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId);
    
    public List<ReportEmpPensionPreparationModel> getReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges, int firstResult, int maxResults, Order order);

    public Long getTotalReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges);

	public List<EmpData> getAllDataByDepartmentAndReligionAndGolJabAndEmpType(List<Long> departmentIds, List<Long> religionIds, List<Long> golJabIds, List<Long> empTypeIds);
	
	public Boolean isEmpDataWithNullWtGroupWorkingExist() throws Exception;

	public List<EmployeeRestModel> getAllDataRestModel(String nikOrName) throws Exception;
    
    public EmployeeRestModel getRestModelByNik(String nik) throws Exception;

	public Map<String,List<DepAttendanceRealizationViewModel>> getListDepAttendanceByDepartmentIdAndRangeDate(Date dateFrom, Date dateUntill) throws Exception;
}
