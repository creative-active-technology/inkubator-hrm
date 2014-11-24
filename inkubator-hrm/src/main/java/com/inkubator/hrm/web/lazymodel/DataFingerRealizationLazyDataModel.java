package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.TempProcessReadFingerService;
import com.inkubator.hrm.web.model.DataFingerRealizationModel;


/**
*
* @author rizkykojek
*/
public class DataFingerRealizationLazyDataModel extends LazyDataModel<DataFingerRealizationModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(DataFingerRealizationLazyDataModel.class);
    private final Long parameter;
    private final TempProcessReadFingerService tempProcessReadFingerService;
    private List<DataFingerRealizationModel> listModel = new ArrayList<>();
    private Integer total;

    public DataFingerRealizationLazyDataModel(Long parameter, TempProcessReadFingerService tempProcessReadFingerService) {
        this.parameter = parameter;
        this.tempProcessReadFingerService = tempProcessReadFingerService;
    }

    @Override
    public List<DataFingerRealizationModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        listModel = tempProcessReadFingerService.getDataFingerRealizationByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(tempProcessReadFingerService.getTotalDataFingerRealizationByParam(parameter)));            
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
    public Object getRowKey(DataFingerRealizationModel model) {
        return model.getNik();
    }

    @Override
    public DataFingerRealizationModel getRowData(String nik) {
        for (DataFingerRealizationModel model : listModel) {
            if (nik.equals(String.valueOf(model.getNik()))) {
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
