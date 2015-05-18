package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.OrganizationLetter;
import com.inkubator.hrm.service.OrganizationLetterService;

/**
*
* @author rizkykojek
*/
public class OrganizationLetterLazyDataModel extends LazyDataModel<OrganizationLetter> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(OrganizationLetterLazyDataModel.class);
    private final String parameter;
    private final OrganizationLetterService organizationLetterService;
    private List<OrganizationLetter> organizationLetters = new ArrayList<>();
    private Integer total;

    public OrganizationLetterLazyDataModel(String parameter, OrganizationLetterService organizationLetterService) {
        this.parameter = parameter;
        this.organizationLetterService = organizationLetterService;
    }

    @Override
    public List<OrganizationLetter> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("letterNumber");
	        }
	        
	        organizationLetters = organizationLetterService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(organizationLetterService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return organizationLetters;
    }

    @Override
    public Object getRowKey(OrganizationLetter organizationLetter) {
        return organizationLetter.getId();
    }

    @Override
    public OrganizationLetter getRowData(String id) {
        for (OrganizationLetter organizationLetter : organizationLetters) {
            if (id.equals(String.valueOf(organizationLetter.getId()))) {
                return organizationLetter;
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
