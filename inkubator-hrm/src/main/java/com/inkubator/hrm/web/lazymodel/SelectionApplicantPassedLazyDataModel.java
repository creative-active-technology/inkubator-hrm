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
import com.inkubator.hrm.web.model.SelectionApplicantPassedViewModel;


/**
*
* @author rizkykojek
*/
public class SelectionApplicantPassedLazyDataModel extends LazyDataModel<SelectionApplicantPassedViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(SelectionApplicantPassedLazyDataModel.class);
    private final Long scheduleId;
    private final RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService;
    private List<SelectionApplicantPassedViewModel> list = new ArrayList<>();
    private Integer total;

    public SelectionApplicantPassedLazyDataModel(Long scheduleId, RecruitSelectionApplicantSchedulleService recruitSelectionApplicantSchedulleService) {
        this.scheduleId = scheduleId;
        this.recruitSelectionApplicantSchedulleService = recruitSelectionApplicantSchedulleService;
    }

    @Override
    public List<SelectionApplicantPassedViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("applicantName");
	        }
	        
	        list = recruitSelectionApplicantSchedulleService.getSelectionApplicantPassedByParam(scheduleId, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitSelectionApplicantSchedulleService.getTotalSelectionApplicantPassedByParam(scheduleId)));            
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
    public Object getRowKey(SelectionApplicantPassedViewModel entity) {
        return entity.getApplicantId();
    }

    @Override
    public SelectionApplicantPassedViewModel getRowData(String id) {
        for (SelectionApplicantPassedViewModel entity : list) {
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
