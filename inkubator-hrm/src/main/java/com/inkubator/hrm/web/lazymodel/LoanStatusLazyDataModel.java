/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import com.inkubator.hrm.entity.SavingType;
import com.inkubator.hrm.service.LoanNewApplicationService;
import com.inkubator.hrm.service.SavingTypeService;
import com.inkubator.hrm.web.model.LoanNewApplicationStatusViewModel;
import com.inkubator.hrm.web.search.LoanStatusSearchParameter;
import com.inkubator.hrm.web.search.SavingTypeSearchParameter;

public class LoanStatusLazyDataModel extends LazyDataModel<LoanNewApplicationStatusViewModel> implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(LoanStatusLazyDataModel.class);
    private final LoanStatusSearchParameter searchParameter;
    private final LoanNewApplicationService loanNewApplicationService;
    private List<LoanNewApplicationStatusViewModel> listLoanNewApplication = new ArrayList<>();
    private Integer jumlahData;

    
    
    public LoanStatusLazyDataModel(LoanStatusSearchParameter searchParameter, LoanNewApplicationService loanNewApplicationService) {
		this.searchParameter = searchParameter;
		this.loanNewApplicationService = loanNewApplicationService;
	}

	@Override
    public List<LoanNewApplicationStatusViewModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");

            try {
                Order order = null;
                if(sortField != null){
                    order = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
                }else{
                    order = Order.desc("firstName");
                }
                listLoanNewApplication = loanNewApplicationService.getAllDataLoanNewApplicationStatus(searchParameter, first, pageSize, order);
                jumlahData = Integer.parseInt(String.valueOf(loanNewApplicationService.getTotalDataLoanNewApplicationStatus(searchParameter)));
            } catch (Exception ex) {
                LOGGER.error("Error", ex);
            }
        
        LOGGER.info("Success Load Lazy data Model");

        setPageSize(pageSize);
        setRowCount(jumlahData);
        return listLoanNewApplication;
    }
    
    @Override
    public Object getRowKey(LoanNewApplicationStatusViewModel loanNewApplicationStatusViewModel) {
        return loanNewApplicationStatusViewModel.getId();
    }

    @Override
    public LoanNewApplicationStatusViewModel getRowData(String id) {
        for (LoanNewApplicationStatusViewModel loanNewApplicationStatusViewModel : listLoanNewApplication) {
            if (id.equals(String.valueOf(loanNewApplicationStatusViewModel.getId()))) {
                return loanNewApplicationStatusViewModel;
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
