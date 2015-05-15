package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TempUnregPayroll;
import com.inkubator.hrm.service.TempUnregPayrollService;
import com.inkubator.hrm.web.search.UnregCalculationSearchParameter;


/**
*
* @author rizkykojek
*/
public class UnregCalculationPayrollLazyDataModel extends LazyDataModel<TempUnregPayroll> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UnregCalculationPayrollLazyDataModel.class);
    private final TempUnregPayrollService tempUnregPayrollService;
    private List<TempUnregPayroll> list = new ArrayList<>();
    private Integer total;
    private UnregCalculationSearchParameter parameter;

    public UnregCalculationPayrollLazyDataModel(UnregCalculationSearchParameter parameter, TempUnregPayrollService tempUnregPayrollService) {
        this.tempUnregPayrollService = tempUnregPayrollService;
        this.parameter = parameter;
    }

    @Override
    public List<TempUnregPayroll> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = tempUnregPayrollService.getByParam(parameter, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(tempUnregPayrollService.getTotalByParam(parameter)));            
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
    public Object getRowKey(TempUnregPayroll model) {
        return model.getId();
    }

    @Override
    public TempUnregPayroll getRowData(String id) {
        for (TempUnregPayroll model : list) {
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
