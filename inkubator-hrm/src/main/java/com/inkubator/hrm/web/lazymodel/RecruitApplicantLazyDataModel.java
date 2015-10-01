package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RecruitApplicant;
import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.search.RecruitApplicantSearchParameter;


/**
*
* @author rizkykojek
*/
public class RecruitApplicantLazyDataModel extends LazyDataModel<RecruitApplicant> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RecruitApplicantLazyDataModel.class);
    private final RecruitApplicantSearchParameter parameter;
    private final RecruitApplicantService recruitApplicantService;
    private List<RecruitApplicant> list = new ArrayList<>();
    private Integer total;

    public RecruitApplicantLazyDataModel(RecruitApplicantSearchParameter parameter, RecruitApplicantService recruitApplicantService) {
        this.parameter = parameter;
        this.recruitApplicantService = recruitApplicantService;
    }

    @Override
    public List<RecruitApplicant> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = recruitApplicantService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitApplicantService.getTotalByParam(parameter)));            
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
    public Object getRowKey(RecruitApplicant entity) {
        return entity.getId();
    }

    @Override
    public RecruitApplicant getRowData(String id) {
        for (RecruitApplicant entity : list) {
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
