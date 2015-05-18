package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.TempProcessReadFinger;
import com.inkubator.hrm.service.TempProcessReadFingerService;


/**
*
* @author rizkykojek
*/
public class TempProcessReadFingerLazyDataModel extends LazyDataModel<TempProcessReadFinger> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(TempProcessReadFingerLazyDataModel.class);
    private final Long parameter;
    private final TempProcessReadFingerService tempProcessReadFingerService;
    private List<TempProcessReadFinger> listModel = new ArrayList<>();
    private Integer total;

    public TempProcessReadFingerLazyDataModel(Long parameter, TempProcessReadFingerService tempProcessReadFingerService) {
        this.parameter = parameter;
        this.tempProcessReadFingerService = tempProcessReadFingerService;
    }

    @Override
    public List<TempProcessReadFinger> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("scheduleDate");
	        }
	        
	        listModel = tempProcessReadFingerService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(tempProcessReadFingerService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return listModel;
    }

    @Override
    public Object getRowKey(TempProcessReadFinger model) {
        return model.getId();
    }

    @Override
    public TempProcessReadFinger getRowData(String id) {
        for (TempProcessReadFinger model : listModel) {
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
