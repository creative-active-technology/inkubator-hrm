package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.PermitImplementation;
import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.web.search.PermitImplementationSearchParameter;


/**
*
* @author Taufik
*/
public class PermitImplementationLazyDataModel extends LazyDataModel<PermitImplementation> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PermitImplementationLazyDataModel.class);
    private final PermitImplementationSearchParameter parameter;
    private final PermitImplementationService permitImplementationService;
    private List<PermitImplementation> permitImplementations = new ArrayList<>();
    private Integer total;

    public PermitImplementationLazyDataModel(PermitImplementationSearchParameter parameter, PermitImplementationService permitImplementationService) {
        this.parameter = parameter;
        this.permitImplementationService = permitImplementationService;
    }

    @Override
    public List<PermitImplementation> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        permitImplementations = permitImplementationService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(permitImplementationService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return permitImplementations;
    }

    @Override
    public Object getRowKey(PermitImplementation permitImplementation) {
        return permitImplementation.getId();
    }

    @Override
    public PermitImplementation getRowData(String id) {
        for (PermitImplementation permitImplementation : permitImplementations) {
            if (id.equals(String.valueOf(permitImplementation.getId()))) {
                return permitImplementation;
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
