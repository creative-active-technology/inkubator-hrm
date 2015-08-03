/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
import com.inkubator.hrm.web.search.PayTempKalkulasiSearchParameter;
import com.inkubator.hrm.web.search.WtAttendanceCalculationSearchParameter;

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
public class LogAttendanceRealizationVmLazyDataModel extends LazyDataModel<TempAttendanceRealizationViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LogAttendanceRealizationVmLazyDataModel.class);
    private Long wtPeriodId;    
    private final LogWtAttendanceRealizationService service;
    private List<TempAttendanceRealizationViewModel> listTempAttendanceRealizationViewModel = new ArrayList<>();
    private Integer jumlahData;
    private WtAttendanceCalculationSearchParameter searchParameter;

    public LogAttendanceRealizationVmLazyDataModel(WtAttendanceCalculationSearchParameter searchParameter, LogWtAttendanceRealizationService service, Long wtPeriodId) {
        this.wtPeriodId = wtPeriodId;
        this.service = service;
        this.searchParameter = searchParameter;
    }
    
    @Override
    public List<TempAttendanceRealizationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId, first, pageSize, Order.desc("logAttendanceRealization.empName"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(searchParameter, wtPeriodId)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        }
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listTempAttendanceRealizationViewModel;
    }
    
    @Override
    public Object getRowKey(TempAttendanceRealizationViewModel tempAttendanceRealizationViewModel) {
        return tempAttendanceRealizationViewModel.getId();
    }

    @Override
    public TempAttendanceRealizationViewModel getRowData(String id) {
        for (TempAttendanceRealizationViewModel tempAttendanceRealizationViewModel : listTempAttendanceRealizationViewModel) {
            if (id.equals(String.valueOf(tempAttendanceRealizationViewModel.getId()))) {
                return tempAttendanceRealizationViewModel;
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
