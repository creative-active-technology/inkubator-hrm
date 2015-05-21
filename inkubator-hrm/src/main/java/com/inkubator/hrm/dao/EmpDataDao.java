/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.Department;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.GolonganJabatan;
import com.inkubator.hrm.web.model.BioDataModel;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.DistributionOvetTimeModel;
import com.inkubator.hrm.web.model.PermitDistributionModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.model.ReportEmpPensionPreparationModel;
import com.inkubator.hrm.web.model.ReportEmployeeEducationViewModel;
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
public interface EmpDataDao extends IDAO<EmpData> {

	/** get property */
    public Long getTotalByGender(Integer gender);

    public Long getTotalByAgeBetween(Date startDate, Date endDate);

    public Long getTotalByAgeLessThan(Date date);

    public Long getTotalByAgeMoreThan(Date date);

    public Long getTotalByDepartmentId(Long departmentId);

    public Long getTotalByNikandNotId(String nik, Long id);

    public Long getTotalByNIK(String nik);
    
    public Long getTotalEmpDataNotTerminate();

    public Long getTotalByTaxFreeIsNull();        
    
    public Long getTotalKaryawanByJabatanId(Long jabatanId);
    
    
    
    
    /** get entity */       
    public EmpData getByEmpIdWithDetail(long id);

    public EmpData getByBioDataWithDepartment(long id);
    
    public EmpData getEntityByNik(String nik);
    
    public EmpData getByIdWithDetail(long id);   
    
    public EmpData getEmpDataWithBiodata(Long id);    

    public EmpData getByPKBankTransfer(long id);

    public EmpData getByEmpDataByBioDataId(long bioDataid);
    
    public BioDataModel getEmpNameWithNearestBirthDate();
    
    
    
    
    /** get pageable/paging */
    public List<EmpData> getByParam(EmpDataSearchParameter searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpDataByParam(EmpDataSearchParameter searchParameter);   
    
    public List<EmpData> getByParam(String nikOrNameSearchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalEmpDataByParam(String nikOrNameSearchParameter);   

    public List<EmpData> getAllDataReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param, int firstResult, int maxResults, Order orderable);

    public Long getTotalReportEmpWorkingGroupByParam(ReportEmpWorkingGroupParameter param);
    
    public List<EmpData> getAllDataReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param, int firstResult, int maxResults, Order orderable);

    public Long getTotalReportEmpDepartmentJabatanByParam(ReportEmpDepartmentJabatanParameter param);
    
    public List<EmpData> getAllDataByDepartementAndEducation(List<Long> departementId, List<Long> educationId, int firstResult, int maxResults, Order order);

    public List<ReportEmployeeEducationViewModel> getAllDataByDepartementAndEducationWithHql(List<Long> departementId, List<Long> educationId, int firstResult, int maxResults, Order order);

    public Long getTotalDataByDepartementAndEducation(List<Long> departementId, List<Long> educationId);
    
    public List<EmpData> getReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, int firstResult, int maxResults, Order order);

    public Long getTotalReportRekapJabatanByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId);
    
    public List<ReportEmpPensionPreparationModel> getReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges, int firstResult, int maxResults, Order order);

    public Long getTotalReportPensionPreparementByParam(List<Long> listDepartmentId, List<Long> listEmpTypeId, List<Integer> listEmpAges);
    
    public List<EmpData> getAllDataByParamWithDetail(List<Department> listDepartment, List<GolonganJabatan> listGoljab, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik, int firstResult, int maxResults, Order order);    
    
    public Long getTotalByParamWithDetail(List<Department> listDepartment, List<GolonganJabatan> listGoljab, String[] empTypeName, List<Integer> listAge, List<Integer> listJoinDate, List<String> listNik);
    
    public List<EmpData> getAllDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId, int firstResult, int maxResults, Order order);

    public Long getTotalDataByEmployeeTypeOrGolonganJabatanOrUnitKerja(List<Long> empTypeId, List<Long> golJabId, List<Long> unitKerjaId);
    
    public List<EmpData> getAllDataNotExistInUserByParam(String param, int firstResult, int maxResults, Order order);
    
    public Long getTotalNotExistInUserByParam(String param);
    
    public List<EmpData> getAllDataSalaryConfirmationByParam(SalaryConfirmationParameter param, int firstResult, int maxResults, Order orderable);
    
    public Long getTotalSalaryConfirmationByParam(SalaryConfirmationParameter param);

    
    
    
    
    /** get list */
    public List<EmpData> getAllDataByNameOrNik(String param);    

    public List<EmpData> getAllDataByJabatanId(Long jabatanId, Order order);

    public List<EmpData> getAllDataByGolJabatanIdAndDepartmentId(Long jabatanId, Long departmentId);

    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model);

    public List<EmpData> getEmployeeBySearchEmployeeLeave(DistributionLeaveSchemeModel model);

    public List<EmpData> getEmployeeByOtSearchParameter(DistributionOvetTimeModel model);

    public List<EmpData> getEmpDataByListId(List<Long> data);    

    public List<EmpData> getEmployeeBySearchEmployeePermit(PermitDistributionModel model);        

    public List<EmpData> getEmployeeBySearchEmployeeFingerException(WtFingerExceptionModel model);

    public List<EmpData> getAllDataNotTerminate();

    public List<EmpData> getAllDataNotTerminateAndJoinDateLowerThan(Date payrollCalculationDate);

    public List<EmpData> getAllDataByDepartmentAndReligionAndGolJabAndEmpType(List<Long> departmentIds, List<Long> religionIds, List<Long> golJabIds, List<Long> empTypeIds);

    public List<String> getAllNikBetween(String from, String until);
    
    public List<EmpData> getAllDataByCompanyIdAndEmpTypeAndGolJabAndUnitKerja(Long companyId, List<Long> empTypes, List<Long> golJabs, List<Long> unitKerjas);    
    
}
