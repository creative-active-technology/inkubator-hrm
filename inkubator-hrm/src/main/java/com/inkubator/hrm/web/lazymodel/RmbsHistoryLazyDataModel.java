package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RmbsApplication;
import com.inkubator.hrm.service.RmbsApplicationService;


/**
*
* @author rizkykojek
*/
public class RmbsHistoryLazyDataModel extends LazyDataModel<RmbsApplication> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsHistoryLazyDataModel.class);
    private final Long empDataId;
    private final RmbsApplicationService rmbsApplicationService;
    private List<RmbsApplication> list = new ArrayList<>();
    private Integer total;

    public RmbsHistoryLazyDataModel(Long empDataId, RmbsApplicationService rmbsApplicationService) {
        this.empDataId = empDataId;
        this.rmbsApplicationService = rmbsApplicationService;
    }

    @Override
    public List<RmbsApplication> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("applicationDate");
	        }
	        
	        list = rmbsApplicationService.getReimbursementHistoryByParam(empDataId, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsApplicationService.getTotalReimbursementHistoryByParam(empDataId)));            
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
    public Object getRowKey(RmbsApplication entity) {
        return entity.getId();
    }

    @Override
    public RmbsApplication getRowData(String id) {
        for (RmbsApplication entity : list) {
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
