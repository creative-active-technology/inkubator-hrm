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
import com.inkubator.hrm.service.LogWtAttendanceRealizationService;


/**
*
* @author rizkykojek
*/
public class PaidOvertimeLazyDataModel extends LazyDataModel<LogWtAttendanceRealization> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PaidOvertimeLazyDataModel.class);
    private final Long wtPeriodId;
    private final LogWtAttendanceRealizationService logWtAttendanceRealizationService;
    private List<LogWtAttendanceRealization> list = new ArrayList<>();
    private Integer total;

    public PaidOvertimeLazyDataModel(Long wtPeriodId, LogWtAttendanceRealizationService logWtAttendanceRealizationService) {
        this.wtPeriodId = wtPeriodId;
        this.logWtAttendanceRealizationService = logWtAttendanceRealizationService;
    }

    @Override
    public List<LogWtAttendanceRealization> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = logWtAttendanceRealizationService.getPaidOvertimeByParam(wtPeriodId, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(logWtAttendanceRealizationService.getTotalPaidOvertimeByParam(wtPeriodId)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return list;
    }

    @Override
    public Object getRowKey(LogWtAttendanceRealization logWtAttendanceRealization) {
        return logWtAttendanceRealization.getId();
    }

    @Override
    public LogWtAttendanceRealization getRowData(String id) {
        for (LogWtAttendanceRealization logWtAttendanceRealization : list) {
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
