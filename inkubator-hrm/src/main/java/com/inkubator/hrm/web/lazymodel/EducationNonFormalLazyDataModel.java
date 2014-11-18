package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.EducationNonFormal;
import com.inkubator.hrm.service.EducationNonFormalService;

/**
*
* @author rizkykojek
*/
public class EducationNonFormalLazyDataModel extends LazyDataModel<EducationNonFormal> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(EducationNonFormalLazyDataModel.class);
    private final String parameter;
    private final EducationNonFormalService service;
    private List<EducationNonFormal> list = new ArrayList<>();
    private Integer total;

    public EducationNonFormalLazyDataModel(String parameter, EducationNonFormalService educationNonFormalService) {
        this.parameter = parameter;
        this.service = educationNonFormalService;
    }

    @Override
    public List<EducationNonFormal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = service.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(parameter)));            
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
    public Object getRowKey(EducationNonFormal educationNonFormal) {
        return educationNonFormal.getId();
    }

    @Override
    public EducationNonFormal getRowData(String id) {
        for (EducationNonFormal educationNonFormal : list) {
            if (id.equals(String.valueOf(educationNonFormal.getId()))) {
                return educationNonFormal;
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
