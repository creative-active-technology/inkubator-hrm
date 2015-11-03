package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RecruitSelectionApplicantSchedulleService;
import com.inkubator.hrm.web.model.SelectionPositionPassedViewModel;


/**
*
* @author rizkykojek
*/
public class SelectionPositionPassedLazyDataModel extends LazyDataModel<SelectionPositionPassedViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(SelectionPositionPassedLazyDataModel.class);
    private final String parameter;
    private final RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
    private List<SelectionPositionPassedViewModel> list = new ArrayList<>();
    private Integer total;

    public SelectionPositionPassedLazyDataModel(String parameter, RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService) {
        this.parameter = parameter;
        this.recruitSelectionApplicantSchedulleService = recruitSelectionApplicantSchedulleService;
    }

    @Override
    public List<SelectionPositionPassedViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("positionName");
	        }
	        
	        list = recruitSelectionApplicantSchedulleService.getSelectionPositionPassedByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitSelectionApplicantSchedulleService.getTotalSelectionPositionPassedByParam(parameter)));            
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
    public Object getRowKey(SelectionPositionPassedViewModel entity) {
        return entity.getPositionId();
    }

    @Override
    public SelectionPositionPassedViewModel getRowData(String id) {
        for (SelectionPositionPassedViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getPositionId()))) {
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
