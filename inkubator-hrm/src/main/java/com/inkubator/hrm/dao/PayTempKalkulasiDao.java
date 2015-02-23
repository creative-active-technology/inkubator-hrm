/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import com.inkubator.datacore.dao.IDAO;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.model.SalaryJournalModel;

/**
 *
 * @author denifahri
 */
public interface PayTempKalkulasiDao extends IDAO<PayTempKalkulasi> {

    public void saveBatch(List<PayTempKalkulasi> data);

    public List<PayTempKalkulasiModel> getByParam(String searchParameter, int firstResult, int maxResults, Order order);

    public Long getTotalPayTempKalkulasiByParam(String searchParameter);

    public PayTempKalkulasi getEntityByPkWithDetail(Long id);

    public void deleteAllData();

    public Long getTotalKaryawan();

    public List<PayTempKalkulasi> getByParamForDetail(String searchParameter, int firstResult, int maxResults, Order order, Long paySalaryComponentId);

    public Long getTotalPayTempKalkulasiByParamForDetail(String searchParameter, Long paySalaryComponentId);

	public PayTempKalkulasi getEntityByEmpDataIdAndSpecificModelComponent(Long empDataId, Integer specific);

    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndTaxNotNull(Long empDataId);
    
    public PayTempKalkulasi getEntityByEmpIdAndModelTakeHomePayId(Long empId);
    
    public List<PayTempKalkulasi> getAllDataByEmpDataIdAndExcludeCompTHP(Long empDataId);

    public List<SalaryJournalModel> getByParamForSalaryJournal(String searchParameter, int firstResult, int maxResults, Order order);
    
    public Long getTotalPayTempKalkulasiForSalaryJournal(String searchParameter);
    
    public List<SalaryJournalModel> getByParamForSalaryJournalDebet(String searchParameter);
    
    public List<SalaryJournalModel> getByParamForSalaryJournalKredit(String searchParameter);
    
    public Long getTotalPayTempKalkulasiForSalaryJournalDebetAndKredit(String searchParameter);    
    

}
