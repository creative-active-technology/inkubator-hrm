/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.util.Date;
import java.util.List;

import javax.script.ScriptException;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiService extends IService<PayTempKalkulasi> {

    /*public void calculatePayRoll() throws Exception;*/
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPayTempKalkulasiByParam(String searchParameter);

    public PayTempKalkulasi getEntityByPkWithDetail(Long id);

    public List<PayTempKalkulasi> getAllDataCalculatedPayment(Date payrollCalculationDate, String createdBy) throws Exception;

    public void deleteAllData() throws Exception;

    public Long getTotalKaryawan() throws Exception;

    public List<PayTempKalkulasi> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long paySalaryComponentId) throws Exception;

    public Long getTotalPayTempKalkulasiByParamForDetail(String searchParameter, Long paySalaryComponentId) throws Exception;
	
	public void executeBatchFinalSalaryCalculation(EmpData empData) throws ScriptException;

    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId);
    
    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId) throws Exception;
    
    public List<PayTempKalkulasi> getAllDataByEmpDataId(Long empDataId) throws Exception;

	public PayTempKalkulasi getEntityByEmpDataIdAndSpecificModelComponent(Long empDataid, Integer specific);

}
