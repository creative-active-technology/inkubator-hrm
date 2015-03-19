package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RmbsType;
import com.inkubator.hrm.service.RmbsTypeService;
import com.inkubator.hrm.web.search.RmbsTypeSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsTypeLazyDataModel extends LazyDataModel<RmbsType> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsTypeLazyDataModel.class);
    private final RmbsTypeSearchParameter parameter;
    private final RmbsTypeService rmbsTypeService;
    private List<RmbsType> list = new ArrayList<>();
    private Integer total;

    public RmbsTypeLazyDataModel(RmbsTypeSearchParameter parameter, RmbsTypeService rmbsTypeService) {
        this.parameter = parameter;
        this.rmbsTypeService = rmbsTypeService;
    }

    @Override
    public List<RmbsType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        list = rmbsTypeService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsTypeService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RmbsType entity) {
        return entity.getId();
    }

    @Override
    public RmbsType getRowData(String id) {
        for (RmbsType entity : list) {
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
