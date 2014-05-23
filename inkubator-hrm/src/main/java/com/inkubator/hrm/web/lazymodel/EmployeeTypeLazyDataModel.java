package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.EmployeeType;
import com.inkubator.hrm.service.EmployeeTypeService;

/**
*
* @author rizkykojek
*/
public class EmployeeTypeLazyDataModel extends LazyDataModel<EmployeeType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(EmployeeTypeLazyDataModel.class);
    private final String parameter;
    private final EmployeeTypeService employeeTypeService;
    private List<EmployeeType> employeeTypes = new ArrayList<>();
    private Integer total;

    public EmployeeTypeLazyDataModel(String parameter, EmployeeTypeService employeeTypeService) {
        this.parameter = parameter;
        this.employeeTypeService = employeeTypeService;
    }

    @Override
    public List<EmployeeType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        employeeTypes = employeeTypeService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(employeeTypeService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return employeeTypes;
    }

    @Override
    public Object getRowKey(EmployeeType employeeType) {
        return employeeType.getId();
    }

    @Override
    public EmployeeType getRowData(String id) {
        for (EmployeeType employeeType : employeeTypes) {
            if (id.equals(String.valueOf(employeeType.getId()))) {
                return employeeType;
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
