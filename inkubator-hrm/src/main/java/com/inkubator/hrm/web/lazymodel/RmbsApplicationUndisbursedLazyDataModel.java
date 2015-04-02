package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RmbsApplicationService;
import com.inkubator.hrm.web.model.RmbsApplicationUndisbursedViewModel;
import com.inkubator.hrm.web.search.RmbsApplicationUndisbursedSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsApplicationUndisbursedLazyDataModel extends LazyDataModel<RmbsApplicationUndisbursedViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsApplicationUndisbursedLazyDataModel.class);
    private final RmbsApplicationUndisbursedSearchParameter parameter;
    private final RmbsApplicationService rmbsApplicationService;
    private List<RmbsApplicationUndisbursedViewModel> list = new ArrayList<>();
    private Integer total;

    public RmbsApplicationUndisbursedLazyDataModel(RmbsApplicationUndisbursedSearchParameter parameter, RmbsApplicationService rmbsApplicationService) {
        this.parameter = parameter;
        this.rmbsApplicationService = rmbsApplicationService;
    }

    @Override
    public List<RmbsApplicationUndisbursedViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("approvalActivityId");
	        }
	        
	        list = rmbsApplicationService.getUndisbursedByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsApplicationService.getTotalUndisbursedByParam(parameter)));            
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
    public Object getRowKey(RmbsApplicationUndisbursedViewModel entity) {
        return entity.getApprovalActivityId();
    }

    @Override
    public RmbsApplicationUndisbursedViewModel getRowData(String id) {
        for (RmbsApplicationUndisbursedViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getApprovalActivityId()))) {
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
