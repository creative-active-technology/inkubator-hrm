/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.common.util.DateTimeUtil;
import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.PayrollHistoryReportModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
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
 * @author Ahmad Mudzakkir Amal
 */
public class PayrollHistoryReportLazyDataModel extends LazyDataModel<PayrollHistoryReportModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(PayrollHistoryReportLazyDataModel.class);
    private final String searchParameter;
    private final LogMonthEndPayrollService service;
    private List<PayrollHistoryReportModel> payrollHistoryReportModelList = new ArrayList<>();
    private Integer jumlahData;

    public PayrollHistoryReportLazyDataModel(String searchParameter, LogMonthEndPayrollService service) {
        this.searchParameter = searchParameter;
        this.service = service;
    }
    
    @Override
    public List<PayrollHistoryReportModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    payrollHistoryReportModelList = service.getByParamForPayrollHistoryReport(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamForPayrollHistoryReport(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    payrollHistoryReportModelList = service.getByParamForPayrollHistoryReport(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamForPayrollHistoryReport(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                payrollHistoryReportModelList = service.getByParamForPayrollHistoryReport(searchParameter, first, pageSize, Order.desc("periodeStart"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalByParamForPayrollHistoryReport(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");
      
        setPageSize(pageSize);
        setRowCount(jumlahData);
        return payrollHistoryReportModelList;
    }

    public List<PayrollHistoryReportModel> getPayrollHistoryReportModelList() {
        return payrollHistoryReportModelList;
    }

    public void setPayrollHistoryReportModelList(List<PayrollHistoryReportModel> payrollHistoryReportModelList) {
        this.payrollHistoryReportModelList = payrollHistoryReportModelList;
    }

    public Integer getJumlahData() {
        return jumlahData;
    }

    public void setJumlahData(Integer jumlahData) {
        this.jumlahData = jumlahData;
    }
    
    
    @Override
    public Object getRowKey(PayrollHistoryReportModel payrollHistoryReportModel) {
        return payrollHistoryReportModel.getId();
    }

    @Override
    public PayrollHistoryReportModel getRowData(String id) {
        for (PayrollHistoryReportModel payrollHistoryReportModel : payrollHistoryReportModelList) {
            if (id.equals(String.valueOf(payrollHistoryReportModel.getId()))) {
                return payrollHistoryReportModel;
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
