/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.EmpData;
import com.inkubator.hrm.entity.LogMonthEndPayroll;
import com.inkubator.hrm.service.EmpDataService;
import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.search.EmpDataSearchParameter;
import com.inkubator.hrm.web.search.ReportDataComponentSearchParameter;
import com.inkubator.hrm.web.search.ReportRekapJabatanEmpSearchParameter;
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
public class ReportDataKomponenLazyDataModel extends LazyDataModel<LogMonthEndPayroll> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ReportDataKomponenLazyDataModel.class);
    private final ReportDataComponentSearchParameter searchParameter;
    private final LogMonthEndPayrollService logMonthEndPayrollService;
    private List<LogMonthEndPayroll> listLogMonthEndPayroll = new ArrayList<>();
    private Integer jumlah;

    public ReportDataKomponenLazyDataModel(ReportDataComponentSearchParameter searchParameter, LogMonthEndPayrollService logMonthEndPayrollService) {       
        this.searchParameter = searchParameter;
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }

    @Override
    public List<LogMonthEndPayroll> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");
      
        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listLogMonthEndPayroll = logMonthEndPayrollService.getReportDataKomponenByParam(searchParameter, first, pageSize, Order.asc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(logMonthEndPayrollService.getTotalReportDataKomponenByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listLogMonthEndPayroll = logMonthEndPayrollService.getReportDataKomponenByParam(searchParameter, first, pageSize, Order.desc(sortField));
                    jumlah = Integer.parseInt(String.valueOf(logMonthEndPayrollService.getTotalReportDataKomponenByParam(searchParameter)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listLogMonthEndPayroll = logMonthEndPayrollService.getReportDataKomponenByParam(searchParameter, first, pageSize, Order.asc("departmentName"));
                jumlah = Integer.parseInt(String.valueOf(logMonthEndPayrollService.getTotalReportDataKomponenByParam(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        setPageSize(pageSize);
        setRowCount(jumlah);
        return listLogMonthEndPayroll;
    }

    @Override
    public Object getRowKey(LogMonthEndPayroll logMonthEndPayroll) {
        return logMonthEndPayroll.getId();
    }

    @Override
    public LogMonthEndPayroll getRowData(String id) {
        for (LogMonthEndPayroll logMonthEndPayroll : listLogMonthEndPayroll) {
            if (id.equals(String.valueOf(logMonthEndPayroll.getId()))) {
                return logMonthEndPayroll;
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
