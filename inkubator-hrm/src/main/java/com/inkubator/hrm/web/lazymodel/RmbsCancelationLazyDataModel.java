package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RmbsCancelationService;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsCancelationLazyDataModel extends LazyDataModel<RmbsCancelationViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsCancelationLazyDataModel.class);
    private final RmbsCancelationSearchParameter parameter;
    private final RmbsCancelationService rmbsCancelationService;
    private List<RmbsCancelationViewModel> list = new ArrayList<>();
    private Integer total;

    public RmbsCancelationLazyDataModel(RmbsCancelationSearchParameter parameter, RmbsCancelationService rmbsCancelationService) {
        this.parameter = parameter;
        this.rmbsCancelationService = rmbsCancelationService;
    }

    @Override
    public List<RmbsCancelationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("cancellation.code");
	        }
	        
	        list = rmbsCancelationService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsCancelationService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RmbsCancelationViewModel entity) {
        return entity.getCancelationId();
    }

    @Override
    public RmbsCancelationViewModel getRowData(String id) {
        for (RmbsCancelationViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getCancelationId()))) {
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
