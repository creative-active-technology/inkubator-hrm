package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TempAttendanceRealization;
import com.inkubator.hrm.service.TempAttendanceRealizationService;
import com.inkubator.hrm.web.search.PaidOvertimeSearchParameter;


/**
*
* @author rizkykojek
*/
public class PaidOvertimeLazyDataModel extends LazyDataModel<TempAttendanceRealization> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(PaidOvertimeLazyDataModel.class);
    private final TempAttendanceRealizationService tempAttendanceRealizationService;
    private final PaidOvertimeSearchParameter searchParameter;
    private List<TempAttendanceRealization> list = new ArrayList<>();
    private Integer total;

    public PaidOvertimeLazyDataModel(PaidOvertimeSearchParameter searchParameter, TempAttendanceRealizationService tempAttendanceRealizationService) {
    	this.searchParameter = searchParameter;
        this.tempAttendanceRealizationService = tempAttendanceRealizationService;
    }

    @Override
    public List<TempAttendanceRealization> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = tempAttendanceRealizationService.getPaidOvertimeByParam(searchParameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(tempAttendanceRealizationService.getTotalPaidOvertimeByParam(searchParameter)));            
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
    public Object getRowKey(TempAttendanceRealization tempAttendanceRealization) {
        return tempAttendanceRealization.getId();
    }

    @Override
    public TempAttendanceRealization getRowData(String id) {
        for (TempAttendanceRealization tempAttendanceRealization : list) {
            if (id.equals(String.valueOf(tempAttendanceRealization.getId()))) {
                return tempAttendanceRealization;
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
