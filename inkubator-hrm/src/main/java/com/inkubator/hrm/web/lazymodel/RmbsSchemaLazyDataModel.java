package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RmbsSchema;
import com.inkubator.hrm.service.RmbsSchemaService;
import com.inkubator.hrm.web.search.RmbsSchemaSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsSchemaLazyDataModel extends LazyDataModel<RmbsSchema> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsSchemaLazyDataModel.class);
    private final RmbsSchemaSearchParameter parameter;
    private final RmbsSchemaService rmbsSchemaService;
    private List<RmbsSchema> list = new ArrayList<>();
    private Integer total;

    public RmbsSchemaLazyDataModel(RmbsSchemaSearchParameter parameter, RmbsSchemaService rmbsSchemaService) {
        this.parameter = parameter;
        this.rmbsSchemaService = rmbsSchemaService;
    }

    @Override
    public List<RmbsSchema> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        list = rmbsSchemaService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsSchemaService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RmbsSchema entity) {
        return entity.getId();
    }

    @Override
    public RmbsSchema getRowData(String id) {
        for (RmbsSchema entity : list) {
            if (id.equals(String.valueOf(entity.getId()))) {
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
