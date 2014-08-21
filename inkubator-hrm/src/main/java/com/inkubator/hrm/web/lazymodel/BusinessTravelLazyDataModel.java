package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.BusinessTravel;
import com.inkubator.hrm.service.BusinessTravelService;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;


/**
*
* @author rizkykojek
*/
public class BusinessTravelLazyDataModel extends LazyDataModel<BusinessTravel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(BusinessTravelLazyDataModel.class);
    private final BusinessTravelSearchParameter parameter;
    private final BusinessTravelService businessTravelService;
    private List<BusinessTravel> businessTravels = new ArrayList<>();
    private Integer total;

    public BusinessTravelLazyDataModel(BusinessTravelSearchParameter parameter, BusinessTravelService businessTravelService) {
        this.parameter = parameter;
        this.businessTravelService = businessTravelService;
    }

    @Override
    public List<BusinessTravel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        businessTravels = businessTravelService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(businessTravelService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return businessTravels;
    }

    @Override
    public Object getRowKey(BusinessTravel businessTravel) {
        return businessTravel.getId();
    }

    @Override
    public BusinessTravel getRowData(String id) {
        for (BusinessTravel businessTravel : businessTravels) {
            if (id.equals(String.valueOf(businessTravel.getId()))) {
                return businessTravel;
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
