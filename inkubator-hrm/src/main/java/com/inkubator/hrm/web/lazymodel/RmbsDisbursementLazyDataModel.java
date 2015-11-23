package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RmbsApplicationDisbursement;
import com.inkubator.hrm.service.RmbsDisbursementService;
import com.inkubator.hrm.web.search.RmbsDisbursementSearchParameter;


/**
*
* @author rizkykojek
*/
public class RmbsDisbursementLazyDataModel extends LazyDataModel<RmbsApplicationDisbursement> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RmbsDisbursementLazyDataModel.class);
    private final RmbsDisbursementSearchParameter parameter;
    private final RmbsDisbursementService rmbsDisbursementService;
    private List<RmbsApplicationDisbursement> list = new ArrayList<>();
    private Integer total;

    public RmbsDisbursementLazyDataModel(RmbsDisbursementSearchParameter parameter, RmbsDisbursementService rmbsDisbursementService) {
        this.parameter = parameter;
        this.rmbsDisbursementService = rmbsDisbursementService;
    }

    @Override
    public List<RmbsApplicationDisbursement> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("rmbsDisbursement.disbursementDate");
	        }
	        
	        list = rmbsDisbursementService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(rmbsDisbursementService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RmbsApplicationDisbursement entity) {
        return entity.getId();
    }

    @Override
    public RmbsApplicationDisbursement getRowData(String id) {
        for (RmbsApplicationDisbursement entity : list) {
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
