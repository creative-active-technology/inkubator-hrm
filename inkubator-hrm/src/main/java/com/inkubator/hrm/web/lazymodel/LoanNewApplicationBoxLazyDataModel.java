package com.inkubator.hrm.web.lazymodel;

import com.inkubator.hrm.service.LoanNewApplicationService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.web.model.LoanNewApplicationBoxViewModel;
import com.inkubator.hrm.web.search.LoanNewApplicationBoxSearchParameter;


/**
*
* @author Ahmad Mudzakkir Amal
*/
public class LoanNewApplicationBoxLazyDataModel extends LazyDataModel<LoanNewApplicationBoxViewModel> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoanNewApplicationBoxLazyDataModel.class);
    private final LoanNewApplicationBoxSearchParameter parameter;
    private final LoanNewApplicationService loanNewApplicationService;
    private List<LoanNewApplicationBoxViewModel> list = new ArrayList<>();
    private Integer total;

    public LoanNewApplicationBoxLazyDataModel(LoanNewApplicationBoxSearchParameter parameter, LoanNewApplicationService loanNewApplicationService) {
        this.parameter = parameter;
        this.loanNewApplicationService = loanNewApplicationService;
    }

    @Override
    public List<LoanNewApplicationBoxViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("approvalActivityId");
	        }
	        
	        list = loanNewApplicationService.getUndisbursedActivityByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(loanNewApplicationService.getTotalUndisbursedActivityByParam(parameter)));            
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
    public Object getRowKey(LoanNewApplicationBoxViewModel entity) {
        return entity.getApprovalActivityId();
    }

    @Override
    public LoanNewApplicationBoxViewModel getRowData(String id) {
        for (LoanNewApplicationBoxViewModel entity : list) {
            if (id.equals(String.valueOf(entity.getApprovalActivityId()))) {
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
