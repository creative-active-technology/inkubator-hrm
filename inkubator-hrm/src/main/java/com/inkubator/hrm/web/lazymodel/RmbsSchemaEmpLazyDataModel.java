package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RmbsSchemaListOfEmpService;
import com.inkubator.hrm.web.model.RmbsSchemaEmpViewModel;
import com.inkubator.hrm.web.search.RmbsSchemaEmpSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsSchemaEmpLazyDataModel extends LazyDataModel<RmbsSchemaEmpViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsSchemaEmpLazyDataModel.class);
    private final RmbsSchemaEmpSearchParameter parameter;
    private final RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService;
    private List<RmbsSchemaEmpViewModel> list = new ArrayList<>();
    private Integer total;

    public RmbsSchemaEmpLazyDataModel(RmbsSchemaEmpSearchParameter parameter, RmbsSchemaListOfEmpService rmbsSchemaListOfEmpService) {
        this.parameter = parameter;
        this.rmbsSchemaListOfEmpService = rmbsSchemaListOfEmpService;
    }

    @Override
    public List<RmbsSchemaEmpViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("rmbsSchemaCode");
	        }
	        
	        list = rmbsSchemaListOfEmpService.getByParamEmployeeSchema(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsSchemaListOfEmpService.getTotalByParamEmployeeSchema(parameter)));            
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
    public Object getRowKey(RmbsSchemaEmpViewModel entity) {
        return entity.getEmpDataId();
    }

    @Override
    public RmbsSchemaEmpViewModel getRowData(String id) {
        for (RmbsSchemaEmpViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getEmpDataId()))) {
                return entity;
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
