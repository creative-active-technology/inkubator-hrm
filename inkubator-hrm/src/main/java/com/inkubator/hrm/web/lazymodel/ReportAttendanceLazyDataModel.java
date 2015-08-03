package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LogWtAttendanceRealization;
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;
import com.inkubator.hrm.web.model.LogWtAttendanceRealizationModel;

public class ReportAttendanceLazyDataModel extends LazyDataModel<LogWtAttendanceRealization> implements Serializable {
	private static final Logger LOGGER = Logger.getLogger(ReportAttendanceLazyDataModel.class);
    private final LogWtAttendanceRealizationModel model;
    private final LogWtAttendanceRealizationService service;
    private List<LogWtAttendanceRealization> logWtAttendanceRealizationList = new ArrayList<>();
    private Integer jumlahData;
    
    public ReportAttendanceLazyDataModel(LogWtAttendanceRealizationModel model, LogWtAttendanceRealizationService service) {
        this.model = model;
        this.service = service;
    }
    
    @Override
    public List<LogWtAttendanceRealization> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("empName");
                }
                logWtAttendanceRealizationList = service.getAllDataByParam(model, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(service.getTotalDataByParam(model)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return logWtAttendanceRealizationList;
    }
    
    @Override
    public Object getRowKey(LogWtAttendanceRealization logWtAttendanceRealization) {
        return logWtAttendanceRealization.getId();
    }

    @Override
    public LogWtAttendanceRealization getRowData(String id) {
        for (LogWtAttendanceRealization logWtAttendanceRealization : logWtAttendanceRealizationList) {
            if (id.equals(String.valueOf(logWtAttendanceRealization.getId()))) {
                return logWtAttendanceRealization;
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
