package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.FingerSwapCapturedService;
import com.inkubator.hrm.web.model.FingerSwapCapturedViewModel;
import com.inkubator.hrm.web.search.FingerSwapCapturedSearchParameter;


/**
*
* @author rizkykojek
*/
public class FingerSwapCapturedLazyDataModel extends LazyDataModel<FingerSwapCapturedViewModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(FingerSwapCapturedLazyDataModel.class);
    private final FingerSwapCapturedSearchParameter parameter;
    private final FingerSwapCapturedService fingerSwapCapturedService;
    private List<FingerSwapCapturedViewModel> list = new ArrayList<>();
    private Integer total;

    public FingerSwapCapturedLazyDataModel(FingerSwapCapturedSearchParameter parameter, FingerSwapCapturedService fingerSwapCapturedService) {
        this.parameter = parameter;
        this.fingerSwapCapturedService = fingerSwapCapturedService;
    }

    @Override
    public List<FingerSwapCapturedViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        list = fingerSwapCapturedService.getAllDataByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(fingerSwapCapturedService.getTotalByParam(parameter)));            
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
    public Object getRowKey(FingerSwapCapturedViewModel fingerSwapCaptured) {
        return fingerSwapCaptured.getId();
    }

    @Override
    public FingerSwapCapturedViewModel getRowData(String id) {
        for (FingerSwapCapturedViewModel fingerSwapCaptured : list) {
            if (id.equals(String.valueOf(fingerSwapCaptured.getId()))) {
                return fingerSwapCaptured;
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
