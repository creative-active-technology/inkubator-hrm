package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.LoanNewCancelationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.RmbsCancelationService;
import com.inkubator.hrm.web.model.LoanNewCancelationBoxViewModel;
import com.inkubator.hrm.web.model.RmbsCancelationViewModel;
import com.inkubator.hrm.web.search.LoanNewCancelationBoxSearchParameter;
import com.inkubator.hrm.web.search.RmbsCancelationSearchParameter;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class LoanNewCancelationBoxLazyDataModel extends LazyDataModel<LoanNewCancelationBoxViewModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LoanNewCancelationBoxLazyDataModel.class);
    private final LoanNewCancelationBoxSearchParameter parameter;
    private final LoanNewCancelationService loanNewCancelationService;
    private List<LoanNewCancelationBoxViewModel> list = new ArrayList<>();
    private Integer total;

    public LoanNewCancelationBoxLazyDataModel(LoanNewCancelationBoxSearchParameter parameter, LoanNewCancelationService loanNewCancelationService) {
        this.parameter = parameter;
        this.loanNewCancelationService = loanNewCancelationService;
    }

    @Override
    public List<LoanNewCancelationBoxViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("cancellation.loan_cancellation_number");
	        }
	        
	        list = loanNewCancelationService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(loanNewCancelationService.getTotalByParam(parameter)));            
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
    public Object getRowKey(LoanNewCancelationBoxViewModel entity) {
        return entity.getCancelationId();
    }

    @Override
    public LoanNewCancelationBoxViewModel getRowData(String id) {
        for (LoanNewCancelationBoxViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getCancelationId()))) {
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
