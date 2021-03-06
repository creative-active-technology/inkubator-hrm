/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.script.ScriptException;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.service.IService;
import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.SalaryJournalModel;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiService extends IService<PayTempKalkulasi> {

    /*public void calculatePayRoll() throws Exception;*/
    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order) throws Exception;

    public Long getTotalPayTempKalkulasiByParam(String searchParameter) throws Exception;

    public PayTempKalkulasi getEntityByPkWithDetail(Long id) throws Exception;

    public List<PayTempKalkulasi> getAllDataCalculatedPayment(Date startPeriodDate, Date endPeriodDate, Date createdOn, String createdBy) throws Exception;

    public void deleteAllData() throws Exception;

    public Long getTotalKaryawan() throws Exception;

    public List<PayTempKalkulasi> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long paySalaryComponentId) throws Exception;

    public Long getTotalPayTempKalkulasiByParamForDetail(String searchParameter, Long paySalaryComponentId) throws Exception;

    public void executeBatchFinalSalaryCalculation(EmpData empData) throws ScriptException;

    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId) throws Exception;

    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId) throws Exception;

    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndExcludeCompTHP(Long empDataId) throws Exception;

    public List<SalaryJournalModel> getByParamForSalaryJournal(String searchParameter, int firstResult, int maxResults, Order order, String locale) throws Exception;

    public Long getTotalPayTempKalkulasiForSalaryJournal(String searchParameter) throws Exception;

    public PayTempKalkulasi getEntityByEmpDataIdAndSpecificModelComponent(Long empDataid, Integer specific) throws Exception;

	public List<PayTempKalkulasi> getAllDataByTotalIncomeBelow(BigDecimal nominal) throws Exception;    
   
}
