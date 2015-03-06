package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.UnregSalary;
import com.inkubator.hrm.service.UnregSalaryService;
import com.inkubator.hrm.web.search.UnregSalarySearchParameter;


/**
*
* @author rizkykojek
*/
public class UnregSalaryCalculationLazyDataModel extends LazyDataModel<UnregSalary> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(UnregSalaryCalculationLazyDataModel.class);
    private final UnregSalaryService unregSalaryService;
    private Date fromPeriodPayrollType;
    private List<UnregSalary> list = new ArrayList<>();
    private Integer total;
    private UnregSalarySearchParameter parameter;

    public UnregSalaryCalculationLazyDataModel(UnregSalaryService unregSalaryService, Date fromPeriodPayrollType, UnregSalarySearchParameter parameter) {
        this.unregSalaryService = unregSalaryService;
        this.parameter = parameter;
        this.fromPeriodPayrollType = fromPeriodPayrollType;
    }

    @Override
    public List<UnregSalary> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	        	orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("salaryDate");
	        }
	        
	        list = unregSalaryService.getByParamBySalaryCalculation(parameter, fromPeriodPayrollType, first, pageSize, orderable);
	        total = Integer.parseInt(String.valueOf(unregSalaryService.getTotalByParamBySalaryCalculation(parameter, fromPeriodPayrollType)));            
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
    public Object getRowKey(UnregSalary model) {
        return model.getId();
    }

    @Override
    public UnregSalary getRowData(String id) {
        for (UnregSalary model : list) {
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
