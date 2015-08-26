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
import com.inkubator.hrm.web.model.BusinessTravelViewModel;
import com.inkubator.hrm.web.search.BusinessTravelSearchParameter;


/**
*
* @author rizkykojek
*/
public class BusinessTravelActivityLazyDataModel extends LazyDataModel<BusinessTravelViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(BusinessTravelActivityLazyDataModel.class);
    private final BusinessTravelSearchParameter parameter;
    private final BusinessTravelService businessTravelService;
    private List<BusinessTravelViewModel> list = new ArrayList<>();
    private Integer total;

    public BusinessTravelActivityLazyDataModel(BusinessTravelSearchParameter parameter, BusinessTravelService businessTravelService) {
        this.parameter = parameter;
        this.businessTravelService = businessTravelService;
    }

    @Override
    public List<BusinessTravelViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("approvalActivity.created_time");
	        }
	        
	        list = businessTravelService.getAllActivityByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(businessTravelService.getTotalActivityByParam(parameter)));            
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
    public Object getRowKey(BusinessTravelViewModel model) {
        return model.getApprovalActivityId();
    }

    @Override
    public BusinessTravelViewModel getRowData(String id) {
        for (BusinessTravelViewModel model : list) {
            if (id.equals(String.valueOf(model.getApprovalActivityId()))) {
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
