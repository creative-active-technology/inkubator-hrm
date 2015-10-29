package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RecruitApplicantService;
import com.inkubator.hrm.web.model.ApplicantRealizationViewModel;
import com.inkubator.hrm.web.search.SelectionApplicantRealizationSearchParameter;


/**
*
* @author rizkykojek
*/
public class SelectionApplicantRealizationLazyDataModel extends LazyDataModel<ApplicantRealizationViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(SelectionApplicantRealizationLazyDataModel.class);
    private final SelectionApplicantRealizationSearchParameter parameter;
    private final RecruitApplicantService recruitApplicantService;
    private List<ApplicantRealizationViewModel> list = new ArrayList<>();
    private Integer total;

    public SelectionApplicantRealizationLazyDataModel(SelectionApplicantRealizationSearchParameter parameter, RecruitApplicantService recruitApplicantService) {
        this.parameter = parameter;
        this.recruitApplicantService = recruitApplicantService;
    }

    @Override
    public List<ApplicantRealizationViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        list = recruitApplicantService.getSelectionApplicantRealizationByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitApplicantService.getTotalSelectionApplicantRealizationByParam(parameter)));            
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
    public Object getRowKey(ApplicantRealizationViewModel entity) {
        return entity.getApplicantId();
    }

    @Override
    public ApplicantRealizationViewModel getRowData(String id) {
        for (ApplicantRealizationViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getApplicantId()))) {
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
