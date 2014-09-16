package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Loan;
import com.inkubator.hrm.service.LoanService;
import com.inkubator.hrm.web.search.LoanSearchParameter;


/**
*
* @author rizkykojek
*/
public class LoanLazyDataModel extends LazyDataModel<Loan> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LoanLazyDataModel.class);
    private final LoanSearchParameter parameter;
    private final LoanService loanService;
    private List<Loan> loans = new ArrayList<>();
    private Integer total;

    public LoanLazyDataModel(LoanSearchParameter parameter, LoanService loanService) {
        this.parameter = parameter;
        this.loanService = loanService;
    }

    @Override
    public List<Loan> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("id");
	        }
	        
	        loans = loanService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(loanService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return loans;
    }

    @Override
    public Object getRowKey(Loan loan) {
        return loan.getId();
    }

    @Override
    public Loan getRowData(String id) {
        for (Loan loan : loans) {
            if (id.equals(String.valueOf(loan.getId()))) {
                return loan;
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
