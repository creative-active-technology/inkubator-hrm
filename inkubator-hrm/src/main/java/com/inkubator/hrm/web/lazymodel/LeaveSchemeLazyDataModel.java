package com.inkubator.hrm.web.lazymodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.inkubator.hrm.entity.LeaveScheme;
import com.inkubator.hrm.service.LeaveSchemeService;
import com.inkubator.hrm.web.search.LeaveSchemeSearchParameter;

/**
*
* @author rizkykojek
*/
public class LeaveSchemeLazyDataModel extends LazyDataModel<LeaveScheme> implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(LeaveSchemeLazyDataModel.class);
    private final LeaveSchemeSearchParameter parameter;
    private final LeaveSchemeService leaveSchemeService;
    private List<LeaveScheme> leaveSchemes = new ArrayList<>();
    private Integer total;

    public LeaveSchemeLazyDataModel(LeaveSchemeSearchParameter parameter, LeaveSchemeService leaveSchemeService) {
        this.parameter = parameter;
        this.leaveSchemeService = leaveSchemeService;
    }

    @Override
    public List<LeaveScheme> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        LOGGER.info("Step Load Lazy data Model");        
        try {
        	Order orderable = null;
	        if (sortField != null) {
	            orderable = (sortOrder == SortOrder.ASCENDING) ? Order.asc(sortField) : Order.desc(sortField);
	        } else {
	        	orderable = Order.desc("name");
	        }
	        
	        leaveSchemes = leaveSchemeService.getByParam(parameter, first, pageSize, orderable);
            total = Integer.parseInt(String.valueOf(leaveSchemeService.getTotalByParam(parameter)));            
        	LOGGER.info("Success Load Lazy data Model");
        	
        } catch (Exception ex) {
            LOGGER.error("Failed Load Lazy data Model");
            LOGGER.error("Error = ", ex);
        }

        setPageSize(pageSize);
        setRowCount(total);
        return leaveSchemes;
    }

    @Override
    public Object getRowKey(LeaveScheme leaveScheme) {
        return leaveScheme.getId();
    }

    @Override
    public LeaveScheme getRowData(String id) {
        for (LeaveScheme leaveScheme : leaveSchemes) {
            if (id.equals(String.valueOf(leaveScheme.getId()))) {
                return leaveScheme;
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
