package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.LogUnregPayrollService;
import com.inkubator.hrm.web.model.UnregPayrollViewModel;
import com.inkubator.hrm.web.search.UnregPayrollSearchParameter;


/**
*
* @author rizkykojek
*/
public class LogUnregPayrollLazyDataModel extends LazyDataModel<UnregPayrollViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LogUnregPayrollLazyDataModel.class);
    private final UnregPayrollSearchParameter parameter;
    private final LogUnregPayrollService logUnregPayrollService;
    private List<UnregPayrollViewModel> list = new ArrayList<>();
    private Integer total;

    public LogUnregPayrollLazyDataModel(UnregPayrollSearchParameter parameter, LogUnregPayrollService logUnregPayrollService) {
        this.parameter = parameter;
        this.logUnregPayrollService = logUnregPayrollService;
    }

    @Override
    public List<UnregPayrollViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("empName");
	        }
	        
	        list = logUnregPayrollService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(logUnregPayrollService.getTotalByParam(parameter)));            
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
    public Object getRowKey(UnregPayrollViewModel model) {
        return model.getId();
    }

    @Override
    public UnregPayrollViewModel getRowData(String id) {
        for (UnregPayrollViewModel model : list) {
            if (id.equals(String.valueOf(model.getId()))) {
                return model;
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
