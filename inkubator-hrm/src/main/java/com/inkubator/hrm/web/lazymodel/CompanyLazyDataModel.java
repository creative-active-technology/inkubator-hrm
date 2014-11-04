package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.Company;
import com.inkubator.hrm.service.CompanyService;
import com.inkubator.hrm.web.search.CompanySearchParameter;


/**
*
* @author rizkykojek
*/
public class CompanyLazyDataModel extends LazyDataModel<Company> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CompanyLazyDataModel.class);
    private final CompanySearchParameter parameter;
    private final CompanyService companyService;
    private List<Company> companies = new ArrayList<>();
    private Integer total;

    public CompanyLazyDataModel(CompanySearchParameter parameter, CompanyService companyService) {
        this.parameter = parameter;
        this.companyService = companyService;
    }

    @Override
    public List<Company> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        companies = companyService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(companyService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return companies;
    }

    @Override
    public Object getRowKey(Company company) {
        return company.getId();
    }

    @Override
    public Company getRowData(String id) {
        for (Company company : companies) {
            if (id.equals(String.valueOf(company.getId()))) {
                return company;
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
