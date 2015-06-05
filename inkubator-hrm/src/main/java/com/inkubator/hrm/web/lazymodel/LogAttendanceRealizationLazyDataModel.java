/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.entity.PayTempKalkulasi;
import com.inkubator.hrm.service.LogAttendanceRealizationService;
import com.inkubator.hrm.service.PayTempKalkulasiService;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.model.PayTempKalkulasiModel;
import com.inkubator.hrm.web.model.TempAttendanceRealizationViewModel;
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
public class LogAttendanceRealizationLazyDataModel extends LazyDataModel<TempAttendanceRealizationViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LogAttendanceRealizationLazyDataModel.class);
    private Long wtPeriodId;    
    private final LogAttendanceRealizationService service;
    private List<TempAttendanceRealizationViewModel> listTempAttendanceRealizationViewModel = new ArrayList<>();
    private Integer jumlahData;

    public LogAttendanceRealizationLazyDataModel(LogAttendanceRealizationService service, Long wtPeriodId) {
        this.wtPeriodId = wtPeriodId;
        this.service = service;
        
    }
    
    @Override
    public List<TempAttendanceRealizationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");

        if (sortField != null) {
            if (sortOrder == SortOrder.ASCENDING) {
                try {
                    listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, first, pageSize, Order.asc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            } else {
                try {
                    listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, first, pageSize, Order.desc(sortField));
                    jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId)));
                } catch (Exception ex) {
                    LOGGER.error("Error", ex);
                }
            }
        } else {
            try {
                listTempAttendanceRealizationViewModel = service.getListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId, first, pageSize, Order.desc("logAttendanceRealization.empName"));
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalListTempAttendanceRealizationViewModelByWtPeriodId(wtPeriodId)));
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
