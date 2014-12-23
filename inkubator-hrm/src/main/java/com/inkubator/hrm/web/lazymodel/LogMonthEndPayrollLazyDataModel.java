package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.LogMonthEndPayrollService;
import com.inkubator.hrm.web.model.LogMonthEndPayrollViewModel;
import com.inkubator.hrm.web.search.LogMonthEndPayrollSearchParameter;


/**
*
* @author rizkykojek
*/
public class LogMonthEndPayrollLazyDataModel extends LazyDataModel<LogMonthEndPayrollViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LogMonthEndPayrollLazyDataModel.class);
    private final LogMonthEndPayrollSearchParameter parameter;
    private final LogMonthEndPayrollService logMonthEndPayrollService;
    private List<LogMonthEndPayrollViewModel> list = new ArrayList<>();
    private Integer total;

    public LogMonthEndPayrollLazyDataModel(LogMonthEndPayrollSearchParameter parameter, LogMonthEndPayrollService logMonthEndPayrollService) {
        this.parameter = parameter;
        this.logMonthEndPayrollService = logMonthEndPayrollService;
    }

    @Override
    public List<LogMonthEndPayrollViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("empName");
	        }
	        
	        list = logMonthEndPayrollService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(logMonthEndPayrollService.getTotalByParam(parameter)));            
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
    public Object getRowKey(LogMonthEndPayrollViewModel model) {
        return model.getId();
    }

    @Override
    public LogMonthEndPayrollViewModel getRowData(String id) {
        for (LogMonthEndPayrollViewModel model : list) {
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
