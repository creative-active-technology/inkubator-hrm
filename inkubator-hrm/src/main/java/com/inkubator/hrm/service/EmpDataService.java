/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.web.employee.LeaveDistributionScheme;
import com.inkubator.hrm.web.model.DistributionLeaveSchemeModel;
import com.inkubator.hrm.web.model.PlacementOfEmployeeWorkScheduleModel;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
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

    public void savePenempatanJadwal(EmpData empData) throws Exception;

//<<<<<<< HEAD
//    public List<EmpData> getTotalBySearchEmployee(Long workingGroupId, Integer deptLikeOrEqual, String deptName, Integer empTypeLikeOrEqual, String empTypeName, Integer gender, Long golJabId, Integer sortBy, Integer orderBy) throws Exception;
//    
    public void saveMassPenempatanJadwal(List<EmpData> data, long groupWorkingId) throws Exception;

    public List<EmpData> getTotalBySearchEmployee(PlacementOfEmployeeWorkScheduleModel model) throws Exception;
    
    public List<EmpData> getTotalBySearchEmployeeLeave(DistributionLeaveSchemeModel model) throws Exception;
    
}
