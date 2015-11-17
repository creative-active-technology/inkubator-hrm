package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.RecruitSelectionApplicantPassed;
import com.inkubator.hrm.service.RecruitSelectionApplicantPassedService;
import com.inkubator.hrm.web.model.RecruitSelectionApplicantPassedViewModel;
import com.inkubator.hrm.web.search.RecruitSelectionApplicantPassedSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class RecruitSelectionApplicantPassedLazyDataModel extends LazyDataModel<RecruitSelectionApplicantPassedViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(RecruitSelectionApplicantPassedLazyDataModel.class);
    private final RecruitSelectionApplicantPassedSearchParameter searchParameter;
    private final RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService;
    private List<RecruitSelectionApplicantPassedViewModel> listSelectionApplicantPassedViewModel = new ArrayList<RecruitSelectionApplicantPassedViewModel>();
    private Integer total;

    public RecruitSelectionApplicantPassedLazyDataModel(RecruitSelectionApplicantPassedSearchParameter parameter, RecruitSelectionApplicantPassedService recruitSelectionApplicantPassedService) {
        this.searchParameter = parameter;
        this.recruitSelectionApplicantPassedService = recruitSelectionApplicantPassedService;
    }

    @Override
    public List<RecruitSelectionApplicantPassedViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("id");
	        }
	        
	        listSelectionApplicantPassedViewModel = recruitSelectionApplicantPassedService.getListSelectionPassedViewModelByParam(searchParameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(recruitSelectionApplicantPassedService.getTotalSelectionPassedViewModelByParam(searchParameter)));   
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return listSelectionApplicantPassedViewModel;
    }

    @Override
    public Object getRowKey(RecruitSelectionApplicantPassedViewModel recruitSelectionApplicantPassed) {
        return recruitSelectionApplicantPassed.getId();
    }

    @Override
    public RecruitSelectionApplicantPassedViewModel getRowData(String id) {
        for (RecruitSelectionApplicantPassedViewModel recruitSelectionApplicantPassed : listSelectionApplicantPassedViewModel) {
            if (id.equals(String.valueOf(recruitSelectionApplicantPassed.getId()))) {
                return recruitSelectionApplicantPassed;
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
