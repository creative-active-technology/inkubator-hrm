package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.CompanyPolicy;
import com.inkubator.hrm.service.CompanyPolicyService;
import com.inkubator.hrm.web.search.CompanyPolicySearchParameter;

/**
*
* @author rizkykojek
*/
public class CompanyPolicyLazyDataModel extends LazyDataModel<CompanyPolicy> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(CompanyPolicyLazyDataModel.class);
    private final CompanyPolicySearchParameter parameter;
    private final CompanyPolicyService service;
    private List<CompanyPolicy> list = new ArrayList<>();
    private Integer total;

    public CompanyPolicyLazyDataModel(CompanyPolicySearchParameter parameter, CompanyPolicyService companyPolicyService) {
        this.parameter = parameter;
        this.service = companyPolicyService;
    }

    @Override
    public List<CompanyPolicy> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("createdOn");
	        }
	        
	        list = service.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(service.getTotalByParam(parameter)));            
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
    public Object getRowKey(CompanyPolicy companyPolicy) {
        return companyPolicy.getId();
    }

    @Override
    public CompanyPolicy getRowData(String id) {
        for (CompanyPolicy companyPolicy : list) {
            if (id.equals(String.valueOf(companyPolicy.getId()))) {
                return companyPolicy;
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
