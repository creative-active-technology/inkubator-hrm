/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.HRMConstant;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.SalaryJournalModel;
import com.inkubator.webcore.util.FacesUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author deni
 */
public class SalaryJournalLazyDataModel extends LazyDataModel<SalaryJournalModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(SalaryJournalLazyDataModel.class);
    private final String searchParameter;
    private final PayTempKalkulasiService service;
    private List<SalaryJournalModel> salaryJournalList = new ArrayList<>();
    private Integer jumlahData;

    public SalaryJournalLazyDataModel(String searchParameter, PayTempKalkulasiService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<SalaryJournalModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("costCenterCode");
                }
                salaryJournalList = service.getByParamForSalaryJournal(searchParameter, first, pageSize, order, FacesUtil.getSessionAttribute(HRMConstant.BAHASA_ACTIVE).toString());
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalPayTempKalkulasiForSalaryJournal(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return salaryJournalList;
    }
    
    @Override
    public Object getRowKey(SalaryJournalModel salaryJournalModel) {
        return salaryJournalModel.getId();
    }

    @Override
    public SalaryJournalModel getRowData(String id) {
        for (SalaryJournalModel salaryJournalModel : salaryJournalList) {
            if (id.equals(String.valueOf(salaryJournalModel.getId()))) {
                return salaryJournalModel;
            }
        }
        return null;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }
}
