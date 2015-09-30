package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Applicant;
import com.inkubator.hrm.service.ApplicantService;
import com.inkubator.hrm.web.search.ApplicantSearchParameter;


/**
*
* @author rizkykojek
*/
public class ApplicantLazyDataModel extends LazyDataModel<Applicant> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ApplicantLazyDataModel.class);
    private final ApplicantSearchParameter parameter;
    private final ApplicantService applicantService;
    private List<Applicant> list = new ArrayList<>();
    private Integer total;

    public ApplicantLazyDataModel(ApplicantSearchParameter parameter, ApplicantService applicantService) {
        this.parameter = parameter;
        this.applicantService = applicantService;
    }

    @Override
    public List<Applicant> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = applicantService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(applicantService.getTotalByParam(parameter)));            
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
    public Object getRowKey(Applicant entity) {
        return entity.getId();
    }

    @Override
    public Applicant getRowData(String id) {
        for (Applicant entity : list) {
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
