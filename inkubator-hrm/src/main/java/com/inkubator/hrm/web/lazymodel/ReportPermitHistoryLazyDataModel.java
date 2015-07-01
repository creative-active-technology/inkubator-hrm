package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.service.PermitImplementationService;
import com.inkubator.hrm.web.model.ReportPermitHistoryModel;
import com.inkubator.hrm.web.search.ReportPermitHistorySearchParameter;


/**
*
* @author rizkykojek
*/
public class ReportPermitHistoryLazyDataModel extends LazyDataModel<ReportPermitHistoryModel> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(ReportPermitHistoryLazyDataModel.class);
    private final ReportPermitHistorySearchParameter parameter;
    private final PermitImplementationService permitImplementationService;
    private List<ReportPermitHistoryModel> list = new ArrayList<>();
    private Integer total;

    public ReportPermitHistoryLazyDataModel(ReportPermitHistorySearchParameter parameter, PermitImplementationService permitImplementationService) {
        this.parameter = parameter;
        this.permitImplementationService = permitImplementationService;
    }

    @Override
    public List<ReportPermitHistoryModel> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.asc("numberFilling");
	        }
	        
	        list = permitImplementationService.getReportPermitHistoryByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(permitImplementationService.getTotalReportPermitHistoryByParam(parameter)));            
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
    public Object getRowKey(ReportPermitHistoryModel entity) {
        return entity.getId();
    }

    @Override
    public ReportPermitHistoryModel getRowData(String id) {
        for (ReportPermitHistoryModel entity : list) {
            if (id.equals(String.valueOf(entity.getId()))) {
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
