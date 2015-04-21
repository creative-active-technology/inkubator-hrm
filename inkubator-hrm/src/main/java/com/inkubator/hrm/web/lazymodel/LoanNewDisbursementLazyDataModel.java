package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LoanNewApplication;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.web.search.LoanNewSearchParameter;

/**
*
* @author Ahmad Mudzakkir Amal
*/
public class LoanNewDisbursementLazyDataModel extends LazyDataModel<LoanNewApplication> implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoanNewDisbursementLazyDataModel.class);
    private final LoanNewSearchParameter parameter;
    private final LoanNewApplicationService loanNewApplicationService;
    private List<LoanNewApplication> loanNewApplications = new ArrayList<>();
    private Integer total;

    public LoanNewDisbursementLazyDataModel(LoanNewSearchParameter parameter, LoanNewApplicationService loanNewApplicationService) {
        this.parameter = parameter;
        this.loanNewApplicationService = loanNewApplicationService;
    }

    @Override
    public List<LoanNewApplication> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        loanNewApplications = loanNewApplicationService.getByParamByStatusUndisbursed(parameter, first, pageSize, orderable);
                total = Integer.parseInt(String.valueOf(loanNewApplicationService.getTotalByParamByStatusUndisbursed(parameter)));                            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }        
        setPageSize(pageSize);
        setRowCount(total);
        return loanNewApplications;
    }

    @Override
    public Object getRowKey(LoanNewApplication loanNewApplication) {
        return loanNewApplication.getId();
    }

    @Override
    public LoanNewApplication getRowData(String id) {
        for (LoanNewApplication loanNewApplication : loanNewApplications) {
            if (id.equals(String.valueOf(loanNewApplication.getId()))) {
                return loanNewApplication;
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
